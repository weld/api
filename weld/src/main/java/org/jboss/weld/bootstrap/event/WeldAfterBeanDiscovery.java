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

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Interceptor;

/**
 * Represents an enhanced version of {@link AfterBeanDiscovery}.
 *
 * @seeIssue WELD-2008
 * @author Martin Kouba
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
     * @return a builder for a custom interceptor
     */
    InterceptorConfigurator addInterceptor();

}
