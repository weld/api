/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.PassivationCapable;

/**
 * This API is experimental and will change!
 *
 * @seeIssue WELD-1795
 * @author Martin Kouba
 * @param <T> the class of the bean instance
 */
public interface BeanBuilder<T> {

    /**
     * Read the information from the given annotated type.
     *
     * @param type
     * @return self
     */
    BeanBuilder<T> read(AnnotatedType<T> type);

    /**
     * If not set, the extension class is used.
     *
     * @param beanClass
     * @return self
     */
    BeanBuilder<T> beanClass(Class<?> beanClass);

    /**
     *
     * @param type
     * @return self
     */
    BeanBuilder<T> addType(Type type);

    /**
     *
     * @param types
     * @return self
     */
    BeanBuilder<T> types(Type... types);

    /**
     *
     * @param types
     * @return self
     */
    BeanBuilder<T> types(Set<Type> types);

    /**
     *
     * @param scope
     * @return self
     */
    BeanBuilder<T> scope(Class<? extends Annotation> scope);

    /**
     * If the set contains {@link Default}, it's automatically removed.
     *
     * @param qualifier
     * @return self
     */
    BeanBuilder<T> addQualifier(Annotation qualifier);

    /**
     *
     * @param qualifiers
     * @return self
     */
    BeanBuilder<T> qualifiers(Annotation... qualifiers);

    /**
     *
     * @param qualifiers
     * @return self
     */
    BeanBuilder<T> qualifiers(Set<Annotation> qualifiers);

    /**
     *
     * @param stereotype
     * @return self
     */
    BeanBuilder<T> addStereotype(Class<? extends Annotation> stereotype);

    /**
     *
     * @param stereotypes
     * @return self
     */
    BeanBuilder<T> stereotypes(@SuppressWarnings("unchecked") Class<? extends Annotation>... stereotypes);

    /**
     *
     * @param stereotypes
     * @return self
     */
    BeanBuilder<T> stereotypes(Set<Class<? extends Annotation>> stereotypes);

    /**
     *
     * @param name
     * @return self
     */
    BeanBuilder<T> name(String name);

    /**
     *
     * @param value
     * @return self
     */
    BeanBuilder<T> alternative(boolean value);

    /**
     *
     * @param injectionPoint
     * @return self
     */
    BeanBuilder<T> addInjectionPoint(InjectionPoint injectionPoint);

    /**
     *
     * @param injectionPoints
     * @return self
     */
    BeanBuilder<T> injectionPoints(InjectionPoint... injectionPoints);

    /**
     *
     * @param injectionPoints
     * @return
     */
    BeanBuilder<T> injectionPoints(Set<InjectionPoint> injectionPoints);

    /**
     *
     * @param id
     * @see PassivationCapable#getId()
     * @return self
     */
    BeanBuilder<T> id(String id);

    /**
     *
     * @param callback
     * @return self
     */
    BeanBuilder<T> createWith(Function<CreationalContext<T>, T> callback);

    /**
     * If no destroy callback is specified, a NOOP dispose callback is automatically set.
     *
     * @param callback
     * @return self
     */
    BeanBuilder<T> produceWith(Supplier<T> callback);

    /**
     * If no destroy callback is specified, a NOOP dispose callback is automatically set.
     *
     * @param callback
     * @return self
     */
    BeanBuilder<T> produceWith(Function<Instance<Object>, T> callback);

    /**
     *
     * @param callback
     * @return self
     */
    BeanBuilder<T> destroyWith(BiConsumer<T, CreationalContext<T>> callback);

    /**
     *
     * @param callback
     * @return self
     */
    BeanBuilder<T> disposeWith(Consumer<T> callback);

    /**
     *
     * @return an immutable bean
     */
    Bean<T> build();

}
