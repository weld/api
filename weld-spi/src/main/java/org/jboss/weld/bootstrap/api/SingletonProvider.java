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

package org.jboss.weld.bootstrap.api;

import org.jboss.weld.bootstrap.api.helpers.IsolatedStaticSingletonProvider;
import org.jboss.weld.bootstrap.api.helpers.RegistrySingletonProvider;
import org.jboss.weld.bootstrap.api.helpers.TCCLSingletonProvider;

/**
 * A provider of {@link Singleton}s
 *
 * @see IsolatedStaticSingletonProvider
 * @see TCCLSingletonProvider
 * @see RegistrySingletonProvider
 *
 * @author Sanjeeb.Sahoo@Sun.COM
 * @author Pete Muir
 */
public abstract class SingletonProvider {
    /*
     * Singleton pattern. Upon first access (see instance()), it initializes itself by a default implementation. Containers are
     * free to explicitly initialize it by calling initialize() method.
     */
    private static volatile SingletonProvider INSTANCE;

    private static final String DEFAULT_SCOPE_FACTORY = RegistrySingletonProvider.class.getName();

    /**
     * Returns a singleton instance of this class.
     *
     * @return {@link SingletonProvider} instance
     */
    public static SingletonProvider instance() {
        if (INSTANCE == null) {
            synchronized (SingletonProvider.class) {
                if (INSTANCE == null) {
                    initializeWithDefaultScope();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Protected constructor.
     * {@link SingletonProvider#instance()} should be used to obtain an instance of this class.
     */
    protected SingletonProvider() {
    }

    /**
     * Create a new singleton
     *
     * @param <T> type of Java object stored in the singleton
     * @param expectedType represents the type of Java object stored in the singleton
     * @return a singelton
     */
    public abstract <T> Singleton<T> create(Class<? extends T> expectedType);

    /**
     * Initialize with the default instance
     */
    private static void initializeWithDefaultScope() {
        try {
            Class<?> aClass = Class.forName(DEFAULT_SCOPE_FACTORY);
            INSTANCE = (SingletonProvider) aClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize with an explicit instance
     *
     * @param instance the explicit instance
     */
    public static void initialize(SingletonProvider instance) {
        synchronized (SingletonProvider.class) {
            if (INSTANCE == null) {
                INSTANCE = instance;
            } else {
                throw new RuntimeException("SingletonProvider is already initialized with " + INSTANCE);
            }
        }
    }

    /**
     * Sets the reference to the singleton instance of this class back to {@code null}.
     */
    public static void reset() {
        INSTANCE = null;
    }

}
