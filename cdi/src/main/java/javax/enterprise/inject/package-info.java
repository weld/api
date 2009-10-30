/**
 * <p>Annotations relating to bean and stereotype definition, 
 * built-in qualifiers, and interfaces and classes relating 
 * to programmatic lookup.</p>
 * 
 * <p>A bean is a source of contextual objects which define application 
 * state and/or logic. These objects are called contextual instances of 
 * the bean. The container creates and destroys these instances and 
 * associates them with the appropriate 
 * {@linkplain javax.enterprise.context.spi.Context context}. Contextual 
 * instances of a bean may be injected into other objects (including 
 * other bean instances) that execute in the same context, and may be 
 * used in Unified EL expressions that are evaluated in the same 
 * context.</p>
 * 
 * <p>The lifecycle of contextual instances is managed by the container 
 * according to the 
 * {@linkplain javax.enterprise.context lifecycle context model}. 
 * Annotations define the lifecycle of the bean and its interactions 
 * with other beans.</p>
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
 *       extends Business 
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
 * {@link javax.inject.Qualifier &#064;Qualifier}.
 * The qualifiers of a bean are declared by annotating the bean class 
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
 * <p>If a bean does not explicitly declare a qualifier other than 
 * {@link javax.inject.Named &#064;Named}, the bean has the qualifier 
 * {@link javax.enterprise.inject.Default &#064;Default}.</p>
 * 
 * <h3>Scope</h3>
 * 
 * <p>All beans have a {@linkplain javax.enterprise.context scope}. The 
 * scope of a bean determines the lifecycle of its instances, and which 
 * instances of the bean are visible to instances of other beans.</p>
 * 
 * <p>A scope type is a Java annotation annotated 
 * {@link javax.inject.Scope &#064;Scope} or 
 * {@link javax.enterprise.context.NormalScope &#064;NormalScope}.
 * The scope of a bean is defined by annotating the bean class or producer 
 * method or field with a scope type or with a stereotype that declares a 
 * default scope.</p>
 * 
 * <pre>
 * &#064;ConversationScoped 
 * public class Order { ... } 
 * </pre>
 * 
 * <p>A bean class or producer method or field may specify at most one 
 * scope type annotation.</p>
 * 
 * <p>If the bean does not explicitly declare a scope or a stereotype
 * with a default scope, the scope defaults to 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <h3>Bean EL name</h3>
 * 
 * <p>A bean may have a bean EL name. A bean with an EL name may be referred 
 * to by its name in {@linkplain javax.el Unified EL} expressions. A valid 
 * bean EL name is a period-separated list of valid EL identifiers.</p>
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
 * <p>{@linkplain javax.interceptor Interceptors} may be bound to any managed 
 * bean that is not itself an interceptor or decorator or to any EJB session 
 * or message-driven bean. An interceptor that is annotated 
 * {@link javax.interceptor.Interceptor &#064;Interceptor} may be identified 
 * by its interceptor bindings.</p>
 * 
 * <pre>
 * &#064;Transactional &#064;Interceptor
 * public class TransactionInterceptor {
 *    &#064;AroundInvoke 
 *    public Object manageTransaction(InvocationContext ctx) { ... }
 * }
 * </pre>
 * 
 * <p>An interceptor binding type is a Java annotation annotated 
 * {@link javax.interceptor.InterceptorBinding &#064;InterceptorBinding}.
 * An interceptor binding of a bean may be declared by annotating the bean 
 * class, or a method of the bean class, with an interceptor binding type
 * or with a stereotype that declares the interceptor binding.</p>
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
 * <p>If a managed bean class is declared final, it may not have any interceptor 
 * bindings. If a managed bean has a non-static, non-private, final method, it 
 * may not have any class-level interceptor bindings, and that method may not
 * have any method-level interceptor bindings.</p>
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
 * <li>It does not implement {@link javax.enterprise.inject.spi.Extension}.</li>
 * <li>It has an appropriate constructor; either the class has a constructor with 
 * no parameters, or the class declares a constructor annotated 
 * {@link javax.inject.Inject &#064;Inject}.</li>
 * </ul>
 *  
 * <p>All Java classes that meet these conditions are managed beans and thus no 
 * special declaration is required to define a managed bean. Optionally, a
 * managed bean may be annotated {@link javax.annotation.ManagedBean}.</p>
 * 
 * <p>If a managed bean has a public field, it must have scope 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <p>If the managed bean class is a generic type, it must have scope 
 * {@link javax.enterprise.context.Dependent &#064;Dependent}.</p>
 * 
 * <h4>Session beans</h4>
 * 
 * <p>The basic lifecycle and semantics of EJB session beans are defined by the 
 * EJB specification.</p>
 * 
 * <ul>
 * <li>A {@linkplain javax.ejb.Stateless stateless session bean} must belong to the 
 * {@link javax.enterprise.context.Dependent &#064;Dependent} pseudo-scope.</li>
 * <li>A {@linkplain javax.ejb.Singleton singleton bean} must belong to either the 
 * {@link javax.enterprise.context.ApplicationScoped &#064;ApplicationScoped} 
 * scope or to the {@link javax.enterprise.context.Dependent &#064;Dependent}
 * pseudo-scope.</li>
 * <li>A {@linkplain javax.ejb.Stateful stateful session bean} may have any scope.</li>
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
 * {@link javax.enterprise.inject.Produces &#064;Produces}.</p>
 * 
 * <p>A common pattern in generic code is a producer method that injects an 
 * {@link javax.enterprise.inject.spi.InjectionPoint} object.</p>
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
 * <pre>
 * &#064;Produces &#064;WebServiceRef(lookup="java:app/service/PaymentService")
 * PaymentService paymentService;
 * </pre>
 * 
 * <pre>
 * &#064;Produces &#064;EJB(ejbLink="../their.jar#PaymentService")
 * PaymentService paymentService;
 * </pre>
 * 
 * <pre>
 * &#064;Produces &#064;Resource(lookup="java:global/env/jdbc/CustomerDatasource")
 * &#064;CustomerDatabase Datasource customerDatabase;
 * </pre>
 * 
 * <pre>
 * &#064;Produces &#064;PersistenceContext(unitName="CustomerDatabase")
 * &#064;CustomerDatabase EntityManager customerDatabasePersistenceContext;
 * </pre>
 * 
 * <pre>
 * &#064;Produces &#064;PersistenceUnit(unitName="CustomerDatabase")
 * &#064;CustomerDatabase EntityManagerFactory customerDatabasePersistenceUnit;
 * </pre>
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
 * <h3>Inter-module injection</h3>
 * 
 * <p>A bean is available for injection in a certain Java EE module or library if:</p>
 * 
 * <ul>
 * <li>the bean is enabled,</li>
 * <li>the bean is either not an alternative, or the module or library is a bean 
 * deployment archive and the bean is a selected alternative of the bean deployment 
 * archive, and</li>
 * <li>the bean class is required to be accessible to classes in the module or 
 * library, according to the class loading requirements of the Java EE platform and 
 * Java Servlet specifications.</li>
 * </ul>
 * 
 * <h3>Dependency injection</h3>
 * 
 * <p>A bean is assignable to a given injection point if:</p>
 * 
 * <ul>
 * <li>The bean has a bean type that matches the type of the injection point. For 
 * this purpose, primitive types are considered to match their corresponding wrapper 
 * types in {@link java.lang} and array types are considered to match only if their 
 * element types are identical.</li>
 * <li>The bean has all the qualifiers of the injection point. If the injection point
 * does not explicitly declare a qualifier, it has the default qualifier 
 * {@link javax.enterprise.inject.Default &#064;Default}.</li>
 * <li>The bean is eligible for injection into the class that declares the injection 
 * point.</li>
 * </ul>
 * 
 * <p>A bean is eligible for injection into a given injection point if:</p>
 * 
 * <ul>
 * <li>it is available for injection in the Java EE module or library that contains 
 * the class that declares the injection point, and</li>
 * <li>it is assignable to the injection point.</li>
 * </ul>
 * 
 * <p>If more than one bean is eligible for injection to the injection point, the
 * container attempts to resolve the ambiguity by eliminating all beans which are 
 * not alternatives.</p>
 * 
 * <p>Certain legal bean types cannot be proxied by the container:</p>
 * 
 * <ul>
 * <li>classes which don't have a non-private constructor with no parameters,</li>
 * <li>classes which are declared final or have final methods,</li>
 * <li>primitive types,</li>
 * <li>and array types.</li>
 * </ul>
 * 
 * <p>An injection point whose declared type cannot be proxied by the 
 * container must not resolve to a bean with a 
 * {@linkplain javax.enterprise.context normal scope}.</p>
 * 
 * <h3>EL name resolution</h3>
 * 
 * <p>EL names are resolved when Unified EL expressions are evaluated. An EL name 
 * resolves to a bean if:</p>
 * 
 * <ul>
 * <li>The bean has the given EL name.</li>
 * <li>the bean is available for injection in the war containing the JSP or JSF 
 * page with the EL expression.</li>
 * </ul>
 * 
 * <p>If an EL name resolves to more than one bean, the container attempts to resolve 
 * the ambiguity by eliminating all beans which are not alternatives.</p>
 * 
 * <h3>Enabled interceptors</h3>
 * 
 * <p>By default, a bean deployment archive has no enabled interceptors. An 
 * interceptor must be explicitly enabled by listing its bean class under the 
 * <tt>&lt;interceptors&gt;</tt> element of the <tt>beans.xml</tt> file of the 
 * bean deployment archive. The order of the interceptor declarations determines 
 * the interceptor ordering. Interceptors which occur earlier in the list are 
 * called first.</p>
 * 
 * <p>An interceptor is bound to a bean if:</p>
 * 
 * <ul>
 * <li>The bean has all the interceptor bindings of the interceptor.</li>
 * <li>The interceptor is enabled in the bean deployment archive of 
 * the bean.</li>
 * </ul>
 * 
 * <p>An interceptor instance is a
 * {@linkplain javax.enterprise.context.Dependent dependent object} 
 * of the object it intercepts.</p>
 * 
 * @see javax.enterprise.context
 * @see javax.inject
 * @see javax.interceptor
 * @see javax.decorator
 * @see javax.enterprise.event
 * 
 * @see javax.enterprise.inject.Produces
 * @see javax.enterprise.inject.Alternative
 * 
 */
package javax.enterprise.inject;