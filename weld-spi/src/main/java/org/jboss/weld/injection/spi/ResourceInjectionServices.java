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

import jakarta.enterprise.inject.spi.DefinitionException;
import jakarta.enterprise.inject.spi.InjectionPoint;

import org.jboss.weld.bootstrap.api.Service;

/**
 * A container should implement this interface to allow the Weld to resolve Resources
 *
 * {@link ResourceInjectionServices} is per-module service.
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 */
public interface ResourceInjectionServices extends Service {

    /**
     * Register a resource injection point. The implementation validates the injection point. If the validation passes, an
     * instance of {@link ResourceReferenceFactory} is returned which may be used at runtime for creating instances of the
     * resource.
     *
     * @param injectionPoint the injection point metadata
     * @return resource factory
     * @throws DefinitionException if the injection point is not annotated with @Resource, if the injection point is a method
     *         that doesn't follow JavaBean conventions or if the injection point type does not match the resource type
     * @throws IllegalStateException if no resource can be resolved
     */
    ResourceReferenceFactory<Object> registerResourceInjectionPoint(InjectionPoint injectionPoint);

    /**
     * Register a resource injection point with the given JNDI name and mapped name. The implementation validates the injection
     * point. If the validation passes, an instance of {@link ResourceReferenceFactory} is returned which may be used at runtime
     * for creating instances of the resource.
     *
     * @param jndiName JNDI name
     * @param mappedName mapped name
     * @return resource factory
     * @throws IllegalStateException if no resource can be resolved
     * @throws IllegalArgumentException if both jndiName and mappedName are null
     */
    ResourceReferenceFactory<Object> registerResourceInjectionPoint(String jndiName, String mappedName);

}