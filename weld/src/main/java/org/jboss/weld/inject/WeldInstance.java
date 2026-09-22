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

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.Prioritized;
import jakarta.enterprise.util.TypeLiteral;

/**
 * Represents an enhanced version of {@link Instance}.
 *
 * <p>
 * In the following example we filter out beans which are not {@link Dependent}, sort the beans by priority, and use the
 * handle whose bean has the highest priority (according to {@link #getHandlePriorityComparator()}) to obtain the hello string.
 * Contextual references for beans with lower priority are not created at all.
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
 *         HelloProvider helloProvider = instance.handlesStream().filter(h -&gt; h.getBean().getScope().equals(Dependent.class))
 *                 .sorted(instance.getHandlePriorityComparator()).findFirst().map(Instance.Handle::get).orElse(null);
 *         if (helloProvider != null)
 *             return helloProvider.getHello();
 *         return "No hello provider found!";
 *     }
 * }
 * </pre>
 *
 * @author Martin Kouba
 * @see <a href="https://issues.jboss.org/browse/WELD-2204">WELD-2204</a>
 * @param <T> the required bean type
 */
public interface WeldInstance<T> extends Instance<T> {

    /**
     * The returned comparator sorts handles by priority in descending order.
     * <ul>
     * <li>A class-based bean whose annotated type has {@code jakarta.annotation.Priority} has the priority of value
     * {@code jakarta.annotation.Priority#value()}</li>
     * <li>A custom bean which implements {@link Prioritized} has the priority of value {@link Prioritized#getPriority()}</li>
     * <li>Any other bean has the priority of value 0</li>
     * </ul>
     *
     * @return a comparator instance
     */
    Comparator<Handle<?>> getHandlePriorityComparator();

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
     * @param subtype a {@link java.lang.reflect.Type} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child {@code Instance}
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an
     *         annotation that is not a qualifier type
     * @throws IllegalStateException if the container is already shutdown
     * @throws IllegalStateException if invoked on {@code Instance<T>} where T is of any other type than
     *         {@link java.lang.Object}
     */
    <X> WeldInstance<X> select(Type subtype, Annotation... qualifiers);

}
