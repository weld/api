/**
 * <p>Annotations relating to decorators.</p>
 * 
 * <p>Decorators are superficially similar to interceptors, 
 * but because they directly implement operations with business 
 * semantics, they are able to implement business logic and, 
 * conversely, unable to implement the cross-cutting concerns 
 * for which interceptors are optimized.</p>
 * 
 * <p>A decorator is a managed bean annotated {@link 
 * javax.decorator.Decorator &#064;Decorator} with a delegate 
 * injection point annotated {@link javax.decorator.Decorates 
 * &#064;Decorates}.</p>
 * 
 * <p>By default, a bean deployment archive has no enabled 
 * decorators. A decorator must be explicitly enabled by listing 
 * its bean class under the <tt>&lt;decorators&gt;</tt> element 
 * of the <tt>beans.xml</tt> file of the bean deployment archive. 
 * The order of the decorator declarations determines the decorator 
 * ordering. Decorators which occur earlier in the list are called 
 * first.</p>
 * 
 * <p>Decorators are called after interceptors.</p>
 * 
 */
package javax.decorator;

