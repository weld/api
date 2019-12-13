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
package org.jboss.weld.construction.api;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.interceptor.AroundConstruct;
import jakarta.interceptor.Interceptors;

/**
 * Extended version of {@link CreationalContext} which gives the integrator additional control over the process of constructing an instance.
 *
 * @author Jozef Hartinger
 *
 */
public interface WeldCreationalContext<T> extends CreationalContext<T> {

    /**
     * By default Weld takes care of {@link AroundConstruct} interceptors of a component instance which are bound to the component using interceptor bindings or
     * the {@link Interceptors} annotation. This may not be desired should an integrator want to manage these interceptors themselves. In that case this switch
     * may be used to suppress Weld management of {@link AroundConstruct} interceptors. In that case an integrator is responsible for performing
     * {@link AroundConstruct} interception.
     *
     * @see #registerAroundConstructCallback(AroundConstructCallback)
     *
     * @param value the value
     */
    void setConstructorInterceptionSuppressed(boolean value);

    /**
     * Indicates whether Weld-managed {@link AroundConstruct} interceptors are suppressed.
     *
     * @see #setConstructorInterceptionSuppressed(boolean)
     *
     * @return true if Weld-managed {@link AroundConstruct} interceptors are suppressed
     */
    boolean isConstructorInterceptionSuppressed();

    /**
     * Register a callback which is notified of component construction. This callback allows an integrator to perform additional tasks (e.g. invoking
     * interceptors bound using the deployment descriptor) around constructor invocation. {@link AroundConstructCallback}s are invoked in the order in which
     * they were registered.
     *
     * @param callback the callback
     */
    void registerAroundConstructCallback(AroundConstructCallback<T> callback);
}
