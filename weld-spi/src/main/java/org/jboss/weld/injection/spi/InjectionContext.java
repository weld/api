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

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.inject.Inject;

import org.jboss.weld.ejb.api.SessionObjectReference;

/**
 * The context in which instance injection occurs.
 *
 * @author Pete Muir
 *
 */
public interface InjectionContext<T> {

    /**
     * Calling {@link #proceed()} will cause Weld to perform injection on the instance as it normally would. It is legal to not
     * call {@link #proceed()}, however the container must ensure all injection, including CDI-style {@link Inject} injection is
     * done.
     */
    void proceed();

    /**
     * Get the underlying instance to be injected. If the instance being injected is an EJB this will be whatever is returned by
     * {@link SessionObjectReference#getBusinessObject(Class)}
     *
     */
    T getTarget();

    /**
     * Get the {@link InjectionTarget} for the instance being injected
     *
     */
    InjectionTarget<T> getInjectionTarget();

    /**
     * Get the {@link AnnotatedType} for the instance being injected
     *
     */
    AnnotatedType<T> getAnnotatedType();

}
