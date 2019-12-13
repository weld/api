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
package org.jboss.weld.bootstrap.event;

import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Interceptor;
import jakarta.enterprise.inject.spi.configurator.BeanConfigurator;

/**
 * Represents an enhanced version of {@link AfterBeanDiscovery}.
 *
 * @author Martin Kouba
 * @see <a href="https://issues.jboss.org/browse/WELD-2008">WELD-2008</a>
 */
public interface WeldAfterBeanDiscovery extends AfterBeanDiscovery {

    /**
     * Obtain a {@link InterceptorConfigurator} to configure a new {@link Interceptor}.
     * <p>
     * The configured interceptor is automatically added at the end of the observer invocation.
     * </p>
     * <p>
     * Each call returns a new configurator instance.
     * </p>
     *
     * @return a configurator to configure a new interceptor
     */
    InterceptorConfigurator addInterceptor();

    /**
     * Obtain a {@link WeldBeanConfigurator}, an extended version of {@link BeanConfigurator}.
     * <p>
     * The configurator behaves in the same manner as {@link BeanConfigurator}.
     * Configured bean is added automatically at the end of the observer invocation.
     * </p>
     * <p>
     * Each call returns new configurator instance.
     * </p>
     *
     * @return a configurator to configure custom new bean
     */
    @Override
    public <T> WeldBeanConfigurator<T> addBean();

}
