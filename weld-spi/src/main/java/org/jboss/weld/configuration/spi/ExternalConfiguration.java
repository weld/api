/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.configuration.spi;

import java.util.Map;

import org.jboss.weld.bootstrap.api.Service;

/**
 * An integrator may provide a map of configuration properties. This map is considered the source with the lowest priority (i.e.
 * it may be overriden by other
 * sources such as system properties). The supported keys are declared in the documentation. Entries with unsupported keys are
 * ignored. Invalid property value
 * is a deployment problem - initialization will be aborted by the container.
 *
 * @see <a href="https://issues.jboss.org/browse/WELD-1791">WELD-1791</a>
 * @author Martin Kouba
 */
public interface ExternalConfiguration extends Service {

    /**
     * @return the map of configuration properties
     */
    Map<String, Object> getConfigurationProperties();

}
