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

/**
 * A handle for controlling {@link AroundConstructCallback} invocations.
 *
 * @author Jozef Hartinger
 *
 * @see AroundConstructCallback
 *
 * @param <T> type the component class
 */
public interface ConstructionHandle<T> {

    /**
     * Proceed to the next {@link AroundConstructCallback}. If there is no next callback, the component is constructed and the component instance is returned
     * from the method.
     *
     * @param parameters the parameters to be passed to the component constructor
     * @param data the context data associated with the {@link AroundConstructCallback} interception
     * @return instance the constructed instance
     */
    T proceed(Object[] parameters, Map<String, Object> data);
}
