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

package javax.decorator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

/**
 * <p>Specifies that a class is a decorator. May be applied to
 * a bean class.</p>
 * 
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { ... }
 * </pre>
 * 
 * <p>A decorator implements one or more bean types and 
 * intercepts business method invocations of beans which 
 * implement those bean types. These bean types are called 
 * decorated types.</p>
 * 
 * <p>A decorator is a managed bean. The set of decorated types 
 * of a decorator includes all interfaces implemented directly or 
 * indirectly by the bean class, except for {@link java.io.Serializable}. 
 * The decorator bean class and its superclasses are not decorated 
 * types of the decorator. The decorator class may be abstract.</p>
 * 
 * <p>Decorators of a session bean must comply with the bean provider 
 * programming restrictions defined by the EJB specification. 
 * Decorators of a stateful session bean must comply with the rules 
 * for instance passivation and conversational state defined by the 
 * EJB specification.</p>
 * 
 * <p>A decorator may be an abstract class, and is not required to 
 * implement every method of every decorated type.</p>
 *  
 * <p>A decorator intercepts every method:</p>
 * <ul>
 * <li>declared by a decorated type of the decorator</li>
 * <li>that is implemented by the bean class of the decorator.</li>
 * </ul>
 * 
 * @see javax.decorator.Decorates &#064;Decorates identifies the 
 * delegate injection point of a decorator.
 * 
 * @author Gavin King
 * @author Pete Muir
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Stereotype
public @interface Decorator
{
}
