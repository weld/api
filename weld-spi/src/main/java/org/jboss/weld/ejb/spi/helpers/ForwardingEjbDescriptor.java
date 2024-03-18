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
package org.jboss.weld.ejb.spi.helpers;

import java.lang.reflect.Method;
import java.util.Collection;

import org.jboss.weld.ejb.spi.BusinessInterfaceDescriptor;
import org.jboss.weld.ejb.spi.EjbDescriptor;

/**
 * An implementation of {@link EjbDescriptor} which forwards all its method calls to another {@link EjbDescriptor} . Subclasses
 * should override one or more methods to modify the behavior of the backing {@link EjbDescriptor} as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * @author Pete Muir
 *
 */
public abstract class ForwardingEjbDescriptor<T> implements EjbDescriptor<T> {

    /**
     * Returns the delegate
     *
     * @return delegate
     */
    protected abstract EjbDescriptor<T> delegate();

    @Override
    public Collection<BusinessInterfaceDescriptor<?>> getLocalBusinessInterfaces() {
        return delegate().getLocalBusinessInterfaces();
    }

    @Override
    public Collection<BusinessInterfaceDescriptor<?>> getRemoteBusinessInterfaces() {
        return delegate().getRemoteBusinessInterfaces();
    }

    @Override
    public Collection<Method> getRemoveMethods() {
        return delegate().getRemoveMethods();
    }

    @Override
    public Class<T> getBeanClass() {
        return delegate().getBeanClass();
    }

    @Override
    public String getEjbName() {
        return delegate().getEjbName();
    }

    @Override
    public boolean isMessageDriven() {
        return delegate().isMessageDriven();
    }

    @Override
    public boolean isSingleton() {
        return delegate().isSingleton();
    }

    @Override
    public boolean isStateful() {
        return delegate().isStateful();
    }

    @Override
    public boolean isStateless() {
        return delegate().isStateless();
    }

    @Override
    public boolean isPassivationCapable() {
        return delegate().isPassivationCapable();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ForwardingEjbDescriptor<?>) {
            return delegate().equals(ForwardingEjbDescriptor.class.cast(obj).delegate());
        }
        return delegate().equals(obj);
    }

    @Override
    public String toString() {
        return delegate().toString();
    }

    @Override
    public int hashCode() {
        return delegate().hashCode();
    }
}
