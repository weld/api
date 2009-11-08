/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
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

package javax.enterprise.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;


/**
 * <p>Supports inline instantiation of annotation type instances.</p>
 * 
 * <p>An instance of an annotation type may be obtained by subclassing 
 * <tt>AnnotationLiteral</tt>.</p>
 * 
 * <pre>
 * public abstract class PayByQualifier 
 *       extends AnnotationLiteral&lt;PayBy&gt;
 *       implements PayBy {}
 * </pre>
 * 
 * <pre>
 * PayBy payby = new PayByQualifier() { public value() { return CHEQUE; } };
 * </pre>
 * 
 * @author Pete Muir
 * @author Gavin King
 * 
 * @param <T> the annotation type
 * 
 * @see javax.enterprise.inject.Instance#select(Annotation...)
 * @see javax.enterprise.event.Event#select(Annotation...)
 * 
 */
public abstract class AnnotationLiteral<T extends Annotation> 
      implements Annotation, Serializable
{
   
   private transient Class<T> annotationType;
   private transient Method[] members;

   protected AnnotationLiteral() {}
   
   private Method[] getMembers() 
   {
      if (members==null) {
         members = annotationType().getDeclaredMethods();
      }
      return members;
   }
	   
   private static Class<?> getAnnotationLiteralSubclass(Class<?> clazz)
   {
      Class<?> superclass = clazz.getSuperclass();
      if (superclass.equals(AnnotationLiteral.class))
      {
         return clazz;
      }
      else if (superclass.equals(Object.class))
      {
         return null;
      }
      else
      {
         return (getAnnotationLiteralSubclass(superclass));
      }
   }
   
   @SuppressWarnings("unchecked")
   private static <T> Class<T> getTypeParameter(Class<?> annotationLiteralSuperclass)
   {
      Type type = annotationLiteralSuperclass.getGenericSuperclass();
      if (type instanceof ParameterizedType)
      {
         ParameterizedType parameterizedType = (ParameterizedType) type;
         if (parameterizedType.getActualTypeArguments().length == 1)
         {
            return (Class<T>) parameterizedType
                  .getActualTypeArguments()[0];
         }
      }
      return null;
   }

   public Class<? extends Annotation> annotationType()
   {
      if (annotationType==null) 
      {
         Class<?> annotationLiteralSubclass = getAnnotationLiteralSubclass(this.getClass());
         if (annotationLiteralSubclass == null)
         {
            throw new RuntimeException(getClass() + "is not a subclass of AnnotationLiteral");
         }
         annotationType = getTypeParameter(annotationLiteralSubclass);
         if (annotationType == null)
         {
            throw new RuntimeException(getClass() + " is missing type parameter in AnnotationLiteral");
         }
      }
      return annotationType;
   }

   @Override
   public String toString()
   {
      StringBuilder string = new StringBuilder();
      string.append('@').append(annotationType().getName()).append('(');
      for (int i = 0; i < getMembers().length; i++)
      {
         string.append(getMembers()[i].getName()).append('=');
         Object value = invoke(getMembers()[i], this);
         if (value instanceof boolean[]) 
         {
            appendInBraces(string, Arrays.toString((boolean[])value));
         }
         else if (value instanceof byte[]) 
         {
            appendInBraces(string, Arrays.toString((byte[])value));
         }
         else if (value instanceof short[]) 
         {
            appendInBraces(string, Arrays.toString((short[])value));
         }
         else if (value instanceof int[]) 
         {
            appendInBraces(string, Arrays.toString((int[])value));
         }
         else if (value instanceof long[]) 
         {
            appendInBraces(string, Arrays.toString((long[])value));
         }
         else if (value instanceof float[]) 
         {
            appendInBraces(string, Arrays.toString((float[])value));
         }
         else if (value instanceof double[]) 
         {
            appendInBraces(string, Arrays.toString((double[])value));
         }
         else if (value instanceof char[]) 
         {
            appendInBraces(string, Arrays.toString((char[])value));
         }
         else if (value instanceof Object[]) 
         {
            appendInBraces(string, Arrays.toString((Object[])value));
         }
         /*else if (value instanceof Class<?>) 
         {
            string.append(((Class<?>)value).getName()).append(".class");
         }*/
         else 
         {
            string.append(value);
         }
         if (i < getMembers().length - 1)
         {
            string.append(',');
         }
      }
      return string.append(')').toString();
   }
   
   private void appendInBraces(StringBuilder buf, String s) {
      buf.append('{').append(s.substring(1,s.length()-1)).append('}');
   }
   
   @Override
   public boolean equals(Object other)
   {
      if (other instanceof Annotation)
      {
         Annotation that = (Annotation) other;
         if (this.annotationType().equals(that.annotationType()))
         {
            for (Method member : getMembers())
            {
               Object thisValue = invoke(member, this);
               Object thatValue = invoke(member, that);
               if (thisValue.getClass().isArray() && thatValue.getClass().isArray())
               {
                  //TODO: broken for primitive arrays!
                  if (!Arrays.equals(Object[].class.cast(thisValue), Object[].class.cast(thatValue)))
                  {
                     return false;
                  }
               }
               else if (!thisValue.equals(thatValue))
               {
                  return false;
               }
            }
            return true;
         }
      }
      return false;
   }
   
   @Override
   public int hashCode()
   {
      int hashCode = 0;
      for (Method member : getMembers())
      {
         int memberNameHashCode = 127 * member.getName().hashCode();
         Object value = invoke(member, this);
         //TODO: broken for primitive arrays!
         int memberValueHashCode = value.getClass().isArray() ? Arrays.hashCode(Object[].class.cast(value)) : value.hashCode();
         hashCode += memberNameHashCode ^ memberValueHashCode;
      }       
      return hashCode;
   }
   
   private static Object invoke(Method method, Object instance)
   {
      try
      {
         method.setAccessible(true);
         return method.invoke(instance);
      }
      catch (IllegalArgumentException e)
      {
         throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
      }
      catch (IllegalAccessException e)
      {
         throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
      }
      catch (InvocationTargetException e)
      {
         throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
      }
   }

}
