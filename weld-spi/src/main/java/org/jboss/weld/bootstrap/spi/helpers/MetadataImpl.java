/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

import org.jboss.weld.bootstrap.spi.Metadata;

/**
 * Basic implementation of {@link Metadata}
 *
 * @param <T> the type of metadata
 */
public class MetadataImpl<T> implements Metadata<T> {

    /**
     * Constant used to declare that the metadata location is not available
     */
    public static final String LOCATION_NOT_AVAILABLE = "n/a";

    private final String location;

    private final T value;

    /**
     * Constructs {@link Metadata} from given value.
     * Metadata location is set to {@link #LOCATION_NOT_AVAILABLE}
     *
     * @param value metadata value
     * @return instance of metadata
     * @param <T> metadata type
     */
    public static <T> MetadataImpl<T> from(T value) {
        return new MetadataImpl<T>(value);
    }

    /**
     *
     * @param value type of metadata that this class holds
     */
    public MetadataImpl(T value) {
        this(value, LOCATION_NOT_AVAILABLE);
    }

    /**
     *
     * @param value type of metadata that this class holds
     * @param location String representation of where is this metadata stored
     */
    public MetadataImpl(T value, String location) {
        this.location = location;
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getLocation();
    }
}
