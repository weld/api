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
package org.jboss.weld.resources.spi.helpers;

import java.net.URL;
import java.util.Collection;

import org.jboss.weld.resources.spi.ResourceLoader;

/**
 * An implementation of {@link ResourceLoader} which forwards all its method calls
 * to another {@link ResourceLoader}}. Subclasses should override one or more 
 * methods to modify the behavior of the backing {@link ResourceLoader} as desired
 * per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 * 
 * @author Pete Muir
 *
 */
public abstract class ForwardingResourceLoader implements ResourceLoader
{
   
   protected abstract ResourceLoader delegate();
   
   public Class<?> classForName(String name)
   {
      return delegate().classForName(name);
   }
   
   public URL getResource(String name)
   {
      return delegate().getResource(name);
   }
   
   public Collection<URL> getResources(String name)
   {
      return delegate().getResources(name);
   }
   
   @Override
   public boolean equals(Object obj)
   {
      return this == obj || delegate().equals(obj);
   }
   
   @Override
   public String toString()
   {
      return delegate().toString();
   }
   
   @Override
   public int hashCode()
   {
      return delegate().hashCode();
   }
   
}
