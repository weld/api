/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

/**
 * Holds a reference to an application singleton. This singleton is used internally by Weld to store various application scoped objects.
 *
 * This allows Weld to operate as a shared library. In a shared mode, the same instance of Weld implementation is used by all the applications in a server
 * environment. In the exclusive mode, each application loads a separate copy of Weld implementation at the application level.
 *
 * Alternative implementations of Singleton can be used as required
 *
 * @see SingletonProvider
 * @author Sanjeeb.Sahoo@Sun.COM
 * @author Pete Muir
 */
public interface Singleton<T> {
    /**
     * Access the singleton
     *
     * @param id singleton identifier
     * @return a singleton object
     * @throws IllegalStateException if the singleton is not set
     */
    T get(String id);

    /**
     * Check if the singleton is set
     *
     * @param id singleton identifier
     * @return true if the singleton is set
     */
    boolean isSet(String id);

    /**
     * Store a singleton
     *
     * @param id singleton identifier
     * @param object the object to store
     */
    void set(String id, T object);

    /**
     * Clear the singleton
     *
     * @param id singleton identifier
     */
    void clear(String id);

}
