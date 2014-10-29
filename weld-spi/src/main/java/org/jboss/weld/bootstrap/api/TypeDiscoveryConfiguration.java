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
     * the integrator for discovering beans. See {@link CDI11Bootstrap#startExtensions(Iterable)} for details.
     *
     * @return the set of bean defining annotations
     */
    Set<Class<? extends Annotation>> getKnownBeanDefiningAnnotations();
}
