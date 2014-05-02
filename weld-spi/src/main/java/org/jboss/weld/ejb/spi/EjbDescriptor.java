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

package org.jboss.weld.ejb.spi;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.ejb.Stateful;

/**
 * EJB metadata from the EJB descriptor. The implementation may optionally implement {@link SubclassedComponentDescriptor}
 * if it uses an enhances subclass to implement EJB functionality.
 *
 * @author Pete Muir
 *
 * @param <T> the bean type
 */
public interface EjbDescriptor<T> {

    /**
     * Gets the EJB type
     *
     * @return The EJB Bean class
     */
    Class<T> getBeanClass();

    /**
     * Gets the local business interfaces of the EJB
     *
     * @return An iterator over the local business interfaces
     */
    Collection<BusinessInterfaceDescriptor<?>> getLocalBusinessInterfaces();

    /**
     * Gets the remote business interfaces of the EJB
     *
     * @return An iterator over the remote business interfaces
     */
    Collection<BusinessInterfaceDescriptor<?>> getRemoteBusinessInterfaces();

    /**
     * Get the EJB name
     *
     * @return
     */
    String getEjbName();

    /**
     * Get the remove methods of the EJB
     *
     * @return An iterator over the remove methods
     */
    Collection<Method> getRemoveMethods();

    /**
     * Indicates if the bean is a stateless session bean
     *
     * @return True if stateless, false otherwise
     */
    boolean isStateless();

    /**
     * Indicates if the bean is a EJB 3.1 Singleton session bean
     *
     * @return True if the bean is a singleton, false otherwise
     */
    boolean isSingleton();

    /**
     * Indicates if the EJB is a stateful session bean
     *
     * @return True if the bean is stateful, false otherwise
     */
    boolean isStateful();

    /**
     * Indicates if the EJB is an MDB
     *
     * @return True if the bean is an MDB, false otherwise
     */
    boolean isMessageDriven();

    /**
     * <p>
     * Indicates if the EJB is passivation capable.
     * </p>
     * <ul>
     * <li>Stateless session beans, singleton session beans and MDBs are not passivation capable.</li>
     * <li>A stateful session bean is passivation capable unless the <code>passivationCapable</code> element of the
     * {@link Stateful} annotation is set to <em>false</em> or the <code>passivation-capable</code> element of the session
     * deployment descriptor element is set to <em>false</em></li>
     * </ul>
     * <p>
     *
     * @return true if the EJB is passivation capable
     */
    boolean isPassivationCapable();

}
