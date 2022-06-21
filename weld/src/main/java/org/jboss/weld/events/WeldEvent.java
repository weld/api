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
package org.jboss.weld.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.util.TypeLiteral;

/**
 * Enriched version of {@link jakarta.enterprise.event.Event}.
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
public interface WeldEvent<T> extends Event<T> {

    /**
     * <p>
     * Obtains a child {@code Event} for the given required type and additional required qualifiers. Must be invoked on
     * {@code Event<T>} where T is {@link java.lang.Object}.
     * </p>
     *
     * @param <X>        the required type
     * @param type       a {@link java.lang.reflect.Type} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child {@code Event}
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an
     *                                  annotation that is not a qualifier type
     * @throws IllegalStateException    if invoked on {@code Event<T>} where T is of any other type than
     *                                  {@link java.lang.Object}
     */
    <X> WeldEvent<X> select(Type type, Annotation... qualifiers);

    @Override
    public WeldEvent<T> select(Annotation... qualifiers);

    @Override
    public <U extends T> WeldEvent<U> select(Class<U> subtype, Annotation... qualifiers);

    @Override
    public <U extends T> WeldEvent<U> select(TypeLiteral<U> subtype, Annotation... qualifiers);
}
