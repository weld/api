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

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.jboss.weld.bootstrap.spi.BeansXml.EMPTY_BEANS_XML;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.spi.Extension;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.BeansXml;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.bootstrap.spi.Metadata;
import org.jboss.weld.ejb.spi.EjbDescriptor;

/**
 * @author pmuir
 *
 */
public class MockDeployment implements Deployment {

    static class MockBeanDeploymentArchive implements BeanDeploymentArchive {

        private final ServiceRegistry services;

        public MockBeanDeploymentArchive(ServiceRegistry services) {
            this.services = services;
        }

        public Collection<String> getBeanClasses() {
            return emptySet();
        }

        public Collection<BeanDeploymentArchive> getBeanDeploymentArchives() {
            return emptySet();
        }

        public BeansXml getBeansXml() {
            return EMPTY_BEANS_XML;
        }

        public Collection<EjbDescriptor<?>> getEjbs() {
            return emptySet();
        }

        public ServiceRegistry getServices() {
            return services;
        }

        public String getId() {
            return "test";
        }

        @Override
        public Collection<String> getAdditionalTypes() {
            return emptySet();
        }
    }

    private final ServiceRegistry services;
    private final BeanDeploymentArchive beanDeploymentArchive;

    public MockDeployment(ServiceRegistry services, MockBeanDeploymentArchive beanDeploymentArchive) {
        this.services = services;
        this.beanDeploymentArchive = beanDeploymentArchive;
    }

    public List<BeanDeploymentArchive> getBeanDeploymentArchives() {
        return Collections.singletonList(beanDeploymentArchive);
    }

    public BeanDeploymentArchive loadBeanDeploymentArchive(Class<?> beanClass) {
        return null;
    }

    public ServiceRegistry getServices() {
        return services;
    }

    public Iterable<Metadata<Extension>> getExtensions() {
        return emptyList();
    }

}
