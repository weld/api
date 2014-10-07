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
package org.jboss.weld.bootstrap.api;

import java.net.URL;

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;

import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.BeansXml;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.bootstrap.spi.Metadata;
import org.jboss.weld.manager.api.WeldManager;

/**
 * Application container initialization API for Weld.
 *
 * To initialize the container you must call, in this order:
 *
 * <ol>
 * <li>{@link #startContainer(Environment, Deployment)}</li>
 * <li>{@link #startInitialization()}</li>
 * <li>{@link #deployBeans()}</li>
 * <li>{@link #validateBeans()}</li>
 * <li>{@link #endInitialization()}</li>
 * </ol>
 *
 * To stop the container and clean up, you must call {@link #shutdown()}
 *
 * @author Pete Muir
 *
 */
public interface Bootstrap {

    /**
     * Creates the application container:
     * <ul>
     * <li>Checks that the services required by the environment have been provided</li>
     * <li>Adds container provided services</li>
     * <li>Creates and initializes the built in contexts</li>
     * <li>Creates the manager</li>
     * </ul>
     *
     * context
     *
     * @param environment the environment in use, by default {@link Environments#EE}
     * @param deployment the Deployment to be booted
     * @throws IllegalStateException if not all the services required for the given environment are available
     *
     * @return self
     */
    Bootstrap startContainer(Environment environment, Deployment deployment);

    /**
     * Starts the application container initialization process:
     *
     * <ul>
     * <li>Reads metadata from beans.xml and the {@link Deployment} service</li>
     * <li>Starts the application context</li>
     * <li>Starts the request context which lasts until {@link #endInitialization()} is called</li>
     * <li>Discovers and creates {@link Extension} service providers</li>
     * </ul>
     *
     * Finally, the {@link BeforeBeanDiscovery} event is fired.
     *
     * @return self
     */
    Bootstrap startInitialization();

    /**
     * Creates and deploys the application's beans:
     *
     * <ul>
     * <li>Creates and deploys the discovered beans</li>
     * <li>Creates and deploys the built-in beans defined by the CDI specification</li>
     * </ul>
     *
     * Finally the {@link AfterBeanDiscovery} is event is fired
     *
     * @return self
     */
    Bootstrap deployBeans();

    /**
     * Validates the deployment.
     *
     * After validation, the {@link AfterDeploymentValidation} event is fired
     *
     * @return self
     */
    Bootstrap validateBeans();

    /**
     * Cleans up after the initialization
     *
     * @return self
     */
    Bootstrap endInitialization();

    /**
     * Causes the container to clean up and shutdown
     *
     * Before the contain is shutdown the {@link BeforeShutdown} event is fired
     */
    void shutdown();

    /**
     * Get the manager used for this beanDeploymentArchive.
     *
     * If {@link #startContainer(Environment, Deployment)} has not been called, this method will return null.
     *
     * If the beanDeploymentArchive is not known to Weld (for example, it was not passed to the Weld as part of the
     * {@link Deployment}, or has not yet been requested by {@link Deployment#loadBeanDeploymentArchive(Class)}), null will be
     * returned.
     *
     * @param beanDeploymentArchive
     * @return the manager or null if not yet available or not found.
     */
    WeldManager getManager(BeanDeploymentArchive beanDeploymentArchive);

    /**
     * Parse the specified URL as a beans.xml file.
     *
     * @param url the url to parse
     * @return the BeansXml data structure which represents the URL
     * @throws IllegalArgumentException if the URL cannot be opened
     *
     */
    BeansXml parse(URL url);

    /**
     * Parse the specified URLs as a series of beans.xml file and merge the result.
     *
     * Duplicate entries are not removed.
     *
     * @param urls the urls to parse
     * @return the BeansXml data structure which represents the URL
     * @throws IllegalArgumentException if the URL cannot be opened
     */
    BeansXml parse(Iterable<URL> urls);

    /**
     * Parse the specified URLs as a series of beans.xml file and merge the result.
     *
     * @param urls the urls to parse
     * @param removeDuplicates whether duplicate entries (alternatives, interceptors, etc) are removed
     * @return the BeansXml data structure which represents the URL
     * @throws IllegalArgumentException if the URL cannot be opened
     */
    BeansXml parse(Iterable<URL> urls, boolean removeDuplicates);

    /**
     * Load CDI extensions using the provided ClassLoader
     *
     * @param classLoader the ClassLoader to use to load the extensions
     * @throws IllegalArgumentException if classLoader is null
     * @return
     */
    Iterable<Metadata<Extension>> loadExtensions(ClassLoader classLoader);

}
