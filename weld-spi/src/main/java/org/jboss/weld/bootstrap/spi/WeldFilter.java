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
 *
 * <p>
 * {@link Filter} is a data structures representing the &lt;include&gt; and &lt;exclude&gt; elements in Weld's extensions to
 * beans.xml. See the XSD for Weld's extensions to beans.xml for details of the semantics of &lt;include&gt; and
 * &lt;exclude&gt;.
 * </p>
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 */
public interface WeldFilter extends Filter {

    /**
     * The pattern attribute
     * @return the pattern attribute
     */
    String getPattern();
}
