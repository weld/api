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
package org.jboss.weld.resources.spi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;

import org.jboss.weld.bootstrap.api.Service;

/**
 * <p>
 * This service allows an integrator to expose an annotation index for a deployment obtained from bytecode scanning. This
 * service is optional. If provided, Weld will use this service to avoid expensive Java Reflection calls.
 * </p>
 *
 * @deprecated This service is deprecated. Implement {@link ClassFileServices} instead.
 *
 * @author Jozef Hartinger
 *
 */
@Deprecated
public interface AnnotationDiscovery extends Service {

    /**
     * <p>
     * Indicates whether the given class contains an annotation of the given annotation type.
     * </p>
     *
     * <p>The process begins with enumerating a set of annotations which consists of:</p>
     *
     * <ul>
     * <li>the given annotation</li>
     * <li>any annotation present in the deployment which on which the given annotation is present as a meta-annotation (as returned by {@link ExtendedAnnotationDiscovery#getAnnotationsAnnotatedWith(Class)})</li>
     * </ul>
     *
     * <p>The set is referred to as <em>M</em> hereafter</p>
     *
     * <p>The given class is said to contain the given annotation if any of these applies:</p>
     *
     * <ul>
     * <li>A member of M is present on the class</li>
     * <li>A member of M, which is annotated with {@link Inherited}, is present on a direct or indirect superclass of the given class</li>
     * <li>A member of M is present on a field or method declared by the given class or any direct or indirect superclass of the given class</li>
     * <li>A member of M is present on a parameter of a method declared by the given class or any direct or indirect superclass of the given class</li>
     * <li>A member of M is present on a constructor declared by the given class</li>
     * <li>A member of M is present on a parameter of a constructor declared by the given class</li>
     * </ul>
     *
     * <p>This is a per-deployment service.</p>
     *
     * @param javaClass the given class
     * @param annotation the given annotation type
     * @return whether the given class contains an annotation of the given annotation type
     */
    boolean containsAnnotation(Class<?> javaClass, Class<? extends Annotation> annotation);
}
