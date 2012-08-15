/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package org.jboss.weld.injection.spi;

import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.weld.bootstrap.api.Service;

/**
 * A container should implement this interface to allow Weld to
 * resolve EJB.
 * 
 * {@link EjbInjectionServices} is a per-module service.
 * 
 * @author Pete Muir
 * @author Jozef Hartinger
 * 
 */
public interface EjbInjectionServices extends Service
{

   /**
    * Register an EJB injection point. The implementation validates the injection point. If the validation passes, an instance of
    * {@link ResourceReferenceFactory} is returned which may be used at runtime for creating instances of the resource.
    * 
    * @param injectionPoint
    *           the injection point metadata
    * @return an instance of the EJB
    * @throws IllegalArgumentException
    *            if the injection point is not annotated with @EJB, or, if the
    *            injection point is a method that doesn't follow JavaBean
    *            conventions
    * @throws IllegalStateException if no suitable EJB can be resolved
    */
   public ResourceReferenceFactory<Object> registerEjbInjectionPoint(InjectionPoint injectionPoint);

   /**
    * Resolve the value for the given @EJB injection point.
    * 
    * @deprecated Instead of calling this method at runtime, Weld should register
    * every EJB injection point at bootstrap using {@link #registerEjbInjectionPoint(InjectionPoint)}
    * and use the returned factory for producing injectable instances at runtime.
    * 
    * @param injectionPoint
    *           the injection point metadata
    * @return an instance of the EJB
    * @throws IllegalArgumentException
    *            if the injection point is not annotated with @EJB, or, if the
    *            injection point is a method that doesn't follow JavaBean
    *            conventions
   
    */
   @Deprecated
   public Object resolveEjb(InjectionPoint injectionPoint);

}
