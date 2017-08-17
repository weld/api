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
package org.jboss.weld.proxy;

import javax.enterprise.inject.spi.Bean;

/**
 * An extension to {@link WeldConstruct} interface which denotes a client proxy object. All Weld client proxies implement this
 * interface hence allowing access to underlying {@link Bean} metadata and contextual instance.
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
public interface WeldClientProxy extends WeldConstruct {

    /**
     * Retrieve a wrapper class contextual metadata.
     *
     * @return wrapper allowing access to contextual instance and {@link Bean} metadata
     */
    Metadata getMetadata();

    public interface Metadata {

        /**
         * Retrieve {@link Bean} metadata for this proxy instance.
         *
         * @return {@link Bean} metadata for this proxy
         */
        Bean<?> getBean();

        /**
         * Retrieve the current contextual instance associated with the current context for this client proxy. Note that in some
         * cases the contextual instance is still an instance of {@link WeldConstruct}.
         *
         * @return the underlying contextual instance of this client proxy instance.
         */
        Object getContextualInstance();
    }
}
