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

public interface CDI11Deployment extends Deployment {

    /**
     * <p>
     * Returns the {@link BeanDeploymentArchive} containing the given class.
     * </p>
     *
     * <p>
     * If the deployment archive containing the given class is not currently a bean deployment archive, null is returned. Unlike
     * {@link #loadBeanDeploymentArchive(Class)}, invocation of this method never results in a new {@link BeanDeploymentArchive}
     * instance to be created. This method may be called at runtime.
     * </p>
     *
     * <p>
     * Alternatively, this method may return some kind of a "root" BDA instead of returning null if the class does not come from
     * a known bean archive.
     * </p>
     *
     * @param beanClass
     * @return the {@link BeanDeploymentArchive} containing the bean class or null if no such {@link BeanDeploymentArchive}
     *         exists
     */
    BeanDeploymentArchive getBeanDeploymentArchive(Class<?> beanClass);
}
