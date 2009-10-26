/**
 * <p>Annotations and interfaces relating to scopes and contexts.</p>
 * 
 * <p>A scope type is a Java annotation annotated 
 * {@link javax.inject.Scope &#064;Scope} or 
 * {@link javax.enterprise.context.NormalScope &#064;NormalScope}.</p>
 * 
 * <p>Associated with every scope type is a 
 * {@linkplain javax.enterprise.context.spi.Context context object}. 
 * The context object determines the lifecycle and visibility of
 * instances of all {@linkplain javax.enterprise.inject beans} with 
 * that scope. In particular, the context object defines:</p>
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
 * <p>Most scopes are <em>normal scopes</em>. The 
 * {@linkplain javax.enterprise.context.spi.Context context object} for 
 * a normal scope type is a mapping from each contextual type with that 
 * scope to an instance of that contextual type. There may be no more than 
 * one mapped instance per contextual type per thread. The set of all 
 * mapped instances of contextual types with a certain scope for a 
 * certain thread is called the <em>context</em> for that scope associated 
 * with that thread.</p>
 * 
 * <p>A context may be associated with one or more threads. A context 
 * with a certain scope is said to <em>propagate</em> from one point in the 
 * execution of the program to another when the set of mapped instances 
 * of contextual types with that scope is preserved.</p>
 * 
 * <p>The context associated with the current thread is called the 
 * <em>current context</em> for the scope. The mapped instance of a 
 * contextual type associated with a current context is called the 
 * <em>current instance</em> of the contextual type.<p>
 * 
 * <p>Any scope that is not a normal scope is called a <em>pseudo-scope</em>. 
 * The concept of a current instance is not well-defined in the case of 
 * a pseudo-scope.</p>
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
 * <h3>Contextual and injected reference validity</h3>
 * 
 * <p>A reference to a bean obtained from the container via {@linkplain 
 * javax.enterprise.inject.Instance programmatic lookup} is called a 
 * contextual reference. A contextual reference for a bean with a normal 
 * scope refers to the current instance of the bean. A contextual 
 * reference for a bean are valid only for a certain period of time. The 
 * application should not invoke a method of an invalid reference.</p>
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
 * <p>A reference to a bean obtained from the container via {@linkplain 
 * javax.inject.Inject dependency injection} is a special kind of
 * contextual reference, called an injected reference. Additional
 * restrictions apply to the validity of an injected reference:</p>
 * 
 * <ul>
 * <li>A reference to a bean injected into a field, bean constructor or 
 * initializer method is only valid until the object into which it was 
 * injected is destroyed.</li>
 * <li>A reference to a bean injected into a producer method is only 
 * valid until the producer method bean instance that is being produced 
 * is destroyed.<li>
 * <li>A reference to a bean injected into a disposer method or observer 
 * method is only valid until the invocation of the method completes.</li>
 * </ul>
 * 
 */
package javax.enterprise.context;