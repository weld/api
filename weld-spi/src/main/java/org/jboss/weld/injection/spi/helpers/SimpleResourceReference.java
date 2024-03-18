/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.injection.spi.helpers;

import java.io.Serializable;

import org.jboss.weld.injection.spi.ResourceReference;

/**
 * A trivial holder that holds a resource reference and does not handle releasing.
 *
 * The holder is serializable as long as the held instance is serializable.
 *
 * @author Jozef Hartinger
 *
 * @param <T> the type of the instance
 */
public class SimpleResourceReference<T> implements ResourceReference<T>, Serializable {

    private static final long serialVersionUID = -4908795910866810739L;

    /**
     * Resource reference
     */
    private final T instance;

    /**
     * Construct an instance from provided instance
     *
     * @param instance resource reference
     */
    public SimpleResourceReference(T instance) {
        this.instance = instance;
    }

    @Override
    public T getInstance() {
        return instance;
    }

    @Override
    public void release() {
        // noop
    }

}