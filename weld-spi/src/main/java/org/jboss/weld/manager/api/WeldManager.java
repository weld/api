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
import java.util.Collection;
import java.util.stream.Collectors;

import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.PassivationCapable;
import jakarta.enterprise.inject.spi.el.ELAwareBeanManager;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.construction.api.WeldCreationalContext;
import org.jboss.weld.context.WeldAlterableContext;
import org.jboss.weld.ejb.spi.EjbDescriptor;
import org.jboss.weld.serialization.spi.BeanIdentifier;

/**
 * Functionality provided by the Weld Manager over and above the {@link BeanManager}.
 *
 * @author Pete Muir
 */
public interface WeldManager extends BeanManager, ELAwareBeanManager, Serializable {

    /**
     * The injection target for the given EJB, or null if Weld was not given this descriptor in the deployment.
     *
     * This should be used to create and inject contextual EJBs by the EJB container.
     * Can also be used for message driven beans, even though they are non-contextual.
     * {@link #fireProcessInjectionTarget(AnnotatedType)} and
     * {@link #fireProcessInjectionTarget(AnnotatedType, InjectionTarget)}
     * must be used to obtain an {@link InjectionTarget} for non-contextual EJB injection.
     *
     * @param <T> the type of the EJB
     * @param descriptor the given EJB descriptor
     * @return the injection target for the given EJB
     */
    <T> InjectionTarget<T> createInjectionTarget(EjbDescriptor<T> descriptor);

    /**
     * Get the Bean object for the given EJB, or null if Weld was not given this descriptor in the deployment.
     *
     * @param <T> the type of the bean
     * @param descriptor the given EJB descriptor
     * @return the Bean object for the given EJB
     */
    <T> Bean<T> getBean(EjbDescriptor<T> descriptor);

    /**
     * Get the EjbDescriptor for the given EJB name
     *
     * @param <T> the type of the EJB
     * @param ejbName the given EJB name
     * @return the EjbDescriptor for the given EJB name
     */
    <T> EjbDescriptor<T> getEjbDescriptor(String ejbName);

    /**
     * Get the services registered for this manager
     *
     * @return the services registered for this manager
     */
    ServiceRegistry getServices();

    /**
     * Fire a ProcessInjectionTarget event for the given type.
     *
     * A helper method to allow integration code to easily fire the ProcessInjectionTarget for Java EE component classes
     * supporting injection
     *
     * The container must use the returned InjectionTarget to inject the Java EE component.
     *
     * @param <X> the type
     * @param type the type
     * @return final version of InjectionTarget after all observers have been invoked
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
     * @param <X> the type
     * @param annotatedType the type
     * @param injectionTarget the injection target
     * @return final version of InjectionTarget after all observers have been invoked
     */
    <X> InjectionTarget<X> fireProcessInjectionTarget(AnnotatedType<X> annotatedType, InjectionTarget<X> injectionTarget);

    /**
     * The ID of the manager, identical to the ID provided by the BDA
     *
     * @return the ID of the manager
     */
    String getId();

    /**
     * @see <a href="https://issues.jboss.org/browse/CDI-671">CDI-671</a>
     * @return a new {@link Instance} with required type {@link Object} and no required qualifiers
     *         ({@link jakarta.enterprise.inject.Default} is added automatically during resolution if
     *         no qualifiers are selected)
     */
    Instance<Object> instance();

