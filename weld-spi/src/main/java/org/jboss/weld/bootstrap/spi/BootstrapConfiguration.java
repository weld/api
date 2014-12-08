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

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.configuration.spi.ExternalConfiguration;
import org.jboss.weld.manager.api.ExecutorServices;

/**
 * This service is deprecated. Use {@link ExternalConfiguration} instead.
 *
 * @author Jozef Hartinger
 *
 */
@Deprecated
public interface BootstrapConfiguration extends Service {

    /**
     * Indicates whether ConcurrentDeployer and ConcurrentValidator should be enabled. If enabled, ConcurrentDeployer and ConcurrentValidator execute their
     * subtasks using {@link ExecutorServices} which can be configured separately.
     *
     * Otherwise, single-threaded version of Deployer and Validator are used.
     *
     * By default, concurrent deployment is enabled.
     * @deprecated this method is deprecated. Use {@link ExternalConfiguration} with <code>org.jboss.weld.bootstrap.concurrentDeployment</code> as key instead
     * @return whether ConcurrentDeployer and ConcurrentValidator should be enabled.
     */
    boolean isConcurrentDeploymentEnabled();

    /**
     * The number of threads used by ContainerLifecycleEventPreloader. The ContainerLifecycleEventPreloader allows observer methods for container lifecycle
     * events to be resolved upfront while the deployment is waiting for classloader or reflection API.
     *
     * ContainerLifecycleEventPreloader has its own thread pool whose size is configured by this property.
     *
     * If set to 0, ContainerLifecycleEventPreloader is not installed.
     *
     * If not specified, the value is set to Math.max(1, Runtime.getRuntime().availableProcessors() - 1)).
     *
     * @deprecated this method is deprecated. Use {@link ExternalConfiguration} with <code>org.jboss.weld.bootstrap.preloaderThreadPoolSize</code> as key instead
     * @return the number of threads used by ContainerLifecycleEventPreloader.
     */
    int getPreloaderThreadPoolSize();

    /**
     * Allows an integrator to enable the non-portable mode. Non-portable mode is suggested by the specification to overcome problems with legacy applications
     * not using CDI SPI properly.
     *
     * The non-portable mode is disabled by default.
     *
     * @see <a href="http://docs.jboss.org/cdi/api/1.1/javax/enterprise/inject/spi/BeanManager.html">BeanManager</a>
     * @deprecated this method is deprecated. Use {@link ExternalConfiguration} with <code>org.jboss.weld.nonPortableMode</code> as key instead
     * @return true if non-portable mode should be enabled.
     */
    boolean isNonPortableModeEnabled();
}
