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
package org.jboss.weld.injection.spi.helpers;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.weld.bootstrap.api.Service;

public abstract class AbstractResourceServices implements Service
{  
   private static final String RESOURCE_LOOKUP_PREFIX = "java:comp/env";
   
   public Object resolveResource(InjectionPoint injectionPoint)
   {
      if (!injectionPoint.getAnnotated().isAnnotationPresent(Resource.class))
      {
         throw new IllegalArgumentException("No @Resource annotation found on injection point " + injectionPoint);
      }
      if (injectionPoint.getMember() instanceof Method && ((Method) injectionPoint.getMember()).getParameterTypes().length != 1)
      {
         throw new IllegalArgumentException("Injection point represents a method which doesn't follow JavaBean conventions (must have exactly one parameter) " + injectionPoint);
      }
      String name = getResourceName(injectionPoint);
      try
      {
         return getContext().lookup(name);
      }
      catch (NamingException e)
      {
         throw new RuntimeException("Error looking up " + name + " in JNDI", e);
      }
   }
   
   public Object resolveResource(String jndiName, String mappedName)
   {
      String name = getResourceName(jndiName, mappedName);
      try
      {
         return getContext().lookup(name);
      }
      catch (NamingException e)
      {
         throw new RuntimeException("Error looking up " + name + " in JNDI", e);
      }
   }
   
   protected String getResourceName(String jndiName, String mappedName)
   {
      if (mappedName != null)
      {
         return mappedName;
      }
      else if (jndiName != null)
      {
         return jndiName;
      }
      else
      {
         throw new IllegalArgumentException("Both jndiName and mappedName are null");
      }
   }
   
   protected abstract Context getContext();
   
   protected String getResourceName(InjectionPoint injectionPoint)
   {
      Resource resource = injectionPoint.getAnnotated().getAnnotation(Resource.class);
      String mappedName = resource.mappedName();
      if (!mappedName.equals(""))
      {
         return mappedName;
      }
      String name = resource.name();
      if (!name.equals(""))
      {
         return RESOURCE_LOOKUP_PREFIX + "/" + name;
      }
      String propertyName;
      if (injectionPoint.getMember() instanceof Field)
      {
         propertyName = injectionPoint.getMember().getName();
      }
      else if (injectionPoint.getMember() instanceof Method)
      {
         propertyName = getPropertyName((Method) injectionPoint.getMember());
         if (propertyName == null)
         {
            throw new IllegalArgumentException(
                  "Injection point represents a method which doesn't follow " +
                  "JavaBean conventions (unable to determine property name) " + injectionPoint);
         }
      }
      else
      {
         throw new AssertionError("Unable to inject into " + injectionPoint);
      }
      String className = injectionPoint.getMember().getDeclaringClass().getName();
      return RESOURCE_LOOKUP_PREFIX + "/" + className + "/" + propertyName;
   }
   
   public static String getPropertyName(Method method)
   {
      String methodName = method.getName();
      if (methodName.matches("^(get).*") && method.getParameterTypes().length == 0)
      {
         return Introspector.decapitalize(methodName.substring(3));
      }
      else if (methodName.matches("^(is).*") && method.getParameterTypes().length == 0)
      {
         return Introspector.decapitalize(methodName.substring(2));
      }
      else
      {
         return null;
      }

   }
   
   public void cleanup()
   {  
      
   }
   
}