    @Override
    <T> WeldInjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> type);

    @Override
    <T> WeldCreationalContext<T> createCreationalContext(Contextual<T> contextual);

    /**
     * Returns the {@link PassivationCapable} bean with the given identifier.
     *
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer,
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     *
     * @param identifier the identifier
     * @return a {@link Bean} that implements {@link PassivationCapable} and has the given
     *         identifier, or a null value if there is no such bean
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    Bean<?> getPassivationCapableBean(BeanIdentifier identifier);

    /**
     * Returns a new instance of {@link WeldInjectionTargetBuilder} which can be used to create a new
     * {@link WeldInjectionTarget} for the specified type.
     *
     * @param <T> the type
     * @param type the specified type
     * @return a new {@link WeldInjectionTargetBuilder} instance for the specified type
     */
    <T> WeldInjectionTargetBuilder<T> createInjectionTargetBuilder(AnnotatedType<T> type);

    /**
     *
     * @return an unwrapped instance (e.g. the delegate in the case of forwarding implementation)
     */
    WeldManager unwrap();

    /**
     * Obtain an {@link AnnotatedType} that may be used to read the annotations of the given class or interface.
     * <p>
     * Allows multiple annotated types, based on the same underlying type, to be created. {@link AnnotatedType}s discovered by
     * the container use the fully
     * qualified class name to identify the type.
     * <p>
     * This method must only be used when creating non-contextual instances, such as Java EE components. It's not designed to
     * work with contextual instances.
     * <p>
     * If called after the container bootstrap finished, the client code is required to explicitly call
     * {@link #disposeAnnotatedType(Class, String)}
     * as soon as the specified type should be garbage-collected (to avoid memory leaks).
     *
     * @param type underlying class for the {@code AnnotatedType}
     * @param id unique ID of this newly created {@code AnnotatedType}
     * @param <T> holds information about the class type
     * @return the {@link AnnotatedType}
     */
    <T> AnnotatedType<T> createAnnotatedType(Class<T> type, String id);

    /**
     * Dispose the {@link AnnotatedType} created for the identified type.
     * <p>
     * This method should be explicitly called for each result of {@link #createAnnotatedType(Class, String)} created after the
     * container bootstrap finished.
     * <p>
     * It's not necessary to call this method unless the identified type should be a subject of garbage collection.
     *
     * @param type underlying class for the {@code AnnotatedType}
     * @param id unique ID of the {@code AnnotatedType} that is to be destroyed
     * @param <T> holds information about the class type
     * @see #createAnnotatedType(Class, String)
     */
    <T> void disposeAnnotatedType(Class<T> type, String id);

    /**
     * Indicates whether there is an active context for a given scope.
     *
     * @throws IllegalStateException if there are multiple active contexts for a given scope
     * @param scopeType an annotation representing the scope
     * @return true if there is an active context for a given scope, false otherwise
     */
    boolean isContextActive(Class<? extends Annotation> scopeType);

    /**
     * Returns an unmodifiable collection of all registered scopes, both built-in and custom. You can then use
     * {@code BeanManager#getContext()}
     * to retrieve the {@link Context}.
     * The method returns scopes regardless of whether their respective contexts are active or otherwise.
     *
     * @return All scopes present in the application
     */
    Collection<Class<? extends Annotation>> getScopes();

    /**
     * Returns an unmodifiable collection of all currently active {@link Context}s. This is just a convenient method.
     *
     * Note that for each scope, there might be more than one {@link Context}, but there can be at most one active at a time.
     *
     * @return Collection of all currently active {@link Context}s
     */
    default Collection<Context> getActiveContexts() {
        return getScopes().stream()
                .filter(this::isContextActive)
                .map(this::getContext)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an unmodifiable collection of all currently active {@link Context}s that implement {@link WeldAlterableContext}.
     * This is just a convenient method.
     *
     * Note that for each scope, there might be more than one {@link Context}, but there can be at most one active at a time.
     * This method can therefore return an incomplete view of all active contexts as not every context implements
     * {@link WeldAlterableContext}.
     *
     * @return Collection of all active contexts implementing {@link WeldAlterableContext}
     */
    default Collection<WeldAlterableContext> getActiveWeldAlterableContexts() {
        return getScopes().stream()
                .filter(this::isContextActive)
                .map(this::getContext)
                .filter(t -> t instanceof WeldAlterableContext)
                .map(t -> (WeldAlterableContext) t)
                .collect(Collectors.toSet());
    }

    /**
     * Obtains all {@linkplain Context context objects}, active and inactive, for the given
     * {@linkplain jakarta.enterprise.context scope}.
     * <p>
     * This feature is planned to be added into specification as part of
     * <a href="https://github.com/jakartaee/cdi/issues/628">this issue</a>.
     *
     * @param scopeType the {@linkplain jakarta.enterprise.context scope}; must not be {@code null}
     * @return immutable collection of {@linkplain Context context objects}; never {@code null}, but may be empty
     */
    Collection<Context> getContexts(Class<? extends Annotation> scopeType);
}
