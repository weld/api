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
package org.jboss.weld.manager.api;

import jakarta.enterprise.inject.spi.Bean;

/**
 * A builder that allows a customized {@link WeldInjectionTarget} instance to be created.
 * <p>
 * By default, the returned {@link WeldInjectionTargetBuilder} instance will support:
 * <ul>
 *    <li>resource injection (e.g. &#064;Resource, &#064;EJB)</li>
 *    <li>decorators, as long as bean is set</li>
 *    <li>lifecycle and around-invoke interceptors</li>
 *    <li>target class lifecycle interceptor callbacks</li>
 * </ul>
 *
 * Each of these capabilities may be disabled by calling a corresponding set*Enabled(false) method.
 *
 * @author Jozef Hartinger
 */
public interface WeldInjectionTargetBuilder<T> {

    /**
     * Enables/disables resource injection in the resulting {@link WeldInjectionTargetBuilder}. By default, this feature is enabled.
     * @param value enables/disables resource injection
     * @return the builder
     */
    WeldInjectionTargetBuilder<T> setResourceInjectionEnabled(boolean value);

    /**
     * Enables/disables target class lifecycle callback in the resulting {@link WeldInjectionTargetBuilder}. By default, this feature is enabled.
     * @param value enables/disables target class lifecycle callback
     * @return the builder
     */
    WeldInjectionTargetBuilder<T> setTargetClassLifecycleCallbacksEnabled(boolean value);

    /**
     * Enables/disables interception support in the resulting {@link WeldInjectionTargetBuilder}. By default, this feature is enabled.
     * @param value enables/disables interception support
     * @return the builder
     */
    WeldInjectionTargetBuilder<T> setInterceptionEnabled(boolean value);

    /**
     * Enables/disables decoration support in the resulting {@link WeldInjectionTargetBuilder}. By default, this feature is enabled as long
     * as the bean is set.
     * @param value enables/disables decoration support
     * @return the builder
     */
    WeldInjectionTargetBuilder<T> setDecorationEnabled(boolean value);

    /**
     * Sets the bean that the resulting {@link WeldInjectionTarget} corresponds to. This is an optional attribute of a {@link WeldInjectionTarget} and it
     * is ok to leave this unset for any non-contextual component.
     * @param bean the specified bean
     * @return the builder
     */
    WeldInjectionTargetBuilder<T> setBean(Bean<T> bean);

    /**
     * Returns a newly-created {@code WeldInjectionTarget} based on the contents of this builder.
     * @return newly-created {@code WeldInjectionTarget}
     */
    WeldInjectionTarget<T> build();
}
