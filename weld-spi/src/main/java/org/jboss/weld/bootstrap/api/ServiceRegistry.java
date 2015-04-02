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
package org.jboss.weld.bootstrap.api;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 * A service registry
 *
 * @author Pete Muir
 *
 */
public interface ServiceRegistry extends Iterable<Service> {

    /**
     * Add a service
     *
     * @see Service
     *
     * @param <S> the service type to add
     * @param type the service type to add
     * @param service the service implementation
     */
    <S extends Service> void add(Class<S> type, S service);

    /**
     * Add a service if no implementation of the given service is registered with the registry yet.
     * Returns null if the given service was added. Otherwise, the previous service implementation is returned.
     *
     * @param <S> the service type to add
     * @param type the service type to add
     * @param service the service implementation
     * @return null if no service implementation was previously associated with the given service. Otherwise, the previous service implementation is returned.
     */
    <S extends Service> S addIfAbsent(Class<S> type, S service);

    /**
     * Add services
     *
     * @param services services to be added
     */
    void addAll(Collection<Entry<Class<? extends Service>, Service>> services);

    Set<Entry<Class<? extends Service>, Service>> entrySet();

    /**
     * Retrieve a service implementation
     *
     * @param <S> the service type
     * @param type the service type
     * @return the service implementation, or null if none is registered
     */
    <S extends Service> S get(Class<S> type);

    /**
     * Retrieve a service implementation wrapped in {@link Optional}.
     *
     * @param <S> the service type
     * @param type the service type
     * @return the service implementation wrapped within {@link Optional}
     */
    <S extends Service> Optional<S> getOptional(Class<S> type);

    /**
     * Retrieve a required service implementation. Throws an exception if the service is not available
     *
     * @param <S> the service type
     * @param type the service type
     * @return the service implementation
     * @throws IllegalStateException if the service is not available
     */
    <S extends Service> S getRequired(Class<S> type);

    /**
     * Check if a service is registered
     *
     * @param <S> the service type
     * @param type the service type
     * @return true if a service is registered, otherwise false
     */
    <S extends Service> boolean contains(Class<S> type);

    /**
     * Clear up the services registered, by calling {@link Service#cleanup()} on each registered service
     */
    void cleanup();

    /**
     * Clean up {@link BootstrapService}s by calling {@link BootstrapService#cleanupAfterBoot()} on each registered
     * {@link BootstrapService}.
     */
    void cleanupAfterBoot();
}