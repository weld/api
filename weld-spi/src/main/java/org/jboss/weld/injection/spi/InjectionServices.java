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

import javax.enterprise.inject.spi.DefinitionException;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.weld.bootstrap.api.Service;

/**
 * Provides callbacks to the container when Weld performs injection on an
 * InjectionTarget, managed bean or session bean
 *
 * This service may be used to provide EE-style injection.
 *
 * The container <b>must</b> respect any modifications made to type via the
 * container lifecycle events. Container lifecycle events may alter the
 * annotations placed on the type, it's members, and the formal parameters of
 * it's members. {@link InjectionContext#getAnnotatedType()} gives access to the
 * modified state of the type.
 *
 * {@link InjectionServices} is a per-BeanDeploymentArchive service.
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 */
public interface InjectionServices extends Service
{

   /**
    * Callback for injection.
    *
    * Call {@link InjectionContext#proceed()} to cause CDI-style injection to
    * occur.
    *
    * @param injectionContext
    */
   public <T> void aroundInject(InjectionContext<T> injectionContext);

    /**
     * For each discovered
     * <ul>
     * <li>@Resource injection point</li>
     * <li>@PersistenceContext injection point</li>
     * <li>@PersistenceUnit injection point</li>
     * <li>@EJB injection point</li>
     * <li>@WebServiceRef injection point</li>
     * </ul>
     *
     * Weld calls this method to validate the injection point type. The implementation is responsible for validation of the
     * injection point type. If the injection point type differs from the type of the matching object in the Java EE component
     * environment, the implementation throws {@link DefinitionException}.
     *
     * @param injectionPoint injection point to be validated
     */
   public void validateResourceInjectionPoint(InjectionPoint injectionPoint);

}
