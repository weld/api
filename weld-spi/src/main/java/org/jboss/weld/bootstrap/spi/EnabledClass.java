/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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
 * Represents a single &lt;class&gt; record (alternative/interceptor/decorator definition) in the beans.xml file.
 *
 * @author Jozef Hartinger
 *
 */
public interface EnabledClass {

    /**
     * Returns the value of the enabled flag. Returns null if the flag has not been set.
     */
    Boolean isEnabled();

    /**
     * Returns the priority of the record. Returns null if priority has not been set explicitly.
     */
    Integer getPriority();

    /**
     * Returns the record value - fully qualifier class name.
     */
    String getValue();
}
