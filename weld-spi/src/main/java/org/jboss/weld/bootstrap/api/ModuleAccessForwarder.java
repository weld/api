/*
 * JBoss, Home of Professional Open Source
 * Copyright 2026, Red Hat, Inc., and individual contributors
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
 * Callback for forwarding JPMS module access from an entry-point module
 * (SE or Servlet) to the core module. The entry-point module creates a
 * lambda that calls {@link Module#addOpens(String, Module)}, and core
 * invokes it lazily before reflective access to user bean packages.
 *
 * <p>
 * The callback must be defined (as a lambda or method reference) in the
 * entry-point module so that the JVM's stack-walking check for
 * {@code addOpens} sees the correct caller module.
 * </p>
 */
@FunctionalInterface
public interface ModuleAccessForwarder {

    /**
     * Forward access from the source module's package to the target module.
     *
     * @param sourceModule the module that owns the package to be opened
     * @param packageName the package name to open
     * @param targetModule the module that needs access
     */
    void forwardAccess(Module sourceModule, String packageName, Module targetModule);
}
