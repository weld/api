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

package javax.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <p>The built-in qualifier type.</p>
 * 
 * <p>This allows the application to obtain a new instance of a bean which is
 * not bound to the declared scope, but has had dependency injection performed.</p>
 *
 * <pre>
 * &#064;Produces &#064;ConversationScoped 
 * &#064;Special Order getSpecialOrder(&#064;New(Order.class) Order order) {
 *    ...
 *    return order;
 * }
 * </pre>
 *    
 * <p>When the qualifier {@link javax.enterprise.inject.New &#064;New} is specified 
 * at an injection point and no {@link javax.enterprise.inject.New#value() value} 
 * member is explicitly specified, the container defaults the 
 * {@link javax.enterprise.inject.New#value() value} to the declared type of the 
 * injection point. So the following injection point has qualifier
 * <tt>&#064;New(Order.class)</tt>:</p>
 * 
 * <pre>
 * &#064;Produces &#064;ConversationScoped 
 * &#064;Special Order getSpecialOrder(&#064;New Order order) { ... }
 * </pre>
 *
 * @author Gavin King
 * @author Pete Muir
 */

@Target( { FIELD, PARAMETER, METHOD, TYPE })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface New
{
   /**
    * @return the bean class of the bean to be injected
    */
   Class<?> value() default New.class;
   
}
