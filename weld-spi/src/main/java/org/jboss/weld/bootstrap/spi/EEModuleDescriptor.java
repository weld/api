/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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
import org.jboss.weld.bootstrap.spi.helpers.EEModuleDescriptorImpl;

/**
 * In Java EE environment, each {@link BeanDeploymentArchive} should provide a description of the Java EE module it belongs to (WAR, RAR, etc.). This applies to
 * physical bean archives deployed within the given module and also to logical bean archives that belong to the module. Bean archives that are not part of a
 * Java EE module (e.g. built-in server libraries) are not required to have a {@link EEModuleDescriptor} service registered.
 *
 * <p>
 * {@link EEModuleDescriptor} is a per-BDA service.
 * </p>
 *
 * <p>
 * It is recommended to share an immutable {@link EEModuleDescriptor} instance for all bean deployment archives of the same Java EE module. However, each bean
 * deployment archive may register its own {@link EEModuleDescriptor} instance. In this case, all descriptors representing a given EE module must use the same
 * id and type.
 * </p>
 *
 * @author Jozef Hartinger
 * @author Martin Kouba
 * @see EEModuleDescriptorImpl
 */
public interface EEModuleDescriptor extends Service {

    /**
     * Enumeration of possible EE module types
     *
     * @author Jozef Hartinger
     *
     */
    public enum ModuleType {
        EAR, WEB, EJB_JAR, APPLICATION_CLIENT, CONNECTOR
    }

    /**
     * @return a unique identifier of an EE module a given {@link BeanDeploymentArchive} belongs to.
     */
    String getId();

    /**
     * Indicates which type of module this descriptor represents.
     *
     * @return the type of module
     */
    ModuleType getType();
}
