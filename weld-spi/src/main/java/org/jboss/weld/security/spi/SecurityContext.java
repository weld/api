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
package org.jboss.weld.security.spi;

/**
 * Encapsulation of security information that can be associated with a thread.
 *
 * @author Jozef Hartinger
 *
 */
public interface SecurityContext extends AutoCloseable {

    /**
     * Associate this security context with the current thread.
     */
    void associate();

    /**
     * Clear the security context associated with the current thread.
     */
    void dissociate();

    /**
     * Signals to the integrator that this instance will no longer be used by Weld.
     */
    void close();
}
