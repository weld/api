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
package org.jboss.weld.bootstrap.api.test;

import java.util.Set;

import org.jboss.weld.bootstrap.api.Bootstrap;
import org.jboss.weld.bootstrap.api.Environment;
import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.context.api.BeanStore;
import org.jboss.weld.manager.api.WeldManager;

public class MockBootstrap implements Bootstrap
{
   
   public WeldManager getManager(BeanDeploymentArchive beanDeploymentArchive)
   {
      return null;
   }
   
   public void shutdown()
   {
   }

   public Bootstrap deployBeans()
   {
      return this;
   }

   public Bootstrap endInitialization()
   {
      return this;
   }

   public Bootstrap startInitialization()
   {
      return this;
   }

   public Bootstrap validateBeans()
   {
      return this;
   }
   
   protected static void verifyServices(ServiceRegistry services, Set<Class<? extends Service>> requiredServices) 
   {
      for (Class<? extends Service> serviceType : requiredServices)
      {
         if (!services.contains(serviceType))
         {
            throw new IllegalStateException("Required service " + serviceType.getName() + " has not been specified");
         }
      }
   }

   public Bootstrap startContainer(Environment environment, Deployment deployment, BeanStore beanStore)
   {
      verifyServices(deployment.getServices(), environment.getRequiredDeploymentServices());
      verifyServices(deployment.getBeanDeploymentArchives().iterator().next().getServices(), environment.getRequiredBeanDeploymentArchiveServices());
      return this;
   }

   
}
