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
package org.jboss.weld.bootstrap.api.helpers;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.ServiceRegistry;

/**
 * Utility class for {@link ServiceRegistry}
 */
public class ServiceRegistries {

    private ServiceRegistries() {
    }

    /**
     * Returns a view of the provided {@link ServiceRegistry} that prevents adding, removing or replacing registrations.
     * Changes made through the original registry remain visible. Service cleanup operations are still delegated.
     *
     * @param serviceRegistry service registry to process
     * @return unmodifiable variant
     */
    public static ServiceRegistry unmodifiableServiceRegistry(final ServiceRegistry serviceRegistry) {
        Map<Class<? extends Service>, Service> services = Collections.unmodifiableMap(new AbstractMap<>() {
            @Override
            public Set<Entry<Class<? extends Service>, Service>> entrySet() {
                return serviceRegistry.entrySet();
            }
        });
        return new ForwardingServiceRegistry() {

            @Override
            public <S extends Service> void add(Class<S> type, S service) {
                throw new UnsupportedOperationException("This service registry is unmodifiable");
            }

            @Override
            public <S extends Service> S addIfAbsent(Class<S> type, S service) {
                throw new UnsupportedOperationException("This service registry is unmodifiable");
            }

            @Override
            public void addAll(Collection<Entry<Class<? extends Service>, Service>> services) {
                throw new UnsupportedOperationException("This service registry is unmodifiable");
            }

            @Override
            public Set<Entry<Class<? extends Service>, Service>> entrySet() {
                return services.entrySet();
            }

            @Override
            public Iterator<Service> iterator() {
                return services.values().iterator();
            }

            @Override
            protected ServiceRegistry delegate() {
                return serviceRegistry;
            }

        };
    }

}
