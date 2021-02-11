/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc. and/or its affiliates, and individual contributors
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

package org.jboss.weld.serialization.spi;

import org.jboss.weld.bootstrap.api.Service;

import java.security.ProtectionDomain;

/**
 * <p>
 * Support services related to proxy generation and serialization which are required to be implemented by all containers.
 * </p>
 * <p>
 * These services are used by all Weld proxy classes to ensure the correct class loaders are used and to aid in the
 * serialization and deserialization of these classes across container instances.
 * </p>
 * <p>
 * Required in all environments since proxies are always in use. If no such service is provided by integrator, a default
 * implementation will be used which will use the information extracted directly from the type of proxy.
 * </p>
 * <p>
 * {@link ProxyServices} is a per-deployment service.
 * </p>
 *
 * @author David Allen
 * @author Matej Novotny
 */
public interface ProxyServices extends Service {
    /**
     * @deprecated {@code defineClass()} methods should be used instead.
     *
     * Returns the class loader that will load the proxy class which extends or implements the given type. This class loader may
     * simply be the same class loader used for the type, or it may be another class loader designed to hold proxies while still
     * providing access to the given type and any of its ancestors and used types.
     *
     * @param proxiedBeanType the base type (class or interface) being proxied
     * @return the class loader to use for the proxy class
     */
    @Deprecated
    ClassLoader getClassLoader(Class<?> proxiedBeanType);

    /**
     * @deprecated {@code loadClass(Class<?>, String)} should be used instead.
     *
     * <p>
     * Loads classes or interfaces extended/implemented by a bean or in particular a proxy class for a bean. This includes
     * application types of the bean as well as Weld types used for proxy classes. Thus the class loader(s) used here must be
     * able to resolve both application classes and Weld implementation classes.
     * </p>
     * <p>
     * This method is only called during deserialization of a proxy object. It does not necessarily need to use the same class
     * loader that the proxy class itself exists in since {@link #getClassLoader(Class)} will still be used to get the correct
     * class loader for the bean type.
     * </p>
     *
     * @param className the class name
     * @return the corresponding Class object
     */
    @Deprecated
    Class<?> loadBeanClass(String className);

    /**
     * Given a base type (class or interface), define a proxy class for this type.
     * Integrators should use the base type to determine a proper {@code ClassLoader} instance to forward creation to.
     *
     * Mimics {@code ClassLoader.defineClass(String name, byte[] b, int off, int len)}.
     *
     * Returns the created class object or throws an exception if there data are not valid or if there is any other
     * problem registering the class with the {@code ClassLoader}.
     *
     * @param originalClass The base type (class or interface) being proxied
     * @param className The binary name of the class
     * @param classBytes The bytes that make up the class data
     * @param off The start offset in classBytes of the class data
     * @param len The length of the class data
     *
     * @return The {@code Class} object created from the data
     *
     * @throws ClassFormatError If the data did not contain a valid class
     */
    default Class<?> defineClass​(Class<?> originalClass, String className, byte[] classBytes, int off, int len) throws ClassFormatError {
        return defineClass​(originalClass, className, classBytes, off, len, null);
    }

    /**
     * Given a base type (class or interface), define a proxy class for this type.
     * Integrators should use the base type to determine a proper {@code ClassLoader} instance to forward creation to.
     *
     * The {@link ProtectionDomain} passed as an argument can be null (see default implementation of the second
     * {@code defineClass()} method). In such a case a new {@link ProtectionDomain} should be derived from the originalClass.
     *
     * Mimics {@code ClassLoader.defineClass(String name, byte[] b, int off, int len, ProtectionDomain domain)}.
     *
     * Returns the created class object or throws an exception if there data are not valid or if there is any other
     * problem registering the class with the {@code ClassLoader}.
     *
     * @param originalClass    The base type (class or interface) being proxied
     * @param className        The binary name of the class
     * @param classBytes       The bytes that make up the class data
     * @param off              The start offset in classBytes of the class data
     * @param len              The length of the class data
     * @param protectionDomain The ProtectionDomain of the class or null
     * @return The {@code Class} object created from the data
     * @throws ClassFormatError If the data did not contain a valid class
     */
    default Class<?> defineClass​(Class<?> originalClass, String className, byte[] classBytes, int off, int len, ProtectionDomain protectionDomain) throws ClassFormatError {
        throw new UnsupportedOperationException("ProxyServices#defineClass(Class<?>, String, byte[], int, int, ProtectionDomain) is not implemented!");
    }

    /**
     * Given a base type (class or interface), attempts to load a proxy of that class.
     * Integrators should use the base type to determine proper {@code ClassLoader} instance to query.
     *
     * @param originalClass   The base type (class or interface) whose proxy we try to load
     * @param classBinaryName The binary name of the class
     * @return The {@code Class} object for given binary name of the class
     * @throws ClassNotFoundException If the class was not found
     */
    default Class<?> loadClass​(Class<?> originalClass, String classBinaryName) throws ClassNotFoundException {
        throw new UnsupportedOperationException("ProxyServices#loadClass(Class<?>, String) is not implemented!");
    }

    /**
     * This method is now deprecated and Weld assumes that all implementations support class defining. Otherwise,
     * an exception will be thrown.
     *
     * Works as a differentiator between the old (deprecated) approach of asking for {@link ClassLoader} and the new
     * one where Weld delegates class loading and creation to the integrator. If this method returns true, then Weld
     * will route all proxy lookup and definition to newly added method. Should it return false, Weld will only
     * use the old {@code ClassLoader} approach.
     *
     * @return true if this implementation implements {@code defineClass()} and {@code loadClass()} methods, false otherwise.
     */
    @Deprecated
    default boolean supportsClassDefining() {
        return false;
    }

}
