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

import java.util.Map;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.interceptor.AroundConstruct;
import javax.interceptor.InvocationContext;

/**
 * An implementation of this interface may be registered with a {@link WeldCreationalContext}. Weld will then call the
 * {@link #aroundConstruct(ConstructionHandle, AnnotatedConstructor, Object[], Map)} method of the implementation during component creation, allowing an
 * integrator to perform additional steps (e.g. invoking interceptors bound using the deployment descriptor) around component construction.
 *
 * @author Jozef Hartinger
 *
 * @see WeldCreationalContext#registerAroundConstructCallback(AroundConstructCallback)
 *
 * @param <T> type the component class
 */
public interface AroundConstructCallback<T> {

    /**
     * The method is called during component creation, allowing an integrator to perform additional steps (e.g. invoking interceptors bound using the deployment
     * descriptor) around component construction.
     *
     * @param handle the handle for controlling the component creation process and retrieving the created instance
     * @param constructor a representation of the component constructor used for component creation
     * @param parameters the parameters that will be passed to the component constructor. These parameters should be made available to {@link AroundConstruct}
     *        interceptors through the {@link InvocationContext#getParameters()} method. An implementation is free to modify the parameters or provide a
     *        different parameter array to the {@link ConstructionHandle}.
     * @param data the context data associated with this {@link AroundConstruct} interception. The data should be made available to {@link AroundConstruct}
     *        interceptors through {@link InvocationContext#getContextData()}. An implementation is free to modify the map or to provide a different one to the
     *        {@link ConstructionHandle}.
     * @return the created instance
     * @throws Exception
     */
    T aroundConstruct(ConstructionHandle<T> handle, AnnotatedConstructor<T> constructor, Object[] parameters, Map<String, Object> data) throws Exception;
}
