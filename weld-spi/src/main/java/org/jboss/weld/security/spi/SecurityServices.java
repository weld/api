/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

import java.security.Principal;
import java.util.function.Consumer;

import org.jboss.weld.bootstrap.api.Service;

/**
 * Responsible for accessing security related functionality the environment can provide.
 *
 * <p>
 * Required in a Java EE environment. {@link SecurityServices} is a per-deployment service.
 * </p>
 *
 * <p>
 * An integrator should either implement {@link #getSecurityContext()} or {@link #getSecurityContextAssociator()}. By default, the
 * {@link #getSecurityContextAssociator()} method delegates to {@link #getSecurityContext()}. The container always calls the
 * {@link #getSecurityContextAssociator()} method.
 * </p>
 *
 * @author pmuir
 * @author Jozef Hartinger
 *
 */
public interface SecurityServices extends Service {

    /**
     * Obtain the Principal representing the current caller identity
     *
     * @return the Principal representing the current caller identity
     */
    Principal getPrincipal();

    /**
     * Obtain the security context associated with the current thread. This method is used by Weld to propagate the security context of the current thread to
     * different threads.
     *
     * <p>
     * By default, a noop fallback implementation is returned.
     * </p>
     *
     * @return the security context associated with the current thread
     */
    default SecurityContext getSecurityContext() {
        return SecurityContext.NOOP_SECURITY_CONTEXT;
    }

    /**
     * Obtain the security context associated with the current thread and associate this context when an action is performed. This method is used by Weld to propagate the security context of the current thread to
     * different threads.
     *
     * <p>
     * By default, the consumer is using {@link #getSecurityContext()} to associate the security context.
     * </p>
     *
     * @return a consumer that accepts an action to be performed with the security context associated with the current thread
     */
    default Consumer<Runnable> getSecurityContextAssociator() {
        final SecurityContext securityContext = getSecurityContext();
        return (action) -> {
            try {
                securityContext.associate();
                action.run();
            } finally {
                securityContext.dissociate();
                securityContext.close();
            }
        };
    }

}
