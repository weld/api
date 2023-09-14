/*
 * JBoss, Home of Professional Open Source
 * Copyright 2019, Red Hat, Inc., and individual contributors
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

package org.jboss.weld.bootstrap.api.helpers;

import org.jboss.weld.bootstrap.api.Singleton;
import org.jboss.weld.bootstrap.api.SingletonProvider;

/**
 *
 * A singleton provider that assumes an isolated classloder per application
 *
 * @author Sanjeeb.Sahoo@Sun.COM
 * @author Pete Muir
 */
public class IsolatedStaticSingletonProvider extends SingletonProvider {

    @Override
    public <T> Singleton<T> create(Class<? extends T> type) {
        return new IsolatedStaticSingleton<T>();
    }

    private static class IsolatedStaticSingleton<T> implements Singleton<T> {
        private T object;

        public T get(String id) {
            if (object == null) {
                throw new IllegalStateException(
                        "Singleton is not set. Is your Thread.currentThread().getContextClassLoader() set correctly?");
            }
            return object;
        }

        public void set(String id, T object) {
            this.object = object;
        }

        public void clear(String id) {
            this.object = null;
        }

        public boolean isSet(String id) {
            return object != null;
        }
    }
}
