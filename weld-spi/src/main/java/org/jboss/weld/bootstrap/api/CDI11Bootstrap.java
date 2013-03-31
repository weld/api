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
     * <p>
     * The container fires the {@link BeforeBeanDiscovery} event which allows extensions to register scopes. The container
     * combines the registered scopes with scopes associated with the built-in contexts and makes the resulting set available
     * through {@link TypeDiscoveryConfiguration#getKnownBeanDefiningAnnotations()}
     * </p>
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
     * <p>
     * If an archive:
     * </p>
     * <ul>
     * <li>contains the <code>beans.xml</code> file and the file does not contain the <code>bean-discovery-mode</code>
     * attribute, or</li>
     * <li>contains the <code>beans.xml</code> file and the value of the <code>bean-discovery-mode</code> attribute is set to
     * <code>all</code></li>
     * </ul>
     * <p>
     * this archive is an <em>explicit bean archive</em>.
     * </p>
     *
     *
     * <p>
     * If an archive:
     * </p>
     * <ul>
     * <li>contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>annotated</code>, or</li>
     * <li>does not contain the <code>beans.xml</code> file nor any implementation of the {@link Extension} interface but the
     * archive contains types annotated with a bean defining annotation or session beans</li>
     * </ul>
     * <p>
     * this archive is an <em>implicit bean archive</em>.
     * </p>
     *
     *
     * <p>
     * If an archive:
     * </p>
     * <ul>
     * <li>contains the <code>beans.xml</code> file and the <code>bean-discovery-mode</code> attribute is set to
     * <code>none</code>, or</li>
     * <li>does not contain the <code>beans.xml</code> file and contains an implementation of the {@link Extension} interface,
     * or</li>
     * <li>does not contain the <code>beans.xml</code> file and does not contain any types annotated with a bean defining
     * annotation nor session bean</li>
     * </ul>
     * <p>
     * this archive is not a bean archive.
     * </p>
     *
     * <p>
     * For each explicit bean archive the integrator creates an instance of {@link BeanDeploymentArchive} representing this
     * archive. The {@link BeanDeploymentArchive#getBeanClasses()} method returns a collection of all types present within the
     * archive. Filtering rules defined in {@link BeansXml#getScanning()} are not required to be applied by the integrator and
     * are applied later on by Weld. The {@link BeanDeploymentArchive#getEjbs()} method returns a collection of EJB descriptors
     * for EJBs present in the archive.
     * </p>
     *
     * <p>
     * For each implicit bean archive the integrator creates an instance of {@link BeanDeploymentArchive} representing this
     * archive. The {@link BeanDeploymentArchive#getBeanClasses()} of the bean archive returns all the types found in the
     * archive which are annotated with a bean defining annotations or are Session bean definitions. Filtering rules (
     * {@link BeansXml#getScanning()}) are not required to be applied by the integrator. The
     * {@link BeanDeploymentArchive#getEjbs()} method returns a collection of EJB descriptors for Session beans present in the
     * archive.
     * </p>
     *
     * <p>
     * Initially, the integrator does not need to create a {@link BeanDeploymentArchive} instance for an archive which is not a
     * bean archive. Note that although these archives are ignored when building the initial collection of bean archives, the
     * integrator may be required to create a {@link BeanDeploymentArchive} later upon a call to
     * {@link Deployment#loadBeanDeploymentArchive(Class)}. For example, when the method is called for a extension class that is
     * deployed in an archive that is not a bean archive.
     * </p>
     *
     * @param extensions discovered CDI extensions
     * @return
     */
    TypeDiscoveryConfiguration startExtensions(Iterable<Metadata<Extension>> extensions);
}