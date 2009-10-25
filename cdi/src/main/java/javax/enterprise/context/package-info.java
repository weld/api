/**
 * <p>Annotations and interfaces relating to scopes and contexts.</p>
 * 
 * <p>Associated with every scope type is a context object. The 
 * context object determines the lifecycle and visibility of
 * instances of all beans with that scope. In particular, the 
 * context object defines:</p>
 * 
 * <ul>
 * <li>When a new instance of any bean with that scope is created</li>
 * <li>When an existing instance of any bean with that scope is 
 * destroyed</li>
 * <li>Which injected references refer to any instance of a bean 
 * with that scope</li>
 * </ul>
 * 
 * <p>The context implementation collaborates with the container via 
 * the {@link javax.enterprise.context.spi.Context Context} and 
 * {@link javax.enterprise.context.spi.Contextual Contextual} 
 * interfaces to create and destroy contextual instances.</p>
 * 
 * <h3>Built-in scopes</h3>
 * 
 * <p>The following built-in scopes are provided:
 * {@link javax.enterprise.context.Dependent &#064;Dependent},
 * {@link javax.enterprise.context.RequestScoped &#064;RequestScoped},
 * {@link javax.enterprise.context.ConversationScoped &#064;ConversationScoped},
 * {@link javax.enterprise.context.SessionScoped &#064;SessionScoped},
 * {@link javax.enterprise.context.ApplicationScoped &#064;ApplicationScoped},
 * {@link javax.inject.Singleton &#064;Singleton}.</p>
 * 
 * <p>The container provides an implementation of the <tt>Context</tt> 
 * interface for each of the built-in scopes. The built-in request, 
 * session, and application contexts support servlet, web service 
 * and EJB invocations. The built-in conversation context supports
 * JSF requests.</p>
 * 
 * <p>For other kinds of invocations, a portable extension may define 
 * a custom context object for any or all of the built-in scopes. For 
 * example, a third-party web application framework might provide a 
 * conversation context object for the built-in conversation scope.</p>
 * 
 * <p>For each of the built-in normal scopes, contexts propagate across 
 * any Java method call, including invocation of EJB local business methods. 
 * The built-in contexts do not propagate across remote method invocations 
 * or to asynchronous processes such as JMS message listeners or EJB timer 
 * service timeouts.</p>
 * 
 * <h3>Normal scopes and pseudo-scopes</h3>
 * 
 * <p>Most scopes are normal scopes. The context object for a normal 
 * scope type is a mapping from each contextual type with that scope 
 * to an instance of that contextual type. There may be no more than 
 * one mapped instance per contextual type per thread. The set of all 
 * mapped instances of contextual types with a certain scope for a 
 * certain thread is called the context for that scope associated with 
 * that thread.</p>
 * 
 * <p>A context may be associated with one or more threads. A context 
 * with a certain scope is said to propagate from one point in the 
 * execution of the program to another when the set of mapped instances 
 * of contextual types with that scope is preserved.</p>
 * 
 * <p>The context associated with the current thread is called the 
 * current context for the scope. The mapped instance of a contextual 
 * type associated with a current context is called the current instance 
 * of the contextual type.<p>
 * 
 * <p>Contexts with normal scopes must obey the following rule:</p>
 * 
 * <p><em>Suppose beans A, B and Z all have normal scopes. Suppose A 
 * has an injection point x, and B has an injection point y. Suppose 
 * further that both x and y resolve to bean Z according to the rules 
 * of typesafe resolution. If a is the current instance of A, and b 
 * is the current instance of B, then both a.x and b.y refer to the 
 * same instance of Z. This instance is the current instance of Z.</em></p>
 * 
 * <p>Any scope that is not a normal scope is called a pseudo-scope. 
 * The concept of a current instance is not well-defined in the case 
 * of a pseudo-scope.</p>
 * 
 * <p>All normal scopes must be explicitly declared 
 * {@link javax.enterprise.context.NormalScope &#064;NormalScope}. All 
 * pseudo-scopes must be explicitly declared 
 * {@link javax.inject.Scope &#064;Scope}.</p> 
 * 
 * <p>All built-in scopes are normal scopes, except for the 
 * {@link javax.enterprise.context.Dependent &#064;Dependent} and
 * {@link javax.inject.Singleton &#064;Singleton} pseudo-scopes.</p>
 * 
 * <h3>Contextual reference validity</h3>
 * 
 * <p>A reference to a bean obtained from the container via injection
 * or programmatic lookup is called a contextual reference. A contextual
 * reference for a bean with a normal scope refers to the current instance
 * of the bean. Contextual reference of a bean are valid only for a 
 * certain period of time. The application should not invoke a method of 
 * an invalid reference.</p>
 * 
 * <p>The validity of a contextual reference depends upon whether the 
 * scope of the injected bean is a normal scope or a pseudo-scope:</p>
 * 
 * <ul>
 * <li>Any reference to a bean with a normal scope is valid as long as 
 * the application maintains a hard reference to it. However, it may 
 * only be invoked when the context associated with the normal scope is 
 * active. If it is invoked when the context is inactive, a 
 * {@link javax.enterprise.context.ContextNotActiveException} is thrown 
 * by the container.</li>
 * <li>Any reference to a bean with a pseudo-scope is valid until the 
 * bean instance to which it refers is destroyed. It may be invoked 
 * even if the context associated with the pseudo-scope is not active. 
 * If the application invokes a method of a reference to an instance 
 * that has already been destroyed, the behavior is undefined.</li>
 * </ul>
 * 
 */
package javax.enterprise.context;