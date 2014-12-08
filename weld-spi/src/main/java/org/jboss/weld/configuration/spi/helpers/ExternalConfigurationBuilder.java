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
package org.jboss.weld.configuration.spi.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jboss.weld.configuration.spi.ExternalConfiguration;

/**
 * A builder for building immutable {@link ExternalConfiguration}
 *
 * @author Jozef Hartinger
 *
 */
public final class ExternalConfigurationBuilder {

    private final Map<String, Object> builder = new HashMap<String, Object>();

    public ExternalConfigurationBuilder add(String key, Object value) {
        builder.put(key, value);
        return this;
    }

    public ExternalConfiguration build() {
        return new ExternalConfigurationImpl(Collections.unmodifiableMap(builder));
    }

    private static class ExternalConfigurationImpl implements ExternalConfiguration {

        private final Map<String, Object> properties;

        private ExternalConfigurationImpl(Map<String, Object> properties) {
            this.properties = properties;
        }

        @Override
        public Map<String, Object> getConfigurationProperties() {
            return properties;
        }

        @Override
        public void cleanup() {
        }

        @Override
        public String toString() {
            return "ExternalConfigurationImpl [properties=" + properties + "]";
        }
    }
}