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

import java.security.ProtectionDomain;

import org.jboss.weld.bootstrap.api.Service;

/**
 * <p>Support services related to proxy generation and serialization which are required
 * to be implemented by all containers.</p>
 * 
 * <p>
 * These services are used by all Weld proxy classes to ensure the correct class loaders
 * are used and to aid in the serialization and deserialization of these classes across
 * container instances.
 * </p>
 * 
 * <p>
 * Required in all environments since proxies are always in use.  A default
 * implementation will be used if none are provided by the container which
 * will use the information directly from the type of proxy.
 * </p>
 * 
 * <p> {@link ProxyServices} is a per-deployment service.</p>
 * 
 * @author David Allen
 *
 */
public interface ProxyServices extends Service
{
   /**
    * Returns the class loader that will load the proxy class which extends or
    * implements the given type.  This class loader may simply be the same
    * class loader used for the type, or it may be another class loader designed
    * to hold proxies while still providing access to the given type and any of its
    * ancestors and used types.
    * 
    * @param type the super type (class or interface) of the proxy
    * @return the class loader to use for the proxy class
    */
   public ClassLoader getClassLoader(Class<?> type);
   
   /**
    * Returns the protection domain to use when a security manager is present
    * during generation of a proxy class with the given super type.  Usually
    * this protection domain will need to correspond to the one used with
    * the class loader provided by {@link #getClassLoader(Class)}.
    * 
    * @param type the super type (class or interface) of the proxy
    * @return the protection domain to use for the proxy class
    */
   public ProtectionDomain getProtectionDomain(Class<?> type);
}
