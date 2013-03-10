/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

import javax.enterprise.context.NormalScope;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import javax.inject.Scope;

import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.BeansXml;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.bootstrap.spi.Metadata;

/**
 * An extension to {@link Bootstrap} which allows weld to perform type discovery as required by CDI 1.1. Each EE7-compatible
 * integrator should use this interface.
 *
 * Application container initialization API for Weld.
 *
 * To initialize the container you must call, in this order:
 *
 * <ol>
 * <li>{@link #startExtensions()}</li>
 * <li>{@link #startContainer()}</li>
 * <li>{@link #startInitialization()}</li>
 * <li>{@link #deployBeans()}</li>
 * <li>{@link #validateBeans()}</li>
 * <li>{@link #endInitialization()}</li>
 * </ol>
 *
 * To stop the container and clean up, you must call {@link #shutdown()}
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 */
public interface CDI11Bootstrap extends Bootstrap {

    /**
     * <p>
     * The container bootstrap sequence begins with initializing extensions. The container performs the following actions:
     * </p>
     *
     * <ol>
     * <li>The container discovers {@link ProcessAnnotatedType} observer methods defined on the extensions and enumerates the
     * set of annotations which these observer methods require using {@link WithAnnotations}. This final set is available
     * through {@link TypeDiscoveryConfiguration#getAdditionalTypeMarkerAnnotations()}</li> and referred to as
     * "required annotations" hereafter.
     * <li>The container fires the {@link BeforeBeanDiscovery} event which allows extensions to register scopes. The container
     * combines the registered scopes with scopes associated with the built-in contexts and makes the resulting set available
     * through {@link TypeDiscoveryConfiguration#getKnownBeanDefiningAnnotations()}</li>
     * </ol>
     *
     * <p>
     * Afterwards, an EE7-compatible integrator performs type discovery in the following locations:
     * </p>
     *
     * <ul>
     * <li>Library jars</li>
     * <li>EJB jars or application client jars</li>
     * <li>The WEB-INF/classes directory of a war</li>
     * <li>Directories in the JVM classpath</li>
     * </ul>
     *
     * <p>
     * These locations are referred to as "available archives" hereafter.
     * </p>
     *
     * <p>
     * Firstly, the integrator discovers every Java annotation annotated with {@link Scope} or {@link NormalScope} and combines
     * these annotations with the annotations returned from {@link TypeDiscoveryConfiguration#getKnownBeanDefiningAnnotations()}
     * . The resulting set is referred to as "bean defining annotations" hereafter.
     * </p>
     *
     * <p>
     * Secondly, the integrator processes available archives according to these rules. The rules are exclusive.
     * </p>
     *
     * <ol>
     * <li>If the archive contains the <code>beans.xml</code> file and the file either does not contain the
     * <code>bean-discovery-mode</code> attribute or its value is set to <code>all</code>, this archive is an explicit bean
     * archive. For each explicit bean archive the integrator creates an instance of {@link BeanDeploymentArchive} representing
     * this archive and returns the instance within {@link Deployment#getBeanDeploymentArchives()}.
     * {@link BeanDeploymentArchive#getBeanClasses()} of the bean archive returns all the types found in the archive. Filtering
     * rules ({@link BeansXml#getScanning()}) are not required to be applied by the integrator. None of the types contained
     * within an explicit bean archive is returned from {@link Deployment#getAdditionalTypes()}.</li>
     *
     * <li>If the archive contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>annotated</code> or if the archive does not contain the <code>beans.xml</code> file but contains types annotated
     * with bean defining annotations, this archive is an implicit bean archive. For each implicit bean archive the integrator
     * creates an instance of {@link BeanDeploymentArchive} representing this archive and returns the instance within
     * {@link Deployment#getBeanDeploymentArchives()}. {@link BeanDeploymentArchive#getBeanClasses()} of the bean archive
     * returns all the types found in the archive which are annotated with any of the bean defining annotations. Each type that
     * is not annotated with a bean defining annotation and contains a required annotation is never returned from
     * {@link BeanDeploymentArchive#getBeanClasses()} but is contained within {@link Deployment#getAdditionalTypes()}. Filtering
     * rules ({@link BeansXml#getScanning()}) are not required to be applied by the integrator.</li>
     *
     * <li>If the archive does not contain the <code>beans.xml</code> file nor any type annotated with a bean defining
     * annotation, the archive is not a bean archive. However, if the archive contains a type that contains a required
     * annotation, this type is contained within {@link Deployment#getAdditionalTypes()}.</li>
     *
     * <li>If the archive contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>none</code>, the archive is not a bean archive. None of the types contained within the archive are contained within
     * {@link Deployment#getAdditionalTypes()} (not event types containing a required annotation).</li>
     * </ol>
     *
     * @param extensions
     * @return
     */
    TypeDiscoveryConfiguration startExtensions(Iterable<Metadata<Extension>> extensions);
}