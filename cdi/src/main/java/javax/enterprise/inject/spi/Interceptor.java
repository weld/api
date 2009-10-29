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
import java.util.Set;

import javax.interceptor.InvocationContext;

/**
 * <p>Represents an enabled interceptor.</p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author David Allen
 *
 * @param <T> the interceptor bean class
 */
public interface Interceptor<T> extends Bean<T>
{

   /**
    * <p>Get the interceptor bindings of the interceptor.</p>
    * 
    * @return the set of interceptor bindings
    */
   public Set<Annotation> getInterceptorBindingTypes();
   
   /**
    * <p>Determines if the interceptor intercepts callbacks or business methods of 
    * the given type.</p>
    * 
    * @param type the type of interception
    * @return  returns <tt>true</tt> if the interceptor intercepts callbacks 
    * or business methods of the given type, and <tt>false</tt> otherwise.
    */
   public boolean intercepts(InterceptionType type); 
   
   /**
    * <p>Invokes the specified kind of lifecycle callback or business method upon the 
    * given interceptor instance.</p>
    * 
    * @param type the interception type
    * @param instance the interceptor instance to invoke
    * @param ctx the context for the invocation
    * @return the return value from the invocation
    */
   public Object intercept(InterceptionType type, T instance, InvocationContext ctx); 


}