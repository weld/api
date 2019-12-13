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

import jakarta.enterprise.event.NotificationOptions;

/**
 * The {@link jakarta.enterprise.event.Event#fireAsync(Object, NotificationOptions)} method allows to configure the notification of asynchronous observer methods.
 * <p>
 * Weld defines the following non-portable options:
 * <ul>
 * <li>{@link #MODE}</li>
 * <li>{@link #TIMEOUT}</li>
 * </ul>
 *
 * @author Martin Kouba
 * @see jakarta.enterprise.event.Event#fireAsync(Object, NotificationOptions)
 * @since 3.0
 */
public interface WeldNotificationOptions extends NotificationOptions {

    /**
     * Makes it possible to specify that observer methods should be notified in parallel (if supported).
     *
     * @see NotificationMode
     */
    String MODE = "weld.async.notification.mode";

    /**
     * Makes it possible to specify a timeout (in milliseconds) after which the returned completion stage must be completed.
     * <p>
     * If the time expires the stage is completed exceptionally with a {@link java.util.concurrent.CompletionException} holding the
     * {@link java.util.concurrent.TimeoutException} as its cause. The expiration does not abort the notification of the observers.
     * </p>
     */
    String TIMEOUT = "weld.async.notification.timeout";

    /**
     *
     * @return notification options with {@value #MODE} set to {@link NotificationMode#PARALLEL}
     */
    static NotificationOptions withParallelMode() {
        return NotificationOptions.builder().set(MODE, NotificationMode.PARALLEL).build();
    }

    /**
     *
     * @param timeout The timeout in milliseconds
     * @return notification options with {@value #TIMEOUT} set
     */
    static NotificationOptions withTimeout(long timeout) {
        return NotificationOptions.builder().set(TIMEOUT, timeout).build();
    }

    /**
     *
     * @see WeldNotificationOptions#MODE
     */
    enum NotificationMode {

        /**
         * Async observers are notified serially in a single worker thread (default behavior).
         */
        SERIAL,
        /**
         * Async observers are notified in parallel assuming that the {@link java.util.concurrent.Executor} used supports parallel execution.
         */
        PARALLEL,;

        public boolean isEqual(Object value) {
            return equals(of(value));
        }

        public static NotificationMode of(Object value) {
            if (value != null) {
                if (value instanceof NotificationMode) {
                    return (NotificationMode) value;
                }
                try {
                    return valueOf(value.toString().toUpperCase());
                } catch (IllegalArgumentException ignored) {
                }
            }
            return null;
        }

    }

}
