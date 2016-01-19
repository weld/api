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
package org.jboss.weld.bootstrap.spi;

import java.util.Collection;
import java.util.Collections;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.ejb.spi.EjbDescriptor;

/**
 * Represents a CDI bean deployment archive.
 *
 * A deployment archive is any library jar, library directory, EJB jar, rar archive or any war WEB-INF/classes directory
 * contained in the Java EE deployment (as defined in the Java Platform, Enterprise Edition (Java EE) Specification, v6, Section
 * 8.1.2).
 *
 * TODO Java SE definition of a deployment archive
 *
 * A bean deployment archive is any deployment archive with a META-INF/beans.xml file, or for a war, with a WEB-INF/beans.xml.
 *
 * The container is allowed to specify a deployment archive as {@link BeanDeploymentArchive} even if no beans.xml is present
 * (for example, a container could define a deployment archive with container specific metadata to be a bean deployment
 * archive).
 *
 * @see Deployment
 *
 * @author Pete Muir
 *
 */
public interface BeanDeploymentArchive {

    /**
     * Get the bean deployment archives which are accessible to this bean deployment archive and adjacent to it in the
     * deployment archive graph.
     *
     * Cycles in the accessible BeanDeploymentArchive graph are allowed. If a cycle is detected by Weld, it will be
     * automatically removed by Web Beans. This means any implementor of this interface don't need to worry about circularities.
     *
     * @return the accessible bean deployment archives
     */
    Collection<BeanDeploymentArchive> getBeanDeploymentArchives();

    /**
     * Gets all classes in the bean deployment archive.
     *
     * <p>
     * For an explicit bean archive this method returns a collection of all types present within the archive.
     * </p>
     *
     * <p>
     * For an implicit bean archive this method returns all the types found in the archive which are annotated with a bean defining annotation or are Session
     * bean definitions.
     * </p>
     *
     * @return the classes, empty if no classes are present
     */
    Collection<String> getBeanClasses();

    /**
     * Get all pre-loaded classes for the bean deployment archive.
     *
     * Weld checks if there is an overlap of FQCNs between the classes returned from this method and class names returned from {@link #getBeanClasses()}.
     * If two names overlap the class object returned from this method is used.
     *
     * @return the classes, empty if no pre-loaded classes are available
     */
    default Collection<Class<?>> getLoadedBeanClasses() {
        return Collections.emptySet();
    }

    /**
     * If possible, return all the classes found in the archive. For explicit bean archive the result of this method should be the same as for
     * {@link #getBeanClasses()}. For implicit bean archive this method should also return types which are neither annotated with bean defining annotations nor
     * are Session bean definitions.
     *
     * @return all classes found in the bean deployment archive, empty if no classes are present
     */
    default Collection<String> getKnownClasses() {
        return getBeanClasses();
    }

    /**
     * Get any deployment descriptors in the bean deployment archive.
     *
     * The container will return a a merged view of the beans.xml per bean deployment archive. This will normally represent a
     * single file such as the physical META-INF/beans.xml or WEB-INF/beans.xml)
     *
     * The container may choose to parse beans.xml itself, or it may use Weld to parse beans.xml
     *
     * @return the parsed beans.xml
     * @see org.jboss.weld.bootstrap.api.Bootstrap#parse(java.net.URL)
     * @see org.jboss.weld.bootstrap.api.Bootstrap#parse(Iterable)
     */
    BeansXml getBeansXml();

    /**
     * Get all the EJBs in the deployment archive
     *
     * @return the EJBs, or empty if no EJBs are present or if this is not an EJB archive
     */
    Collection<EjbDescriptor<?>> getEjbs();

    /**
     * Get the Bean Deployment Archive scoped services
     *
     * @return bean deployment archive scoped services
     */
    ServiceRegistry getServices();

    /**
     * Get a string which uniquely identifies the {@link BeanDeploymentArchive} within the {@link Deployment}. The identifier
     * must be consistent between multiple occurrences of this deployment.
     *
     * @return identifier of BeanDeploymentArchive
     */
    String getId();
}
