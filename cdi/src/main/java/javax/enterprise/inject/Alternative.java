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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>Specifies that a bean is an alternative. May be
 * applied to a bean class, producer method or field
 * or {@linkplain javax.enterprise.inject.Stereotype stereotype}.</p>
 * 
 * <pre>
 * &#064;Alternative
 * public class MockOrder extends Order { ... }
 * </pre>
 * 
 * <p>An alternative is never available for injection, 
 * lookup or EL resolution in a Java EE module or library 
 * that is not a bean deployment archive (a module or 
 * library with no <tt>beans.xml</tt> file).</p>
 * 
 * <p>Nor is an alternative available for injection, lookup 
 * or EL resolution in every bean deployment archive. An 
 * alternative must be explicitly <em>selected</em> in every
 * bean deployment archive in which the alternative should 
 * be available for injection, lookup and EL resolution.</p>
 * 
 * <p>By default, a bean deployment archive has no selected 
 * alternatives. An alternative  must be explicitly declared 
 * using the <tt>&lt;alternatives&gt;</tt> element of the 
 * <tt>beans.xml</tt> file of the bean deployment archive.</p>
 * 
 * <p>The <tt>&lt;alternatives&gt;</tt> element contains a 
 * list of bean classes and stereotypes. An alternative is 
 * selected for the bean deployment archive if either:</p>
 * 
 * <ul>
 * <li>the alternative is a managed bean or session bean and 
 * the bean class of the bean is listed,</li>
 * <li>the alternative is a producer method, field or resource, 
 * and the bean class that declares the method or field is listed, 
 * or</li>
 * <li>any {@link javax.enterprise.inject.Alternative &#064;Alternative} 
 * stereotype of the alternative is listed.</li>
 * </ul>
 * 
 * @author Gavin King
 * @author Pete Muir
 */

@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Documented
public @interface Alternative
{
}
