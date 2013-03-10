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

import java.lang.annotation.Annotation;
import java.util.Set;

import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.Deployment;

/**
 * {@link TypeDiscoveryConfiguration} is used by an integrator to determine which classes to discover during the type discovery
 * phase.
 *
 * @author Jozef Hartinger
 *
 */
public interface TypeDiscoveryConfiguration {

    /**
     * Returns a set of bean defining annotations. This set is used in combination with bean defining annotations discovered by
     * the integrator for discovering beans. See {@link Bootstrap#startExtensions(Iterable)} for details.
     */
    Set<Class<? extends Annotation>> getKnownBeanDefiningAnnotations();

    /**
     * <p>
     * Returns a set of marker annotations. Each Java class, interface or enum which contains any of the marker annotations and
     * is not part of an explicit {@link BeanDeploymentArchive} nor annotated with a bean defining annotation must be discovered
     * by the integrator and returned from {@link Deployment#getAdditionalTypes()}.
     * </p>
     *
     * <p>
     * The marker annotation can appear on the annotated type, or on any member, or any parameter of any member of the annotated
     * type, as defined in Section 11.4. The annotation may be applied as a meta-annotation on any annotation considered.
     * </p>
     *
     * <p>
     * An EE7-compatible integrator is required to search for these additional types in:
     * </p>
     *
     * <ul>
     * <li>Library jars</li>
     * <li>EJB jars or application client jars</li>
     * <li>The WEB-INF/classes directory of a war</li>
     * <li>Directories in the JVM classpath</li>
     * </ul>
     */
    Set<Class<? extends Annotation>> getAdditionalTypeMarkerAnnotations();
}
