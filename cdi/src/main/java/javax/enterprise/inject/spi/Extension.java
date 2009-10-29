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

package javax.enterprise.inject.spi;

/**
 * <p>Service interface implemented by service providers that 
 * observe container lifecycle events. Service providers must
 * be declared in <tt>META-INF/services</tt></p>
 * 
 * <p>The container instantiates a single instance of each 
 * service provider at the beginning of the application 
 * initialization process and maintains a reference to it until
 * the application shuts down.</p>
 *  
 * <p>Service providers may have observer methods, which may 
 * observe any event, including any container lifecycle event, 
 * and obtain an injected 
 * {@link javax.enterprise.inject.spi.BeanManager}.<p>
 * 
 * <p>Service providers are made available for injection as beans
 * with the qualifier {@link javax.context.Default &#064;Default}.</p>
 * 
 * 
 * @author Gavin King
 * @author Pete Muir
 *
 */
public interface Extension {}