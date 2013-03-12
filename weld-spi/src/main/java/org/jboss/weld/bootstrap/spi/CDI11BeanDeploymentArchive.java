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
package org.jboss.weld.bootstrap.spi;

import java.util.Collection;

import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

import org.jboss.weld.bootstrap.api.Bootstrap;

public interface CDI11BeanDeploymentArchive extends BeanDeploymentArchive {

    /**
     * Types which are not discovered by the container as defined in the CDI specification but are required because they match a
     * {@link ProcessAnnotatedType} observer method that requires certain annotations expressed by applying the
     * {@link WithAnnotations} annotation on the observed type.
     *
     * @see Bootstrap#startExtensions(Iterable) for more details.
     */
    Collection<String> getAdditionalTypes();
}
