/**
 * <p>Annotations relating to bean and stereotype definition, 
 * built-in qualifiers, and interfaces and classes relating 
 * to programmatic lookup.</p>
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
 * <p>The bean types of a bean are determined automatically. However, 
 * the set of bean types may be resticted using the
 * {@link javax.enterprise.inject.Typed &#064;Typed} annotation.</p>
 * 
 * <h3>Qualifiers</h3>
 * 
 * <p>A {@linkplain javax.inject.Qualifier qualifier} represents some 
 * client-visible semantic associated with a type that is satisfied 
 * by some implementations of the type (and not by others). Qualifiers
 * are applied to injection points to distinguish which implementation 
 * is required by the client.</p>
 * 
 * <pre>
 * &#064;Inject &#064;Synchronous PaymentProcessor paymentProcessor; 
 * </pre>
 * 
 * <p>A qualifier type is a Java annotation annotated 
 * {@link javax.inject.Qualifier &#064;Qualifier}.</p>
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
 * <li>the bean has all the qualifiers of the injection point.</li>
 * </ul>
 * 
 * <h3>Scope</h3>
 * 
 * <p>All beans have a {@linkplain javax.enterprise.context scope}. The 
 * scope of a bean determines the lifecycle of its instances, and which 
 * instances of the bean are visible to instances of other beans.</p>
 * 
 * <p>A scope type is a Java annotation annotated 
 * {@link javax.inject.Scope &#064;Scope}.</p>
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
 * <p>A bean class or producer method or field may specify at most one 
 * scope type annotation.</p>
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
 * <p>An interceptor binding type is a Java annotation annotated 
 * {@link javax.interceptor.InterceptorBinding &#064;InterceptorBinding}.</p>
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
 * <h4>Managed beans</h4>
 * 
 * <p>A managed bean is a bean that is implemented by a Java class. The basic 
 * lifecycle and semantics of managed beans are defined by the Managed Beans 
 * specification.</p>
 * 
 * <p>A top-level Java class is a managed bean if it is defined to be a managed 
 * bean by any other Java EE specification, or if it meets all of the following 
 * conditions:</p>
 * 
 * <ul>
 * <li>It is not a non-static inner class.</li>
 * <li>It is a concrete class, or is annotated 
 * {@link javax.decorator.Decorator &#064;Decorator}.</li>
 * <li>It is not annotated with an EJB component-defining annotation or declared 
 * as an EJB bean class in <tt>ejb-jar.xml</tt>.</li>
 * <li>It has an appropriate constructor; either the class has a constructor with 
 * no parameters, or the class declares a constructor annotated 
 * {@link javax.inject.Inject &#064;Inject}.</li>
 * </ul>
 *  
 * <p>All Java classes that meet these conditions are managed beans and thus no 
 * special declaration is required to define a managed bean.</p>
 * 
 * <p>If a managed bean has a public field, it must have scope 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <p>If the managed bean class is a generic type, it must have scope 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <h4>Session beans</h4>
 * 
 * <p>A session bean is a bean that is implemented by a session bean with an 
 * EJB 3.x client view. The basic lifecycle and semantics of an EJB session bean 
 * are defined by the EJB specification.</p>
 * 
 * <ul>
 * <li>A stateless session bean must belong to the 
 * {@link javax.enterprise.context.Dependent &#064;Dependent} pseudo-scope.</li>
 * <li>A  singleton bean must belong to either the 
 * {@link javax.enterprise.context.ApplicationScoped &#064;ApplicationScoped} 
 * scope or to the {@link javax.enterprise.context.Dependent &#064;Dependent}
 * pseudo-scope.</li>
 * <li>A stateful session bean may have any scope.</li>
 * </ul>
 * 
 * <p>If the session bean class is a generic type, it must have scope 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <p>If a session bean is a stateful session bean:</p>
 * 
 * <ul>
 * <li>If the scope is {@link javax.enterprise.context.Dependent &#064;Dependent}, 
 * the application may call any EJB remove method of a contextual instance of the 
 * session bean.</li>
 * <li>Otherwise, the application may not directly call any EJB remove method of 
 * any contextual instance of the session bean.</li>
 * </ul>
 * 
 * <h4>Producer methods and fields</h4>
 * 
 * <p>A {@linkplain javax.enterprise.inject.Produces producer method or field} 
 * acts as a source of objects to be injected, where:</p>
 * 
 * <ul>
 * <li>the objects to be injected are not required to be instances 
 * of beans, or</li>
 * <li>the concrete type of the objects to be injected may vary at
 * runtime, or</li>
 * <li>the objects require some custom initialization that is not 
 * performed by the bean constructor.</li>
 * </ul>
 * 
 * <p>A producer method or field is a method or field of a bean class annotated
 * {@link javax.enterprise..inject.Produces &#064;Produces}.</p>
 * 
 * <h4>Resources</h4>
 * 
 * <p>A resource is a bean that represents a reference to a resource, persistence 
 * context, persistence unit, remote EJB or web service in the Java EE component 
 * environment.</p> 
 * 
 * <p>A resource may be declared by specifying a Java EE component environment 
 * injection annotation as part of a producer field declaration.</p>
 * 
 * <ul>
 * <li>For a Java EE resource, <tt>&#064;Resource</tt> must be specified.</li> 
 * <li>For a persistence context, <tt>&#064;PersistenceContext</tt> must be specified. 
 * <li>For a persistence unit, <tt>&#064;PersistenceUnit</tt> must be specified. 
 * <li>For a remote EJB, <tt>&#064;EJB</tt> must be specified. 
 * <li>or a web service, <tt>&#064;WebServiceRef</tt> must be specified.
 * </ul>
 * 
 * <p>The injection annotation specifies the metadata needed to obtain the 
 * resources, entity manager, entity manager factory, remote EJB instance or 
 * web service reference from the component environment.</p>
 * 
 * <p>A resource may not have an EL name.</p>
 * 
 * <h3>Enabled and disabled beans</h3>
 * 
 * <p>A bean is said to be enabled if:</p>
 * 
 * <ul>
 * <li>it is deployed in a bean deployment archive (a module with a <tt>beans.xml</tt>
 * file), and</li>
 * <li>it is not a 
 * {@linkplain javax.enterprise.inject.Produces producer method or field} 
 * of a disabled bean, and</li>
 * <li>it is not {@linkplain javax.enterprise.inject.Specializes specialized} 
 * by any other enabled bean, and either</li>
 * <li>it is not an {@linkplain javax.enterprise.inject.Alternative alternative}, 
 * or it is a selected alternative of at least one bean deployment archive.</li>
 * </ul>
 * 
 * <p>Otherwise, the bean is said to be disabled.</p>
 * 
 * <h3>Eligible dependencies</h3>
 * 
 * <p>A bean is eligible for injection into a given class if it satisfies the 
 * following conditions:</p>
 * 
 * <ul>
 * <li>The bean is enabled.</li>
 * <li>The bean is not an interceptor or decorator.</li>
 * <li>The bean is either not an alternative, or is a selected alternative of 
 * the bean deployment archive of the given class.</li>
 * <li>In a Java EE or servlet container, the bean class is required to be 
 * accessible to the given class, according to the class loading requirements 
 * of the Java EE platform and Java Servlet specifications.</li>
 * </ul>
 * 
 * @see javax.enterprise.inject.Produces
 * @see javax.inject.Scope
 * @see javax.inject.Named
 * @see javax.inject.Qualifier
 * @see javax.interceptor.InterceptorBinding
 * 
 */
package javax.enterprise.inject;