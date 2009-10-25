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

import java.lang.annotation.Annotation;

import javax.enterprise.inject.TypeLiteral;

/**
 * <p>Allows the application to fire events of a particular type, 
 * and register observers for events of that type.</p>
 * 
 * <p>Beans fire events via an instance of the <tt>Event</tt> 
 * interface, which may be injected:</p>
 * 
 * <pre>
 * &#064;Inject &#064;Any Event&lt;LoggedInEvent&gt; loggedInEvent;
 * </pre>
 * 
 * <p>The method {@link javax.enterprise.event.Event#fire(T)} accepts 
 * an event object:</p>
 * 
 * <p>Any combination of qualifiers may be specified at the injection 
 * point:</p>
 * 
 * <pre>
 * &#064;Inject &#064;Admin Event&lt;LoggedInEvent&gt; adminLoggedInEvent;
 * </pre>
 * 
 * <p>Or, the {@link javax.enterprise.inject.Any &#064;Any} qualifier may 
 * be used, allowing the application to specify qualifiers dynamically:</p>
 * 
 * <pre>
 * &#064;Inject &#064;Any Event&lt;LoggedInEvent&gt; loggedInEvent;
 * </pre>
 * 
 * <p>For an injected <tt>Event</tt>:</p>
 * 
 * <ul>
 * <li>the specified type is the type parameter specified at the injection 
 * point, and</li>
 * <li>the specified qualifiers are the qualifiers specified at the injection 
 * point.</li>
 * </ul>
 * 
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author David Allen
 * 
 * @param <T> the type of the event object
 */

public interface Event<T>
{

   /**
    * Fire an event
    * 
    * @param event the event type
    */
   public void fire(T event);
   
   /**
    * Returns a child Event with the additional specified qualifiers.
    * @param qualifiers Additional qualifiers to add to child Event
    * @return new child Event
    */
   public Event<T> select(Annotation... qualifiers);
   
   /**
    * Returns a child Event of the type specified and additional specified qualifiers.
    * @param <U> The subtype of T for the child Event
    * @param subtype The class of the subtype of T
    * @param qualifiers Additional specified qualifiers
    * @return new child Event
    */
   public <U extends T> Event<U> select(Class<U> subtype, Annotation... qualifiers);
   
   /**
    * Returns a child Event of the type specified and additional specified qualifiers.
    * @param <U> The subtype of T for the child Event
    * @param subtype The TypeLiteral of the subtype of T
    * @param qualifiers Additional specified qualifiers
    * @return new child Event
    */   
   public <U extends T> Event<U> select(TypeLiteral<U> subtype, Annotation... qualifiers);
}
