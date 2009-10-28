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
import java.util.List;
import java.util.Set;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.UnsatisfiedResolutionException;

/**
 * <p>
 * This interface provides operations for obtaining contextual references for
 * beans, along with many other operations of use to portable extensions.
 * Occasionally it is necessary to use this programmatic API to fully utilize
 * portable extensions in the Contexts and Dependency Injection for the Java EE
 * platform standard.
 * </p>
 * <p>
 * The container provides a built-in bean with bean type BeanManager, scope
 * {@literal @}{@link javax.enterprise.context.Dependent} and qualifier
 * {@literal @}{@link javax.enterprise.inject.Default}. The built-in
 * implementation is a {@linkplain PassivationCapable passivation capable}
 * dependency. Thus, any bean may obtain an instance of BeanManager by injecting
 * it:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * BeanManager manager;
 * </pre>
 * <p>
 * Java EE components may also obtain an instance of BeanManager from
 * {@linkplain javax.naming JNDI} by looking up the name {@code
 * java:comp/BeanManager}.
 * </p>
 * <p>
 * Any operation of BeanManager may be called at any time during the execution
 * of the application.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author Clint Popetz
 * @author David Allen
 */
public interface BeanManager
{

   /**
    * Obtains a contextual reference for a given bean and a given bean type.
    * 
    * @param bean the {@link Bean} object representing the bean
    * @param beanType a bean type that must be implemented by any proxy that is
    *           returned
    * @return a contextual reference representing the bean
    * @throws IllegalArgumentException if the given type is not a bean type of
    *            the given bean
    */
   public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx);

   /**
    * Obtains an instance of bean for a given injection point. This method
    * should not be called by an application.
    * 
    * @param ij the injection point the instance is needed for
    * @param ctx the context in which the injection is occurring
    * @return an instance of the bean
    * @throws UnsatisfiedResolutionException if no bean can be resolved for the
    *            given type and bindings
    * @throws AmbiguousResolutionException if more than one bean is resolved for
    *            the given type and bindings
    */
   public Object getInjectableReference(InjectionPoint ij, CreationalContext<?> ctx);

   /**
    * Obtain an instance of a {@link CreationalContext} for the given contextual
    * 
    * @param contextual the contextual to create a creational context for
    * @return the {@link CreationalContext} instance
    */
   public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual);

   /**
    * Returns the set of beans which match the given required type and
    * qualifiers and are accessible to the class into which the BeanManager was
    * injected, according to the rules of typesafe resolution. Typesafe
    * resolution usually occurs at container deployment time.
    * 
    * @param beanType the type of the beans to be resolved
    * @param qualifiers the qualifiers used to restrict the matched beans. If no
    *           qualifiers are passed to getBeans(), the default binding @Current
    *           is assumed.
    * @return the matched beans
    * @throws IllegalArgumentException if the given type represents a type
    *            variable
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are
    * @throws IllegalArgumentException if an instance of an annotation that is
    *            not a binding type is given
    */
   public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers);

   /**
    * Returns the set of beans which match the given EL name and are accessible
    * to the class into which the BeanManager was injected, according to the
    * rules of EL name resolution.
    * 
    * @param name the name used to restrict the beans matched
    * @return the matched beans
    */
   public Set<Bean<?>> getBeans(String name);

   /**
    * Returns the {@link Bean} object representing the
    * most specialized enabled bean registered with the container that
    * specializes the given bean,
    * 
    * @param <X> the type of the bean
    * @param bean the {@link Bean} representation of the
    *           bean.
    * @return the most specialized enabled bean
    */
   public <X> Bean<? extends X> getMostSpecializedBean(Bean<X> bean);

   /**
    * Returns the PassivationCapableBean with the given identifier.
    */
   public Bean<?> getPassivationCapableBean(String id);

   /**
    * Apply the ambiguous dependency resolution rules
    * 
    * @param <X> the type of the bean
    * @param beans a set of beans of the given type
    * @throws AmbiguousResolutionException if the ambiguous dependency
    *            resolution rules fail
    */
   public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans);

   /**
    * Validates the injection point dependency.
    * 
    * @param injectionPoint the injection point to validate
    * @throws an InjectionException if there is a deployment problem (for
    *            example, an unsatisfied or unresolvable ambiguous dependency)
    *            associated with the injection point.
    */
   public void validate(InjectionPoint injectionPoint);

   /**
    * Fires an event and notifies observers.
    * 
    * @param event the event object
    * @param qualifiers the event qualifiers used to restrict the observers
    *           matched
    * @throws IllegalArgumentException if the runtime type of the event object
    *            contains a type variable
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are given
    * @throws IllegalArgumentException if an instance of an annotation that is
    *            not a binding type is given,
    */
   public void fireEvent(Object event, Annotation... qualifiers);

   /**
    * Obtains observers for an event by considering event type and qualifiers.
    * 
    * @param <T> the type of the event to obtain
    * @param event the event object
    * @param qualifiers the qualifiers used to restrict the matched observers
    * @return the resolved observers
    * @throws IllegalArgumentException if a parameterized type with a type
    *            parameter or a wildcard is passed
    * @throws IllegalArgumentException if an annotation which is not a event
    *            binding type is passed
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are passed
    */
   public <T> Set<ObserverMethod<?, T>> resolveObserverMethods(T event, Annotation... qualifiers);

   /**
    * Obtains an ordered list of enabled decorators for a set of bean types and
    * a set of qualifiers.
    * 
    * @param types the set of bean types of the decorated bean
    * @param qualifiers the qualifiers declared by the decorated bean
    * @return the resolved decorators
    * @throws IllegalArgumentException if the set of bean types is empty
    * @throws IllegalArgumentException if an annotation which is not a binding
    *            type is passed
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are passed
    */
   public List<Decorator<?>> resolveDecorators(Set<Type> types, Annotation... qualifiers);

   /**
    * Obtains an ordered list of enabled interceptors for a set of interceptor
    * bindings.
    * 
    * @param type the type of the interception
    * @param interceptorBindings the bindings used to restrict the matched
    *           interceptors
    * @return the resolved interceptors
    * @throws IllegalArgumentException if no interceptor binding type is passed
    * @throws IllegalArgumentException if an annotation which is not a
    *            interceptor binding type is passed
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are passed
    */
   public List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings);

   /**
    * Tests the given annotationType to determine if it is a
    * {@linkplain javax.enterprise.context scope type}.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotation is a {@linkplain javax.enterprise.context
    *         scope type}
    */
   public boolean isScope(Class<? extends Annotation> annotationType);

   /**
    * Tests the given annotationType to determine if it is a normal
    * {@linkplain javax.enterprise.context scope type}.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotation is a normal
    *         {@linkplain javax.enterprise.context scope type}
    */
   public boolean isNormalScope(Class<? extends Annotation> annotationType);

   /**
    * Tests the given annotationType to determine if it is a passivating
    * {@linkplain javax.enterprise.context scope type}.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotation is a passivating
    *         {@linkplain javax.enterprise.context scope type}
    */
   public boolean isPassivatingScope(Class<? extends Annotation> annotationType);

   /**
    * Tests the given annotationType to determine if it is a
    * {@linkplain javax.inject.Qualifier qualifier type}.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotation is a {@linkplain javax.inject.Qualifier
    *         qualifier type}
    */
   public boolean isQualifier(Class<? extends Annotation> annotationType);

   /**
    * Tests the given annotationType to determine if it is an
    * {@linkplain Interceptor interceptor} binding type.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotationType is a {@linkplain Interceptor
    *         interceptor} binding type
    */
   public boolean isInterceptorBindingType(Class<? extends Annotation> annotationType);

   /**
    * Tests the given annotationType to determine if it is a
    * {@linkplain javax.enterprise.inject.Stereotype stereotype}.
    * 
    * @param annotationType the annotation to test
    * @return true if the annotationType is a
    *         {@linkplain javax.enterprise.inject.Stereotype stereotype}
    */
   public boolean isStereotype(Class<? extends Annotation> annotationType);

   /**
    * Obtains the set of meta-annotations for an {@linkplain Interceptor
    * interceptor} binding type.
    * 
    * @param bindingType the annotation for which to obtain meta-annotations
    * @return the set of meta-annotations for the given {@linkplain Interceptor
    *         interceptor} binding type
    */
   public Set<Annotation> getInterceptorBindingTypeDefinition(Class<? extends Annotation> bindingType);

   /**
    * Obtains meta-annotations for a
    * {@linkplain javax.enterprise.inject.Stereotype stereotype}.
    * 
    * @param stereotype the {@linkplain javax.enterprise.inject.Stereotype
    *           stereotype} for which to obtain meta-annotations
    * @return the set of meta-annotations for the given
    *         {@linkplain javax.enterprise.inject.Stereotype stereotype}
    */
   public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype);

   /**
    * Obtains an active {@linkplain javax.enterprise.context.spi.Context
    * context} instance for the given {@linkplain javax.enterprise.context scope
    * type}.
    * 
    * @param scopeType the {@linkplain javax.enterprise.context scope} to get
    *           the context instance for
    * @return the {@linkplain javax.enterprise.context.spi.Context context}
    *         instance
    * @throws ContextNotActiveException if no active contexts exist for the
    *            given scope type
    * @throws IllegalArgumentException if more than one active context exists
    *            for the given scope type
    */
   public Context getContext(Class<? extends Annotation> scopeType);

   /**
    * Returns the {@link javax.el.ELResolver} for integration with the servlet
    * engine and JSF implementation. This resolver will return a contextual
    * instance of a bean if the name for resolution resolves to exactly one
    * bean.
    */
   public ELResolver getELResolver();

   /**
    * Returns a wrapper {@link javax.el.ExpressionFactory} that delegates
    * {@link javax.el.MethodExpression} and {@link javax.el.ValueExpression}
    * creation to the given {@link javax.el.ExpressionFactory}. When a Unified
    * EL expression is evaluated using a {@link javax.el.MethodExpression} or
    * {@link javax.el.ValueExpression} returned by the wrapper
    * {@link javax.el.ExpressionFactory}
    * 
    * @param expressionFactory the expression factory to wrap
    * @return the wrapped {@link javax.el.ExpressionFactory}
    */
   public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory);

   /**
    * Get an {@link AnnotatedType} for the given class
    * 
    * @param <T> the type
    * @param type the type
    * @return the {@link AnnotatedType}
    */
   public <T> AnnotatedType<T> createAnnotatedType(Class<T> type);

   /**
    * Returns an InjectionTarget to allow injection into custom beans or
    * non-contextual instances by portable extensions. The container ignores the
    * annotations and types declared by the elements of the actual Java class
    * and uses the metadata provided via the Annotated interface instead.
    * 
    * @param <T> the type of the AnnotatedType to inspect
    * @param type the AnnotatedType to inspect
    * @returns a container provided instance of InjectionTarget for the given
    *          type
    * @throws IllegalArgumentException if there is a definition error associated
    *            with any injection point of the type.
    */
   public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type);

   /**
    * Allows a new bean to be registered. This fires a ProcessBean event and
    * then registers a new bean with the container, thereby making it available
    * for injection into other beans. This method may be called at any time in
    * the applications lifecycle.
    * 
    * @param bean the bean to register
    */
   @Deprecated
   public void addBean(Bean<?> bean);

}
