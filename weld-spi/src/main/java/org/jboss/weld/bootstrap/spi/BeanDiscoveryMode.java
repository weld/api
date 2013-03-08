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
package org.jboss.weld.bootstrap.spi;

/**
 * Represents the value of the <code>bean-discovery-mode</code> attribute within <code>beans.xml</code>. If a
 * <code>beans.xml</code> file does not contain the <code>bean-discovery-mode</code> attribute, the value defaults to
 * {@link BeanDiscoveryMode#ALL}.
 *
 * @author Jozef Hartinger
 *
 */
public enum BeanDiscoveryMode {

    /**
     * This archive will be ignored.
     */
    NONE,

    /**
     * Only those types with bean defining annotations will be considered.
     */
    ANNOTATED,

    /**
     * All types in the archive will be considered.
     */
    ALL,
}
