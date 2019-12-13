/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.interceptor;

import java.lang.annotation.Annotation;
import java.util.Set;

import jakarta.interceptor.Interceptors;
import jakarta.interceptor.InvocationContext;

/**
 * Represents an enhanced version of {@link InvocationContext}.
 *
 * @author Martin Kouba
 * @see <a href="https://issues.jboss.org/browse/CDI-468">CDI-468</a>
 */
public interface WeldInvocationContext extends InvocationContext {

    /**
     * A key value under which we store interceptor bindings in {@link InvocationContext}
     */
    String INTERCEPTOR_BINDINGS_KEY = "org.jboss.weld.interceptor.bindings";

    /**
     * Return the set of interceptor bindings for a lifecycle callback, business method, timeout method, or constructor.
     *
     * <p>
     * The returned set may be empty if only interceptors using the {@link Interceptors} annotation are associated.
     * </p>
     *
     * @return a set of interceptor bindings
     */
    Set<Annotation> getInterceptorBindings();

    /**
     * Return the set of interceptor bindings with the specified annotation type.
     *
     * @param <T> subclass of {@link Annotation}
     * @param annotationType type of annotation
     * @return a set of interceptor bindings with the specified annotation type
     * @see #getInterceptorBindings()
     */
    <T extends Annotation> Set<T> getInterceptorBindingsByType(Class<T> annotationType);

}