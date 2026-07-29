/*
 * Copyright The Weld Authors
 * SPDX-License-Identifier: Apache-2.0
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
