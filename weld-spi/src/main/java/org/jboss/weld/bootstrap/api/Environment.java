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

import java.util.Set;

import org.jboss.weld.bootstrap.spi.EEModuleDescriptor;

/**
 * Represents an environment. Specifies the services Weld requires
 *
 * @author Pete Muir
 */
public interface Environment {

    /**
     * The deployment scoped services required for this environment
     *
     * @return the services to require
     */
    Set<Class<? extends Service>> getRequiredDeploymentServices();

    /**
     * The bean deployment archive scoped services required for this environment
     *
     * @return the services to require
     */
    Set<Class<? extends Service>> getRequiredBeanDeploymentArchiveServices();

    /**
     * Environment aware of EE modules. In such environment each bean archive which belongs to a module should register
     * {@link EEModuleDescriptor}
     *
     * @return true by default
     */
    default boolean isEEModulesAware() {
        return true;
    }

    /**
     * Since CDI 4.0, there is a requirement to fire {@link jakarta.enterprise.event.Startup} event when the container
     * is ready to accept requests and {@link jakarta.enterprise.event.Shutdown} before container shutdown.
     *
     * <p>
     * By default, Weld fires these events close to {@code @Initialized(ApplicationScoped.class)}.
     * However, integrators may choose to fire it at later point if they, for instance, need to make sure other
     * related technologies or libraries also bootstrap successfully.
     * </p>
     *
     * <p>
     * Overriding this method and returning {@code false} means that Weld will not attempt to fire these events and
     * this responsibility then lies with the integrator.
     * </p>
     *
     * @see jakarta.enterprise.event.Startup
     * @see jakarta.enterprise.event.Shutdown
     *
     * @return true by default
     */
    default boolean automaticallyHandleStartupShutdownEvents() {
        return true;
    }

}
