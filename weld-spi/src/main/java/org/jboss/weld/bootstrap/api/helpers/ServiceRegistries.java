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

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.ServiceRegistry;

public class ServiceRegistries
{
   
   private ServiceRegistries() {}
   
   public static ServiceRegistry unmodifiableServiceRegistry(final ServiceRegistry serviceRegistry)
   {
      return new ForwardingServiceRegistry()
      {
         
         public <S extends Service> void add(java.lang.Class<S> type, S service) 
         {
            throw new UnsupportedOperationException("This service registry is unmodifiable");
         }
         
         @Override
         protected ServiceRegistry delegate()
         {
            return serviceRegistry;
         }
         
      };
   }
   
}
