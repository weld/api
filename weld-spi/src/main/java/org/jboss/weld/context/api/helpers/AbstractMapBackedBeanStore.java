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
package org.jboss.weld.context.api.helpers;

import java.util.Map;
import java.util.Set;

import javax.enterprise.context.spi.Contextual;

import org.jboss.weld.context.api.BeanStore;
import org.jboss.weld.context.api.ContextualInstance;

public abstract class AbstractMapBackedBeanStore implements BeanStore
{
   
   public AbstractMapBackedBeanStore()
   {
      super();
   }

   protected abstract Map<String, ContextualInstance<? extends Object>> delegate();

   /**
    * Gets an instance from the store
    * 
    * @param The bean to look for
    * @return An instance, if found
    * 
    * @see org.jboss.weld.context.api.BeanStore#get(BaseBean)
    */
   public <T extends Object> ContextualInstance<T> get(String id)
   {
      @SuppressWarnings("unchecked")
      ContextualInstance<T> instance = (ContextualInstance<T>) delegate().get(id);
      return instance;
   }

   /**
    * Clears the store
    * 
    * @see org.jboss.weld.context.api.BeanStore#clear()
    */
   public void clear()
   {
      delegate().clear();
   }

   /**
    * Returns the beans contained in the store
    * 
    * @return The beans present
    * 
    * @see org.jboss.weld.context.api.BeanStore#getContextuals()
    */
   public Set<String> getContextualIds()
   {
      return delegate().keySet();
   }

   /**
    * Puts a bean instance under the bean key in the store
    * 
    * @param bean The bean
    * @param instance the instance
    * 
    * @see org.jboss.weld.context.api.BeanStore#put(Contextual, Object)
    */
   public <T> void put(String id, ContextualInstance<T> beanInstance)
   {
      delegate().put(id, beanInstance);
   }

   @Override
   public String toString()
   {
      return "holding " + delegate().size() + " instances";
   }
   
}