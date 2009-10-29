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

package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.spi.Contextual;

/**
 * <p>Represents an enabled bean. This interface defines everything 
 * the container needs to manage instances of the bean.</p>
 * 
 * @author Gavin King
 * @author David Allen
 * @param <T> the class of the bean instance
 */
public interface Bean<T> extends Contextual<T>
{

   /**
    * Returns the client-visible {@linkplain Type types} of a bean.
    * 
    * @return the bean {@linkplain Type types}
    */
   public Set<Type> getTypes();

   /**
    * Returns the {@linkplain javax.inject.Qualifier qualifiers} of a bean.
    * 
    * @return the {@linkplain javax.inject.Qualifier qualifiers}
    */
   public Set<Annotation> getQualifiers();

   /**
    * Returns the {@linkplain javax.enterprise.context scope} of a bean.
    * 
    * @return the {@linkplain javax.enterprise.context scope}
    */
   public Class<? extends Annotation> getScope();

   /**
    * Returns the name of a bean, if it has one.
    * 
    * @return the name
    */
   public String getName();

   /**
    * The {@linkplain javax.enterprise.inject.Stereotype stereotypes} applied to
    * this bean
    * 
    * @return {@linkplain javax.enterprise.inject.Stereotype stereotypes} if any
    */
   public Set<Class<? extends Annotation>> getStereotypes();

   /**
    * The bean {@linkplain Class class} of the managed bean or session bean or
    * of the bean that declares the producer method or field
    * 
    * @return the {@linkplain Class class} of the managed bean
    */
   public Class<?> getBeanClass();

   /**
    * Test to see if the bean is an
    * {@linkplain javax.enterprise.inject.Alternative alternative}.
    * 
    * @return true if the bean is an
    *         {@linkplain javax.enterprise.inject.Alternative alternative}
    */
   public boolean isAlternative();

   /**
    * Determines if the {@code create()} method may sometimes return a null
    * value.
    * 
    * @return true if the {@code create()} method may return a null
    */
   public boolean isNullable();

   /**
    * A set of {@link InjectionPoint} objects representing injection points of
    * the bean, that will be validated by the container at initialization time.
    * 
    * @return the {@linkplain InjectionPoint injection points} of a bean
    */
   public Set<InjectionPoint> getInjectionPoints();

}