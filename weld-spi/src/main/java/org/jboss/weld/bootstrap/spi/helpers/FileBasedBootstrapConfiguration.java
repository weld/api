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
package org.jboss.weld.bootstrap.spi.helpers;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.jboss.weld.bootstrap.spi.BootstrapConfiguration;
import org.jboss.weld.resources.spi.ResourceLoader;
import org.jboss.weld.resources.spi.ResourceLoadingException;

public class FileBasedBootstrapConfiguration implements BootstrapConfiguration {

    private static final String CONFIGURATION_FILE = "org.jboss.weld.bootstrap.properties";
    private static final String SYSTEM_PROPERTY_PREFIX = CONFIGURATION_FILE + ".";

    private static final String CONCURRENT_DEPLOYMENT = "concurrentDeployment";
    private static final String PRELOADER_THREAD_POOL_SIZE = "preloaderThreadPoolSize";

    private final boolean concurrentDeployment;
    private final int preloaderThreadPoolSize;

    public FileBasedBootstrapConfiguration(ResourceLoader loader) {
        URL configuration = loader.getResource(CONFIGURATION_FILE);
        Properties properties = null;
        if (configuration != null) {
            properties = loadProperties(configuration);
        }
        this.concurrentDeployment = initBooleanValue(properties, CONCURRENT_DEPLOYMENT, true);
        this.preloaderThreadPoolSize = initIntValue(properties, PRELOADER_THREAD_POOL_SIZE,
                Math.max(1, Runtime.getRuntime().availableProcessors() - 1));
    }

    private static Properties loadProperties(URL url) {
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            throw new ResourceLoadingException(e);
        }
        return properties;
    }

    private static int initIntValue(Properties properties, String property, int defaultValue) {
        String value = getSystemOrFileProperty(properties, property);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ResourceLoadingException("Invalid thread pool size " + value);
        }
    }

    private static boolean initBooleanValue(Properties properties, String property, boolean defaultValue) {
        String value = getSystemOrFileProperty(properties, property);
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

    private static String getSystemOrFileProperty(Properties properties, String property) {
        String value = System.getProperty(SYSTEM_PROPERTY_PREFIX + property);
        if (value == null && properties != null) {
            return properties.getProperty(property);
        } else {
            return value;
        }
    }

    public boolean isConcurrentDeploymentEnabled() {
        return concurrentDeployment;
    }

    public int getPreloaderThreadPoolSize() {
        return preloaderThreadPoolSize;
    }

    @Override
    public void cleanup() {
    }
}
