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

import org.jboss.weld.bootstrap.api.Bootstrap;
import org.jboss.weld.bootstrap.api.Environments;
import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.bootstrap.api.helpers.SimpleServiceRegistry;
import org.jboss.weld.bootstrap.api.test.MockDeployment.MockBeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.ejb.spi.EjbServices;
import org.jboss.weld.injection.spi.EjbInjectionServices;
import org.jboss.weld.injection.spi.JpaInjectionServices;
import org.jboss.weld.injection.spi.ResourceInjectionServices;
import org.jboss.weld.resources.spi.ResourceLoader;
import org.jboss.weld.security.spi.SecurityServices;
import org.jboss.weld.transaction.spi.TransactionServices;
import org.testng.annotations.Test;

public class BootstrapTest
{

  /*
   * EjbServices is a required bean deployment archive service in this environment.
   * For backwards compatibility with older integrators that register EjbServices
   * as a deployment service, this check is suppressed.
   *
   * @see WELD-1685
   */
   @Test(expectedExceptions = IllegalStateException.class, enabled = false)
   public void testMissingEjbServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());

      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void testMissingEjbInjectionServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());

      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void testMissingJpaServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());

      ServiceRegistry bdaServices = new SimpleServiceRegistry();

      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void testMissingSecurityServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());

      ServiceRegistry bdaServices = new SimpleServiceRegistry();

      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test // no exception should be thrown as ValidationServices are no longer required
   public void testMissingValidationServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());

      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test
   public void testEEEnv()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());


      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void testMissingTxServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());

      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceInjectionServices.class, new MockResourceServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void testMissingResourceServices()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();
      deploymentServices.add(TransactionServices.class, new MockTransactionServices());
      deploymentServices.add(SecurityServices.class, new MockSecurityServices());
      deploymentServices.add(EjbServices.class, new MockEjbServices());

      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      bdaServices.add(EjbInjectionServices.class, new MockEjbInjectionServices());
      bdaServices.add(JpaInjectionServices.class, new MockJpaServices());
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());

      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.EE_INJECT, deployment);
   }

   @Test
   public void testSEEnv()
   {
      Bootstrap bootstrap = new MockBootstrap();
      ServiceRegistry deploymentServices = new SimpleServiceRegistry();

      ServiceRegistry bdaServices = new SimpleServiceRegistry();
      bdaServices.add(ResourceLoader.class, new MockResourceLoader());
      Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
      bootstrap.startContainer(Environments.SE, deployment);
   }

   @Test
      public void testServletEnv()
      {
         Bootstrap bootstrap = new MockBootstrap();
         ServiceRegistry deploymentServices = new SimpleServiceRegistry();
         ServiceRegistry bdaServices = new SimpleServiceRegistry();
         bdaServices.add(ResourceLoader.class, new MockResourceLoader());
         Deployment deployment = new MockDeployment(deploymentServices, new MockBeanDeploymentArchive(bdaServices));
         bootstrap.startContainer(Environments.SERVLET, deployment);
      }


}
