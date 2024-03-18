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

package org.jboss.weld.resources.spi;

import java.net.URL;
import java.util.Collection;

import org.jboss.weld.bootstrap.api.Service;

/**
 * Resource loading/class creation services for Weld. By default an implementation which uses the Thread Context ClassLoader if
 * available, otherwise the classloading of the implementation is used. An alternative implementation that uses a predefined
 * classloader is available for multi-modular environments.
 *
 * The {@link ResourceLoader} is a per-BeanManager service. Single-module deployments can use the default implementation, but
 * applications that consist of multiple modules must use an implementation that is aware of the module classloader.
 *
 * @author Pete Muir
 *
 */
public interface ResourceLoader extends Service {
    /**
     * Name of the resource loader
     */
    String PROPERTY_NAME = ResourceLoader.class.getName();

    /**
     * Creates a class from a given FQCN
     *
     * @param name The name of the clsas
     * @return The class
     */
    Class<?> classForName(String name);

    /**
     * Gets a resource as a URL by name
     *
     * @param name The name of the resource
     * @return An URL to the resource
     */
    URL getResource(String name);

    /**
     * Gets resources as URLs by name
     *
     * @param name The name of the resource
     * @return references to the URLS
     */
    Collection<URL> getResources(String name);

}
