/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
 * An integrator should implement this interface to allow Weld to resolve web service references
 *
 * {@link JaxwsInjectionServices} is a per-module service.
 *
 * @author Jozef Hartinger
 *
 */
public interface JaxwsInjectionServices extends Service {

    /**
     * Register a WebServiceRef injection point. The implementation validates the injection point. If the validation passes, an
     * instance of {@link ResourceReferenceFactory} is returned which may be used at runtime for creating instances of the
     * resource.
     *
     * @param <T> the type of the injected instance
     * @param injectionPoint the injection point metadata
     * @return factory for the web service reference
     * @throws DefinitionException if there is a definition problem related to the injection point
     */
    <T> ResourceReferenceFactory<T> registerWebServiceRefInjectionPoint(InjectionPoint injectionPoint);
}
