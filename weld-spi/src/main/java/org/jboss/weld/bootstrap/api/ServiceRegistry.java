/*
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

import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;

/**
 * A service registry
 * 
 * @author Pete Muir
 *
 */
public interface ServiceRegistry extends Iterable<Service>
{
   
   /**
    * Add a service
    * 
    * @see Service
    * 
    * @param <S> the service type to add
    * @param serviceType the service type to add
    * @param service the service implementation
    */
   public <S extends Service> void add(Class<S> type, S service);
   
   /**
    * Add services
    * 
    * @param services
    */
   public void addAll(Collection<Entry<Class<? extends Service>, Service>> services);
   
   public Set<Entry<Class<? extends Service>, Service>> entrySet();
   
   /**
    * Retrieve a service implementation
    * 
    * @param <S> the service type
    * @param serviceType the service type
    * @return the service implementation, or null if none is registered
    */
   public <S extends Service> S get(Class<S> type);
   
   /**
    * Check if a service is registered
    * 
    * @param <S> the service type
    * @param serviceType the service type
    * @return true if a service is registered, otherwise false
    */
   public <S extends Service> boolean contains(Class<S> type);
   
   /**
    * Clear up the services registered, by calling {@link Service#cleanup()} on
    * each registered service
    */
   public void cleanup();
   
   
   
}