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
package org.jboss.weld.bootstrap.api.helpers;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.ServiceRegistry;

public abstract class ForwardingServiceRegistry implements ServiceRegistry
{
   
   protected abstract ServiceRegistry delegate();
   
   public <S extends Service> void add(Class<S> type, S service)
   {
      delegate().add(type, service);
   }
   
   public <S extends Service> boolean contains(Class<S> type)
   {
      return delegate().contains(type);
   }
   
   public <S extends Service> S get(Class<S> type)
   {
      return delegate().get(type);
   }
   
   public Iterator<Service> iterator()
   {
      return delegate().iterator();
   }

   public void addAll(Collection<Entry<Class<? extends Service>, Service>> services)
   {
      delegate().addAll(services);
   }

   public Set<Entry<Class<? extends Service>, Service>> entrySet()
   {
      return delegate().entrySet();
   }
   
   public void cleanup()
   {
      delegate().cleanup();
   }
   
}
