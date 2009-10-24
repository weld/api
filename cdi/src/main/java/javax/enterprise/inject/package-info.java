/**
 * <p>This package contains annotations relating to bean and 
 * stereotype definition, built-in qualifiers, and interfaces 
 * and classes relating to programmatic lookup.</p>
 * 
 * <p>A bean comprises the following attributes:</p>
 *
 * <ul>
 * <li>A (nonempty) set of bean types</li> 
 * <li>A (nonempty) set of qualifiers</li> 
 * <li>A scope</li>
 * <li>Optionally, a bean EL name</li> 
 * <li>A set of interceptor bindings</li> 
 * <li>A bean implementation</li>
 * </ul>
 * 
 * <h3>Bean types</h3>
 * 
 * <p>A bean type is a client-visible type of the bean. A 
 * bean may have multiple bean types. The following bean has 
 * bean types <tt>BookShop</tt>, <tt>Business</tt> and 
 * <tt>Shop&lt;Book&gt;</tt>.</p>
 * 
 * <pre>
 * public class BookShop 
         extends Business 
 *       implements Shop&lt;Book&gt; { 
 *    ... 
 * } 
 * </pre>
 * 
 * <p>Almost any Java type may be a bean type of a bean:</tt>
 *  
 * <ul>
 * <li>A bean type may be an interface, a concrete class or an 
 * abstract class, and may be declared final or have final methods.</li>
 * <li>A bean type may be a parameterized type with actual type 
 * parameters and type variables.</li>
 * <li>A bean type may be an array type. Two array types are 
 * considered identical only if the element type is identical.</li>
 * <li>A bean type may be a primitive type. Primitive types are 
 * considered to be identical to their corresponding wrapper types 
 * in <tt>java.lang</tt>.</li> 
 * <li>A bean type may be a raw type.</li>
 * </ul>
 * 
 * <p>A type variable is not a legal bean type. A parameterized type 
 * that contains a wildcard type parameter is not a legal bean type.</p>
 * 
 * <p>The bean types of a bean may be resticted using the
 * {@link javax.enterprise.inject.Typed &#064;Typed} annotation.</p>
 * 
 * <h3>Qualifiers</h3>
 * 
 * <p>A {@linkplain javax.inject.Qualfiier qualifier} represents some 
 * client-visible semantic associated with a type that is satisfied 
 * by some implementations of the type (and not by others). Qualifiers
 * are applied to injection points to distinguish which implementation 
 * is required by the client.</p>
 * 
 * <pre>
 * &#064;Inject &#064;Synchronous PaymentProcessor paymentProcessor; 
 * </pre>
 * 
 * <p>The qualifiers of a bean are declared by annotating the bean class 
 * or producer method or field with the qualifier types.</p>
 * 
 * <pre>
 * &#064;Synchronous &#064;Reliable 
 * class SynchronousReliablePaymentProcessor 
 *       implements PaymentProcessor { 
 *    ... 
 * } 
 * </pre>
 * 
 * <p>A bean may only be injected to an injection point if</p>
 * 
 * <ul>
 * <li>the type of the injection point is one of the bean types of the 
 * bean, and</li>
 * <li>it has all the qualifiers of the injection point.</li>
 * </ul>
 * 
 * <h3>Scope</h3>
 * 
 * <p>All beans have a scope. The scope of a bean determines the lifecycle 
 * of its instances, and which instances of the bean are visible to instances 
 * of other beans.</p>
 * 
 * <p>The scope of a bean is defined by annotating the bean class or producer 
 * method or field with a scope type or stereotype that declares a default
 * scope type.</p>
 * 
 * <pre>
 * &#064;ConversationScoped 
 * public class Order { ... } 
 * </pre>
 * 
 * <h3>Bean EL name</h3>
 * 
 * <p>A bean may have a bean EL name. A bean with an EL name may be referred 
 * to by its name in Unified EL expressions. A valid bean EL name is a 
 * period-separated list of valid EL identifiers.</p>
 * 
 * <p>To specify the EL name of a bean, the qualifier 
 * {@link javax.inject.Named &#064;Named} is applied to the bean class or 
 * producer method or field.
 * 
 * <pre>
 * &#064;Named("currentOrder") 
 * public class Order { ... } 
 * </pre>
 * 
 * If the <tt>&#064;Named</tt> annotation does not specify the 
 * {@link javax.inject.Named#value() value} member, the EL name is defaulted.
 * 
 * <h3>Interceptor bindings</h3>
 * 
 * <p>Interceptor bindings may be used to associate interceptors with any 
 * managed bean that is not itself an interceptor or decorator or with any 
 * EJB session or message-driven bean.</p>
 * 
 * <p>An interceptor binding may be declared by annotating the bean class, 
 * or a method of the bean class, with an interceptor binding type.</p>
 * 
 * <p>In the following example, the <tt>TransactionInterceptor</tt> will be 
 * applied at the class level, and therefore applies to all business methods 
 * of the class: 
 * 
 * <pre>
 * &#064;Transactional 
 * public class ShoppingCart { ... }
 * </pre>
 * 
 * <p>In this example, the <tt>TransactionInterceptor</tt> will be applied at 
 * the method level:</p>
 * 
 * <pre>
 * public class ShoppingCart { 
 *    &#064;Transactional 
 *    public void placeOrder() { ... } 
 * } 
 * </pre>
 * 
 * <h3>Bean implementation</h3>
 * 
 * <p>The container provides built-in support for injection and contextual 
 * lifecycle management of the following kinds of bean:</p>
 * 
 * <ul>
 * <li>Managed beans</li>
 * <li>Session beans</li> 
 * <li>Producer methods and fields</li> 
 * <li>Resources (Java EE resources, persistence contexts, persistence units, 
 * remote EJBs and web services)</li>
 * </ul>
 * 
 * @see javax.inject.Scope
 * @see javax.inject.Named
 * 
 */
package javax.enterprise.inject;