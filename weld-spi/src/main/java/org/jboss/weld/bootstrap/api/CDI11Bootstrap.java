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
import org.jboss.weld.bootstrap.spi.CDI11BeanDeploymentArchive;
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
     * set of annotations which these observer methods require using the {@link WithAnnotations} annotation. This final set is
     * available through {@link TypeDiscoveryConfiguration#getAdditionalTypeMarkerAnnotations()}</li> and referred to as
     * <em>required annotations</em> hereafter.
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
     * These locations are referred to as <em>available archives</em> hereafter.
     * </p>
     *
     * <p>
     * Firstly, the integrator discovers every Java annotation annotated with {@link Scope} or {@link NormalScope} and combines
     * these annotations with the annotations returned from {@link TypeDiscoveryConfiguration#getKnownBeanDefiningAnnotations()}
     * . The resulting set is referred to as <em>bean defining annotations</em> hereafter.
     * </p>
     *
     * <p>
     * Secondly, the integrator processes available archives according to these rules. The rules are exclusive.
     * </p>
     *
     * <ol>
     * <li>If the archive contains the <code>beans.xml</code> file and the file either does not contain the
     * <code>bean-discovery-mode</code> attribute or its value is set to <code>all</code>, this archive is an <em>explicit bean
     * archive</em>. For each explicit bean archive the integrator creates an instance of {@link BeanDeploymentArchive}
     * representing this archive. The {@link BeanDeploymentArchive#getBeanClasses()} method returns a collection of all types
     * present within the archive. Filtering rules defined in {@link BeansXml#getScanning()} are not required to be applied by
     * the integrator and are applied later on by Weld. The {@link BeanDeploymentArchive#getEjbs()} method returns a collection
     * of EJB descriptors for EJBs present in the archive. The {@link CDI11BeanDeploymentArchive#getAdditionalTypes()} method returns
     * an empty collection for an explicit bean archive.</li>
     *
     * <li>If the archive contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>annotated</code> or if the archive does not contain the <code>beans.xml</code> file but the archive contains types
     * annotated with a bean defining annotation or session beans or types annotated with a required annotation, this archive is
     * an <em>implicit bean archive</em>. For each implicit bean archive the integrator creates an instance of
     * {@link BeanDeploymentArchive} representing this archive. The {@link BeanDeploymentArchive#getBeanClasses()} of the bean
     * archive returns all the types found in the archive which are annotated with a bean defining annotations or are Session
     * bean definitions. Filtering rules ({@link BeansXml#getScanning()}) are not required to be applied by the integrator. The
     * {@link BeanDeploymentArchive#getEjbs()} method returns a collection of EJB descriptors for Session beans present in the
     * archive. The {@link CDI11BeanDeploymentArchive#getAdditionalTypes()} method returns a collection of types present in the
     * archive which are not contained within {@link BeanDeploymentArchive#getBeanClasses()} (are not annotated with a
     * bean-defining annotation nor define a Sesion bean) but contain a required annotation.</li>
     *
     * <li>If the archive does not contain the <code>beans.xml</code> file and does not contain a type annotated with a bean
     * defining annotation and does not contain a type containing a required annotation, this archive is not a bean archive and
     * the integrator does not need to create a {@link BeanDeploymentArchive} instance for this archive.</li>
     *
     * <li>If the archive contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>none</code>, the archive is not a bean archive. The integrator does not need to create a
     * {@link BeanDeploymentArchive} instance for this archive.</li>
     * </ol>
     *
     * @param extensions discovered CDI extensions
     * @return
     */
    TypeDiscoveryConfiguration startExtensions(Iterable<Metadata<Extension>> extensions);
}