/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package javax.enterprise.event;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Specifies that a parameter of a method of a bean implementation class is the
 * event parameter of an observer method. An observer method is a non-abstract
 * method of a managed bean class or session bean class. An observer method may
 * be either static or non-static. If the bean is a session bean, the observer
 * method must be either a business method of the EJB or a static method of the
 * bean class. A bean class may also have any number of observer methods
 * declared.
 * </p>
 * 
 * <p>
 * To observe a simple Java class as the event type, the {@literal @}Observes
 * annotation is used directly on the event parameter.</p>
 * <pre>
 *     public void afterLogin(@Observes LoggedInEvent event) { ... }
 * </pre>
 * 
 * <p>
 * Qualifiers may also be added to further differentiate between events of the same
 * type.  When multiple qualifiers are used, each of them must exist on the event
 * in order for the observer method to be invoked.  Also note that any of the
 * qualifiers may have type members bound to specific values which also must
 * match those of the event object.</p>
 * <pre>
 *     public void afterLogin(@Observes @Admin LoggedInEvent event) { ... }
 *     public void afterDocumentUpdatedByAdmin(@Observes @Updated @ByAdmin Document doc) { ... }
 *     public void afterAdminLogin(@Observes @Role("admin") LoggedInEvent event) { ... }
 * </pre>
 * 
 * <p>
 * An application may have any number of observers for the same event type. The
 * order in which these observers are called is indeterminate. For this reason,
 * caution must be used if any one of the observers might throw an exception
 * since the remaining observers not yet notified will never see the event.
 * Event processing for a single event is terminated whenever an exception is
 * thrown by an observer. Therefore the code should not make any assumptions
 * about the state of other similar observers.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author David Allen
 */

@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface Observes
{
   /**
    * Specifies when an observer method should be notified of an event.
    * Defaults to ALWAYS meaning that if a bean instance with the observer
    * method does not already exist, one will be created to receive the
    * event.
    */
	public Reception notifyObserver() default Reception.ALWAYS;
	
	/**
	 * Specifies whether or not the notification should occur as part of
	 * an ongoing transaction, and if so, in which phase of the transaction
	 * the notification should occur.  The default is IN_PROGRESS meaning
	 * the notification is not transactional.
	 */
	public TransactionPhase during() default TransactionPhase.IN_PROGRESS;
}
