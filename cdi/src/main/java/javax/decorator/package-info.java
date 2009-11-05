/**
 * <p>Annotations relating to decorators.</p>
 * 
 * <p>A decorator implements one or more bean types and 
 * intercepts business method invocations of 
 * {@linkplain javax.enterprise.inject beans} which 
 * implement those bean types. These bean types are called 
 * decorated types.</p>
 * 
 * <p>A decorator is a managed bean annotated {@link 
 * javax.decorator.Decorator &#064;Decorator}.</p>
 * 
 * <p>Decorators are superficially similar to interceptors, 
 * but because they directly implement operations with business 
 * semantics, they are able to implement business logic and, 
 * conversely, unable to implement the cross-cutting concerns 
 * for which interceptors are optimized. Decorators are called 
 * after interceptors.</p>
 * 
 * <h3>Decorated types</h3>
 * 
 * <p>The set of decorated types of a decorator includes all 
 * bean types of the managed bean that are Java interfaces, 
 * except for {@link java.io.Serializable}. The decorator bean 
 * class and its superclasses are not decorated types of the 
 * decorator. The decorator class may be abstract.</p>
 * 
 * <p>A decorator intercepts every method:</p>
 * <ul>
 * <li>declared by a decorated type of the decorator</li>
 * <li>that is implemented by the bean class of the decorator.</li>
 * </ul>
 * 
 * <p>A decorator may be an abstract class, and is not required to 
 * implement every method of every decorated type.</p>
 * 
 * <h3>Delegate injection points</h3>
 * 
 * <p>All decorators have a 
 * {@linkplain javax.decorator.Delegate delegate injection point}.  
 * A delegate injection point is an injection point of the bean 
 * class annotated {@link javax.decorator.Delegate &#064;Delegate}.</p>
 * 
 * <p>The type of the delegate injection point must implement or 
 * extend every decorated type. A decorator is not required to 
 * implement the type of the delegate injection point.</p>
 * 
 * <h3>Enabled decorators</h3>
 *  
 * <p>By default, a bean archive has no enabled decorators. A 
 * decorator must be explicitly enabled by listing its bean class 
 * under the <tt>&lt;decorators&gt;</tt> element of the 
 * <tt>beans.xml</tt> file of the bean archive. The order of the 
 * decorator declarations determines the decorator ordering. 
 * Decorators which occur earlier in the list are called first.</p>
 * 
 * <p>A decorator is bound to a bean if:</p>
 * 
 * <ul>
 * <li>The bean is {@linkplain javax.enterprise.inject eligible for injection} 
 * to the delegate injection point of the decorator.</li>
 * <li>The decorator is enabled in the bean archive of the bean.</li>
 * </ul>
 * 
 * <p>If a managed bean class is declared final, it may not have
 * decorators. If a managed bean has a non-static, non-private, 
 * final method, it may not have any decorator which implements
 * that method.</p>
 * 
 * <p>A decorator instance is a 
 * {@linkplain javax.enterprise.context.Dependent dependent object} 
 * of the object it decorates.</p>
 * 
 * @see javax.enterprise.inject
 * 
 * @see javax.decorator.Decorator
 * @see javax.decorator.Delegate
 * 
 */
package javax.decorator;

