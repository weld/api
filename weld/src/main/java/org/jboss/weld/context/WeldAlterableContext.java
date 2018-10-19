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
package org.jboss.weld.context;

import java.util.Collection;

import javax.enterprise.context.spi.AlterableContext;

import org.jboss.weld.context.api.ContextualInstance;

/**
 * Enriched version of {@link AlterableContext} which allows to capture all instances from given context or to set them to
 * previously obtained values.
 *
 * Most built-in contexts implement this in order to support context propagation. Exception are {@link ApplicationContext} which
 * works out of the box and then {@link SingletonContext} and {@link DependentContext} which are not to be propagated.
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
public interface WeldAlterableContext extends AlterableContext {

    /**
     * Retrieves set of {@link ContextualInstance} within the context. This entails all instances that were created up to this point - Weld creates
     * them lazily so unless some beans were already used, they have not been stored.
     *
     * @return Set of all {@link ContextualInstance} existing in this context
     */
    default <T> Collection<ContextualInstance<T>> getAllContextualInstances() {
        throw new UnsupportedOperationException("getAll() is not implemented for context " + this.getClass());
    }

    /**
     * Clears the backing bean store and feeds it with the set of {@link ContextualInstance} provided as parameter.
     * All {@link ContextualInstance} have to belong to the same scope as does this {@link WeldAlterableContext} otherwise
     * {@code IllegalArgumentException} is thrown.
     *
     * @param setOfInstances set of {@link ContextualInstance} which are to become the new bean store for this context
     * @throws IllegalArgumentException if {@link ContextualInstance}s belong to different scope than this context
     */
    default <T> void clearAndSet(Collection<ContextualInstance<T>> setOfInstances) {
        throw new UnsupportedOperationException("reset() is not implemented for context " + this.getClass());
    }
}
