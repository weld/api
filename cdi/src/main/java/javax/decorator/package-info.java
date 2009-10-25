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
 * <p>The set of decorated types 
 * of a decorator includes all interfaces implemented directly or 
 * indirectly by the bean class, except for {@link java.io.Serializable}. 
 * The decorator bean class and its superclasses are not decorated 
 * types of the decorator. The decorator class may be abstract.</p>
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
 * {@linkplain javax.decorator.Decorates delegate injection point}.  
 * The decorator applies to any bean that is eligible for injection 
 * to the delegate injection point.</p>
 * 
 * <p>A delegate injection point is an injection point of the bean 
 * class annotated {@link javax.decorator.Decorates &#064;Decorates}.</p>
 * 
 * <p>The type of the delegate injection point must implement or 
 * extend every decorated type. A decorator is not required to 
 * implement the type of the delegate injection point.</p>
 * 
 * <h3>Enabled decorators</h3>
 *  
 * <p>By default, a bean deployment archive has no enabled 
 * decorators. A decorator must be explicitly enabled by listing 
 * its bean class under the <tt>&lt;decorators&gt;</tt> element 
 * of the <tt>beans.xml</tt> file of the bean deployment archive. 
 * The order of the decorator declarations determines the decorator 
 * ordering. Decorators which occur earlier in the list are called 
 * first.</p>
 * 
 * @see javax.decorator.Decorator
 * @see javax.decorator.Decorates
 * 
 */
package javax.decorator;

