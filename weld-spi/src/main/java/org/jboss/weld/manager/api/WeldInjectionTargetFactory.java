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
package org.jboss.weld.manager.api;

import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.InjectionTargetFactory;
import javax.interceptor.Interceptors;

public interface WeldInjectionTargetFactory<T> extends InjectionTargetFactory<T> {

    /**
     * Create a new injection target for an interceptor bound using {@link Interceptors} or a deployment descriptor. Unlike
     * {@link #createInjectionTarget(javax.enterprise.inject.spi.Bean)}, the resulting InjectionTarget does not support
     * interception as it is itself an interceptor.
     *
     * @return the injection target
     */
    InjectionTarget<T> createInterceptorInjectionTarget();
}
