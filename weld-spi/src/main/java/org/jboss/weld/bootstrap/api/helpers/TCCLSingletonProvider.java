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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.weld.bootstrap.api.Singleton;
import org.jboss.weld.bootstrap.api.SingletonProvider;

/**
 * Singleton provider that uses the Thread Context ClassLoader to differentiate between applications
 *
 * @author Sanjeeb.Sahoo@Sun.COM
 * @author Pete Muir
 */
public class TCCLSingletonProvider extends SingletonProvider {

    @Override
    public <T> Singleton<T> create(Class<? extends T> type) {
        return new TCCLSingleton<T>();
    }

    private static class TCCLSingleton<T> implements Singleton<T> {

        private final Map<ClassLoader, T> store = new ConcurrentHashMap<ClassLoader, T>();

        public T get(String id) {
            T instance = store.get(getClassLoader());
            if (instance == null) {
                throw new IllegalStateException("Singleton not set for " + getClassLoader());
            }
            return instance;
        }

        public void set(String id, T object) {
            store.put(getClassLoader(), object);
        }

        public void clear(String id) {
            store.remove(getClassLoader());
        }

        public boolean isSet(String id) {
            return store.containsKey(getClassLoader());
        }

        private ClassLoader getClassLoader() {
            return Thread.currentThread().getContextClassLoader();
        }
    }
}
