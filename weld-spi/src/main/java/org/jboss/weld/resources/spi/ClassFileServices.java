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
package org.jboss.weld.resources.spi;

import org.jboss.weld.bootstrap.api.BootstrapService;

/**
 * An optional service that provides fast access to Java class metadata without the need to load the given class.
 *
 * An integrator may use this service to expose its bytecode scanning facility. If a service implementation is present, Weld uses it to optimize the bootstrap
 * process. The ClassFileServices implementation is used to determine whether a given Java class fulfills CDI bean requirements instead of loading the class
 * using a {@link ClassLoader} and examining the result using Java reflection.
 *
 * @author Jozef Hartinger
 *
 */
public interface ClassFileServices extends BootstrapService {

    /**
     * Obtains Java class metadata for a class identified with the specified class name.
     *
     * @param className the specified class name
     * @return the class metadata
     * @throws ClassFileInfoException if the service implementation is not able to obtain metadata of a class with the specified name
     */
    ClassFileInfo getClassFileInfo(String className);
}
