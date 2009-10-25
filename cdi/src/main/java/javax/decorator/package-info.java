/**
 * <p>Annotations relating to decorators.</p>
 * 
 * <p>A decorator implements one or more bean types and 
 * intercepts business method invocations of beans which 
 * implement those bean types. These bean types are called 
 * decorated types.</p>
 * 
 * <p>Decorators are superficially similar to interceptors, 
 * but because they directly implement operations with business 
 * semantics, they are able to implement business logic and, 
 * conversely, unable to implement the cross-cutting concerns 
 * for which interceptors are optimized.</p>
 * 
 * <p>Decorators are called after interceptors.</p>
 * 
 * <p>A decorator is a managed bean annotated {@link 
 * javax.decorator.Decorator &#064;Decorator} with a delegate 
 * injection point annotated {@link javax.decorator.Decorates 
 * &#064;Decorates}.</p>
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
 */
package javax.decorator;

