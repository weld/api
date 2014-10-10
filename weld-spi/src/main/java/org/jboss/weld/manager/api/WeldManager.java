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

package org.jboss.weld.manager.api;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.construction.api.WeldCreationalContext;
import org.jboss.weld.ejb.spi.EjbDescriptor;
import org.jboss.weld.serialization.spi.BeanIdentifier;

/**
 * Functionality provided by the Weld Manager over and above the JSR-299 Manager.
 *
 * @author Pete Muir
 *
 */
public interface WeldManager extends BeanManager, Serializable {

    /**
     * Create a new child activity. A child activity inherits all beans, interceptors, decorators, observers, and contexts
     * defined by its direct and indirect parent activities.
     *
     * This method should not be called by the application.
     *
     * @return the child activity
     */
    WeldManager createActivity();

    /**
     * Associate an activity with the current context for a normal scope
     *
     * @param scopeType the scope to associate the activity with
     * @return the activity
     * @throws ContextNotActiveException if the given scope is inactive
     * @throws IllegalArgumentException if the given scope is not a normal scope
     */
    WeldManager setCurrent(Class<? extends Annotation> scopeType);

    /**
     * The injection target for the given EJB, or null if Weld was not given this descriptor in the deployment.
     *
     * This should only be used to create an inject contextual EJBs by the EJB container.
     * {@link #fireProcessInjectionTarget(AnnotatedType)} and
     * {@link #fireProcessInjectionTarget(AnnotatedType, InjectionTarget)}
     * must be used to obtain an {@link InjectionTarget} for non-contextual EJB injection.
     *
     * @param <T>
     * @param descriptor
     * @return the injection target for the given EJB
     */
    <T> InjectionTarget<T> createInjectionTarget(EjbDescriptor<T> descriptor);

    /**
     * Get the Bean object for the given EJB, or null if Weld was not given this descriptor in the deployment.
     *
     * @param <T>
     * @param descriptor
     * @return the Bean object for the given EJB
     */
    <T> Bean<T> getBean(EjbDescriptor<T> descriptor);

    /**
     * Get the EjbDescriptor for the given ejb name
     *
     * @param <T>
     * @param ejbName
     * @return the EjbDescriptor for the given ejb name
     */
    <T> EjbDescriptor<T> getEjbDescriptor(String ejbName);

    /**
     * Get the services registered for this manager
     *
     * @return the services registered for this manager
     */
    ServiceRegistry getServices();

    /**
     *
     * @return the {@link WeldManager} that corresponds to the current activity
     */
    WeldManager getCurrent();

    /**
     * Fire a ProcessInjectionTarget event for the given type.
     *
     * A helper method to allow integration code to easily fire the ProcessInjectionTarget for Java EE component classes
     * supporting injection
     *
     * The container must use the returned InjectionTarget to inject the Java EE component.
     *
     * @param <X>
     * @param type
     * @return InjectionTarget
     */
    <X> InjectionTarget<X> fireProcessInjectionTarget(AnnotatedType<X> type);

    /**
     * Fire a ProcessInjectionTarget event for the given type.
     *
     * A helper method to allow integration code to easily fire the ProcessInjectionTarget for Java EE component classes
     * supporting injection
     *
     * The container must use the returned InjectionTarget to inject the Java EE component.
     *
     * @param <X>
     * @param annotatedType
     * @param injectionTarget
     * @return InjectionTarget
     */
    <X> InjectionTarget<X> fireProcessInjectionTarget(AnnotatedType<X> annotatedType, InjectionTarget<X> injectionTarget);

    /**
     * The ID of the manager, identical to the ID provided by the BDA
     *
     * @return the ID of the manager
     */
    String getId();

    Instance<Object> instance();

    @Override
    <T> WeldInjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> type);

    @Override
    <T> WeldCreationalContext<T> createCreationalContext(Contextual<T> contextual);

    Bean<?> getPassivationCapableBean(BeanIdentifier identifier);

    /**
     * Returns a new instance of {@link WeldInjectionTargetBuilder} which can be used to create a new {@link WeldInjectionTarget} for the specified type.
     * @param <T>
     * @param type the specified type
     * @return a new {@link WeldInjectionTargetBuilder} instance for the specified type
     */
    <T> WeldInjectionTargetBuilder<T> createInjectionTargetBuilder(AnnotatedType<T> type);

    /**
     *
     * @return an unwrapped instance (e.g. the delegate in the case of forwarding implementation)
     */
    WeldManager unwrap();

}
