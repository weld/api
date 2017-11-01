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
package org.jboss.weld.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.Prioritized;
import javax.enterprise.util.TypeLiteral;

/**
 * Represents an enhanced version of {@link Instance}.
 *
 * <p>
 * In the following example we filter out beans which are not {@link Dependent} then sort the beans by priority and use the handler whose bean has the highest
 * priority (according to {@link #getPriorityComparator()}) to obtain the hello string. Note that contextual references for beans with lower priority are not
 * created at all.
 * </p>
 *
 * <pre>
 * &#64;ApplicationScoped
 * class Hello {
 *
 *     &#64;Inject
 *     WeldInstance&lt;HelloProvider&gt; instance;
 *
 *     String hello() {
 *         HelloProvider helloProvider = instance.handlersStream().filter(h -&gt; h.getBean().getScope().equals(Dependent.class))
 *                 .sorted(instance.getPriorityComparator()).findFirst().map(Handler::get).orElse(null);
 *         if (helloProvider != null)
 *             return helloProvider.getHello();
 *         return "No hello provider found!";
 *     }
 * }
 * </pre>
 *
 * @author Martin Kouba
 * @seeIssue WELD-2204
 * @param <T>
 */
public interface WeldInstance<T> extends Instance<T> {

    /**
     * Obtains an initialized contextual reference handler for the bean that has the required type and required qualifiers and is eligible for injection.
     *
     * <p>
     * The contextual reference is obtained lazily, i.e. when first needed.
     * </p>
     *
     * @return a new handler
     * @throws UnsatisfiedResolutionException
     * @throws AmbiguousResolutionException
     */
    Handler<T> getHandler();

    /**
     * Allows to iterate over contextual reference handlers for all the beans that have the required type and required qualifiers and are eligible
     * for injection.
     *
     * <p>
     * Note that the returned {@link Iterable} is stateless and so each {@link Iterable#iterator()} produces a new set of handlers.
     * </p>
     *
     * @return a new iterable
     */
    Iterable<Handler<T>> handlers();

    /**
     *
     * @return a new stream of contextual reference handlers
     */
    default Stream<Handler<T>> handlersStream() {
        return StreamSupport.stream(handlers().spliterator(), false);
    }

    /**
     * The returned comparator sorts handlers by priority in descending order.
     * <ul>
     * <li>A class-based bean whose annotated type has {@code javax.annotation.Priority} has the priority of value {@code javax.annotation.Priority#value()}</li>
     * <li>A custom bean which implements {@link Prioritized} has the priority of value {@link Prioritized#getPriority()}</li>
     * <li>Any other bean has the priority of value 0</li>
     * </ul>
     *
     * @return a comparator instance
     */
    Comparator<Handler<?>> getPriorityComparator();

    @Override
    WeldInstance<T> select(Annotation... qualifiers);

    @Override
    <U extends T> WeldInstance<U> select(Class<U> subtype, Annotation... qualifiers);

    @Override
    <U extends T> WeldInstance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers);

     /**
     * <p>
     * Obtains a child {@code Instance} for the given required type and additional required qualifiers.
     * Must be invoked on {@code Instance<T>} where T is {@link java.lang.Object}.
     * </p>
     *
     * @param <X> the required type
     * @param subtype    a {@link java.lang.reflect.Type} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child <tt>Instance</tt>
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an
     *                                  annotation that is not a qualifier type
     * @throws IllegalStateException    if the container is already shutdown
     * @throws IllegalStateException    if invoked on {@code Instance<T>} where T is of any other type than {@link java.lang.Object}
     */
    <X> WeldInstance<X> select(Type subtype, Annotation... qualifiers);

    /**
     * This interface represents a contextual reference handler.
     * <p>
     * Allows to inspect the metadata of the relevant bean and also to destroy the underlying contextual instance.
     * </p>
     *
     * @author Martin Kouba
     * @param <T>
     */
    interface Handler<T> extends AutoCloseable {

        /**
         * The contextual reference is obtained lazily, i.e. when first needed.
         *
         * @return the contextual reference
         * @see Instance#get()
         * @throws IllegalStateException If the producing {@link WeldInstance} does not exist
         */
        T get();

        /**
         *
         * @return the bean metadata
         */
        Bean<?> getBean();

        /**
         * Destroy the contextual instance.
         *
         * It's a no-op if:
         * <ul>
         * <li>called multiple times</li>
         * <li>if the producing {@link WeldInstance} does not exist</li>
         * <li>if the handler does not hold a contextual reference, i.e. {@link #get()} was never called</li>
         * </ul>
         *
         * @see Instance#destroy(Object)
         */
        void destroy();

        /**
         * Delegates to {@link #destroy()}.
         */
        @Override
        void close();

    }

}