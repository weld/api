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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>Identifies the delegate injection point of a decorator.</p>
 * 
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    &#064;Decorates &#064;Any Logger logger; 
 *    ... 
 * } 
 * </pre>
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    private Logger logger; 
 *    
 *    public TimestampLogger(&#064;Decorates &#064;Debug Logger logger) { 
 *       this.logger=logger; 
 *    } 
 *    ... 
 * } 
 * </pre>
 * 
 * <p>All decorators have a delegate injection point. A delegate 
 * injection point is an injection point of the bean class. The 
 * type and qualifiers of the injection point are called the 
 * delegate type and delegate qualifiers.</p>
 * 
 * <p>The decorator applies to any bean that is eligible for injection 
 * to the delegate injection point.</p>
 * 
 * <p>A decorator must have exactly one delegate injection point. The 
 * delegate injection point must be an injected field, initializer 
 * method parameter or bean constructor method parameter.</p>
 * 
 * <p>The delegate type of a decorator must implement or extend every 
 * decorated type. A decorator is not required to implement the delegate 
 * type.</p>
 * 
 * <p>The container injects a delegate object to the delegate injection 
 * point. The delegate object implements the delegate type and delegates 
 * method invocations along the decorator stack.</p> 
 *
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    &#064;Decorates &#064;Any Logger logger; 
 *    
 *    void log(String message) {
 *       logger.log( timestamp() + ": " + message );
 *    }
 *    ...
 * } 
 * </pre>
 * 
 * @see javax.decorator.Decorator &#064;Decorator specifies that a
 * class is a decorator.
 * 
 * @author Gavin King
 * @author Pete Muir
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface Decorates
{
}
