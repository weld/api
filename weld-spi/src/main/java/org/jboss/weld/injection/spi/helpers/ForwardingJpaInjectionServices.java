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
package org.jboss.weld.injection.spi.helpers;

import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.jboss.weld.injection.spi.JpaInjectionServices;
import org.jboss.weld.injection.spi.ResourceReferenceFactory;

/**
 * An implementation of {@link JpaInjectionServices} which forwards all its method calls to another {@link JpaInjectionServices}
 * . Subclasses should override one or more methods to modify the behavior of the backing {@link JpaInjectionServices} as
 * desired per the <a href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * @author Pete Muir
 *
 */
public abstract class ForwardingJpaInjectionServices implements JpaInjectionServices {

    /**
     * Returns the delegate
     *
     * @return delegate
     */
    protected abstract JpaInjectionServices delegate();

    public ResourceReferenceFactory<EntityManager> registerPersistenceContextInjectionPoint(InjectionPoint injectionPoint) {
        return delegate().registerPersistenceContextInjectionPoint(injectionPoint);
    }

    public ResourceReferenceFactory<EntityManagerFactory> registerPersistenceUnitInjectionPoint(InjectionPoint injectionPoint) {
        return delegate().registerPersistenceUnitInjectionPoint(injectionPoint);
    }

    @Override
    public String toString() {
        return delegate().toString();
    }

    @Override
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || delegate().equals(obj);
    }

}
