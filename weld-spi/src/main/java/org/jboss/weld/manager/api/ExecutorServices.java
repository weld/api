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
package org.jboss.weld.manager.api;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.weld.bootstrap.api.Service;

/**
 * Allows a custom TaskExecutor to be provided by the container. By default, {@link Executors#newSingleThreadExecutor()} is
 * used.
 *
 * This is a per-deployment service.
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 */
public interface ExecutorServices extends Service {

    ExecutorService getTaskExecutor();

    /**
     * Returns a new ScheduledExecutorService instance which will be used for asynchronous observer notification timeout.
     * Can return null in which case the observer notification timeout feature throws an exception.
     * @return ScheduledExecutorService instance or null
     */
    default ScheduledExecutorService getTimerExecutor() {
        return null;
    }

    /**
     * Executes the given tasks and blocks until they all finish. If a task throws an exception, the exception is rethrown by
     * this method. If multiple tasks throw exceptions, there is no guarantee about which of the exceptions is rethrown by this
     * method.
     *
     * @param <T> the result type of tasks
     * @param tasks the collection of tasks
     * @return A list of Futures representing the tasks, in the same sequential order as produced by the iterator for the given
     *         task list, each of which has completed.
     */
    <T> List<Future<T>> invokeAllAndCheckForExceptions(Collection<? extends Callable<T>> tasks);

    /**
     * Executes all the tasks returned from calling {@link TaskFactory#createTasks(int)} method. The method is called exactly
     * once.If a task throws an exception, the exception is rethrown by this method. If multiple tasks throw exceptions, there
     * is no guarantee about which of the exceptions is rethrown by this method.
     *
     * @param <T> the result type of tasks
     * @param factory factory capable of creating tasks
     * @return A list of Futures representing the tasks, in the same sequential order as produced by the iterator for the given
     *         task list, each of which has completed.
     */
    <T> List<Future<T>> invokeAllAndCheckForExceptions(TaskFactory<T> factory);

    /**
     * Instead of submitting a list of tasks to be executed a caller may submit a factory object capable of creating the list of
     * tasks. The size of the underlying thread pool is passed as a parameter. An implementation may or may not consider this
     * parameter when creating the task list.
     *
     * @author Jozef Hartinger
     *
     * @param <T> the type of the result of created tasks
     */
    public interface TaskFactory<T> {

        /**
         * Creates a list of tasks to be executed in a thread pool.
         *
         * @param threadPoolSize the size of the underlying thread pool
         * @return a list of tasks to be executed in a thread pool
         */
        List<Callable<T>> createTasks(int threadPoolSize);
    }

}
