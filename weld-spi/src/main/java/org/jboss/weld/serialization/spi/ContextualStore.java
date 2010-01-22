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
package org.jboss.weld.serialization.spi;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.serialization.spi.helpers.SerializableContextual;
import org.jboss.weld.serialization.spi.helpers.SerializableContextualInstance;

/**
 * Application wide contextual identifier service which allows a serializable
 * reference to a contextual to be obtained, and the contextual to be returned
 * for a given id.
 * <p/>
 * If the contextual implements PassivationCapable, the id will be obtained
 * from it, in which case the Contextual can be activated in any container.
 * If not, the Contextual can only be activated in this container.
 * <p/>
 * Note that this allows a Bean object to be loaded regardless
 * of the bean's accessiblity from the current module, and should not be abused
 * as a way to ignore accessibility rules enforced during resolution.
 *
 * @author Pete Muir
 * @author Marius Bogoevici
 */
public interface ContextualStore extends Service
{
   /**
    * Given a particular id, return the correct contextual. For contextuals
    * which aren't passivation capable, the contextual can't be found in another
    * container, and null will be returned.
    *
    * @param id An identifier for the contextual
    * @return the contextual
    */

   <C extends Contextual<I>, I> C getContextual(String id);

   /**
    * Add a contextual (if not already present) to the store, and return
    * it's id. If the contextual is passivation capable, it's id will
    * be used, otherwise an id will be generated
    *
    * @param contextual the contexutal to add
    * @return the current id for the contextual
    */
   String putIfAbsent(Contextual<?> contextual);

   /**
    * Returns a {@link SerializableContextual} that corresponds to the given {@link Contextual}
    *
    * @param contextual the contextual for which the serializable contextual is created
    * @return a serializable contextual
    */
   <C extends Contextual<I>, I> SerializableContextual<C, I> getSerializableContextual(Contextual<I> contextual);

   /**
    * Returns a {@link org.jboss.weld.serialization.spi.helpers.SerializableContextualInstance} that corresponds to the given instance and {@link Contextual}
    *
    * @param contextual the contextual for which the serializable contextual instance is returned
    * @param instance the instance
    * @param creationalContext the creational context of the instance
    * @return the serializable contextual instance
    */
   <C extends Contextual<I>, I>SerializableContextualInstance<C, I> getSerializableContextualInstance(Contextual<I> contextual, I instance, CreationalContext<I> creationalContext);
}
