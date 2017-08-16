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
package org.jboss.weld.bootstrap.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.Prioritized;
import javax.enterprise.inject.spi.configurator.BeanConfigurator;
import javax.enterprise.util.TypeLiteral;

/**
 * Represents an enhanced version of {@link BeanConfigurator<T>}
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
public interface WeldBeanConfigurator<T> extends BeanConfigurator<T> {

    @Override
    WeldBeanConfigurator<T> alternative(boolean value);

    @Override
    WeldBeanConfigurator<T> name(String name);

    @Override
    WeldBeanConfigurator<T> stereotypes(Set<Class<? extends Annotation>> stereotypes);

    @Override
    WeldBeanConfigurator<T> addStereotypes(Set<Class<? extends Annotation>> stereotypes);

    @Override
    WeldBeanConfigurator<T> addStereotype(Class<? extends Annotation> stereotype);

    @Override
    WeldBeanConfigurator<T> qualifiers(Set<Annotation> qualifiers);

    @Override
    WeldBeanConfigurator<T> qualifiers(Annotation... qualifiers);

    @Override
    WeldBeanConfigurator<T> addQualifiers(Set<Annotation> qualifiers);

    @Override
    WeldBeanConfigurator<T> addQualifiers(Annotation... qualifiers);

    @Override
    WeldBeanConfigurator<T> addQualifier(Annotation qualifier);

    @Override
    WeldBeanConfigurator<T> scope(Class<? extends Annotation> scope);

    @Override
    WeldBeanConfigurator<T> types(Set<Type> types);

    @Override
    WeldBeanConfigurator<T> types(Type... types);

    @Override
    WeldBeanConfigurator<T> addTransitiveTypeClosure(Type type);

    @Override
    WeldBeanConfigurator<T> addTypes(Set<Type> types);

    @Override
    WeldBeanConfigurator<T> addTypes(Type... types);

    @Override
    WeldBeanConfigurator<T> addType(TypeLiteral<?> typeLiteral);

    @Override
    WeldBeanConfigurator<T> addType(Type type);

    @Override
    WeldBeanConfigurator<T> read(BeanAttributes<?> beanAttributes);

    @Override
    <U extends T> WeldBeanConfigurator<U> read(AnnotatedType<U> type);

    @Override
    WeldBeanConfigurator<T> disposeWith(BiConsumer<T, Instance<Object>> callback);

    @Override
    WeldBeanConfigurator<T> destroyWith(BiConsumer<T, CreationalContext<T>> callback);

    @Override
    <U extends T> WeldBeanConfigurator<U> produceWith(Function<Instance<Object>, U> callback);

    @Override
    <U extends T> WeldBeanConfigurator<U> createWith(Function<CreationalContext<U>, U> callback);

    @Override
    WeldBeanConfigurator<T> id(String id);

    @Override
    WeldBeanConfigurator<T> injectionPoints(Set<InjectionPoint> injectionPoints);

    @Override
    WeldBeanConfigurator<T> injectionPoints(InjectionPoint... injectionPoints);

    @Override
    WeldBeanConfigurator<T> addInjectionPoints(Set<InjectionPoint> injectionPoints);

    @Override
    WeldBeanConfigurator<T> addInjectionPoints(InjectionPoint... injectionPoints);

    @Override
    WeldBeanConfigurator<T> addInjectionPoint(InjectionPoint injectionPoint);

    @Override
    WeldBeanConfigurator<T> beanClass(Class<?> beanClass);

    /**
     * Allows to set a priority to an alternative bean hence selecting it globally.
     * Has the same effect as putting {@link Priority} annotation on an actual bean class
     * or implementing {@link Prioritized} interface with custom bean classes.
     * This method has no effect on custom beans which are not alternatives.
     *
     * @param priority the priority of this bean
     * @return self
     */
    WeldBeanConfigurator<T> priority(int priority);
}
