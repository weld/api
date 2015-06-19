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

/**
 * A per-BDA service that provides information about the EE module a given {@link BeanDeploymentArchive} belongs to.
 *
 * @author Jozef Hartinger
 *
 */
public interface EEModuleDescriptor extends Service {

    /**
     * Enumeration of possible EE module types
     * @author Jozef Hartinger
     *
     */
    public enum ModuleType {
        EAR, WEB, EJB_JAR, APPLICATION_CLIENT, CONNECTOR
    }

    /**
     * Returns a unique identifier of an EE module a given {@link BeanDeploymentArchive} belongs to.
     */
    String getId();

    /**
     * Indicates which type of module this module is.
     * @return the type of module
     */
    ModuleType getType();
}
