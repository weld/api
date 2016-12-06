/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.bootstrap.event;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InterceptionType;
import javax.enterprise.inject.spi.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Allows to configure a new {@link Interceptor} instance.
 *
 * @author Tomas Remes
 * @seeIssue WELD-2008
 */
public interface InterceptorConfigurator {

    /**
     * @param interceptionType
     * @param interceptorFunction
     * @return self
     */
    InterceptorConfigurator intercept(InterceptionType interceptionType, Function<InvocationContext, Object> interceptorFunction);

    /**
     * @param interceptionType
     * @param interceptorFunction
     * @return self
     */
    InterceptorConfigurator interceptWithMetadata(InterceptionType interceptionType, BiFunction<InvocationContext, Bean<?>, Object> interceptorFunction);

    /**
     * Adds interceptor binding annotation.
     *
     * @param binding
     * @return self
     */
    InterceptorConfigurator addBinding(Annotation binding);

    /**
     * Adds interceptor binding annotations.
     *
     * @param bindings
     * @return self
     */
    InterceptorConfigurator addBindings(Annotation... bindings);

    /**
     * Adds set of interceptor binding annotations.
     *
     * @param bindings
     * @return self
     */
    InterceptorConfigurator addBindings(Set<Annotation> bindings);

    /**
     * Replace all bindings
     *
     * @param bindings - new bindings to be set
     * @return self
     */
    InterceptorConfigurator bindings(Annotation... bindings);

    /**
     * Adds priority annotation.
     *
     * @param priority value
     * @return self
     */
    InterceptorConfigurator priority(int priority);

    /**
     * @return interceptor
     */
    Interceptor<?> build();

}
