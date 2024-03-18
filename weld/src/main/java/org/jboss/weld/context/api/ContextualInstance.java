/*
 * JBoss, Home of Professional Open Source
 * Copyright 2018, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.context.api;

import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;

/**
 * Represents a contextual instance of a given type. This is an abstraction on top of the actual bean instance stored in each
 * context. It glues together the actual instance with its {@link CreationalContext} and {@link Contextual}
 *
 * @author Matej Novotny
 */
public interface ContextualInstance<T> {

    /**
     * Returns the actual bean instance
     *
     * @return bean instance
     */
    T getInstance();

    /**
     * Returns the {@link CreationalContext} for this contextual instance
     *
     * @return creational context
     */
    CreationalContext<T> getCreationalContext();

    /**
     * Returns the {@link Contextual} for this contextual instance
     *
     * @return contextual object
     */
    Contextual<T> getContextual();
}
