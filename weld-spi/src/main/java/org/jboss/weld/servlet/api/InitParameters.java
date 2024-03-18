/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.servlet.api;

/**
 * Names of init parameters that can be used in web.xml to configure Weld's Servlet integration.
 *
 * @author Jozef Hartinger
 *
 */
public interface InitParameters {

    /**
     * Enable/disable ignoring HttpServletListeners notifications for forwarded requests.
     */
    String CONTEXT_IGNORE_FORWARD = "org.jboss.weld.context.ignore.forward";
    /**
     * Enable/disable ignoring HttpServletListeners notifications for include requests (inner requests).
     */
    String CONTEXT_IGNORE_INCLUDE = "org.jboss.weld.context.ignore.include";
    /**
     * A string representation of regex used in {@link org.jboss.weld.servlet.spi.helpers.RegexHttpContextActivationFilter}.
     * Allows to define a pattern for HTTP requests where Weld should skip CDI req. context manipulation.
     *
     */
    String CONTEXT_MAPPING = "org.jboss.weld.context.mapping";

    /**
     * Enable/disable lazy initialization of the conversation context.
     */
    String CONVERSATION_CONTEXT_LAZY_PARAM = "org.jboss.weld.context.conversation.lazy";
}
