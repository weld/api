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
package org.jboss.weld.ejb.spi;

/**
 * An implementation of {@link EjbDescriptor} may optionally implement this interface if the EJB container uses
 * subclassing to implement EJB functionality. Weld will use the class returned from {@link #getComponentSubclass()}
 * when instantiating a new EJB instance.
 *
 * @author Jozef Hartinger
 *
 * @param <T> the component type
 */
public interface SubclassedComponentDescriptor<T> {

    /**
     * Returns the enhanced subclass of the component type. Weld will use this subclass to create EJB instances instead
     * of creating instances of the component class.
     *
     * @return the enhanced subclass of the component type
     */
    Class<? extends T> getComponentSubclass();
}
