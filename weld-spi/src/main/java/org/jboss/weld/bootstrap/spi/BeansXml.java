/*
 * JBoss, Home of Professional Open Source
 * Copyright 2017, Red Hat, Inc., and individual contributors
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

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

public interface BeansXml {

    BeansXml EMPTY_BEANS_XML = new BeansXml() {

        public List<Metadata<String>> getEnabledInterceptors() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledDecorators() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledAlternativeStereotypes() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledAlternativeClasses() {
            return emptyList();
        }

        public Scanning getScanning() {
            return EMPTY_SCANNING;
        }

        public URL getUrl() {
            return null;
        }

        public BeanDiscoveryMode getBeanDiscoveryMode() {
            return BeanDiscoveryMode.ALL;
        }

        @Override
        public String getVersion() {
            return null;
        }

    };

    List<Metadata<String>> getEnabledAlternativeStereotypes();

    List<Metadata<String>> getEnabledAlternativeClasses();

    List<Metadata<String>> getEnabledDecorators();

    List<Metadata<String>> getEnabledInterceptors();

    Scanning getScanning();

    URL getUrl();

    /**
     * @return The value of the <code>bean-discovery-mode</code> attribute or {@link BeanDiscoveryMode#ALL} if the file does not
     *         contain the <code>bean-discovery-mode</code> attribute.
     */
    BeanDiscoveryMode getBeanDiscoveryMode();

    /**
     * @return the value of the <code>version</code> attribute or null if the version is not explicitly specified
     */
    String getVersion();

}