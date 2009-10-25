/**
 * <p>Annotations and interfaces relating to events.<p>
 * 
 * <p>{@linkplain javax.enterprise.inject Beans} may produce and 
 * consume events. Events allows beans to interact in a completely 
 * decoupled fashion, with no compile-time dependency between the 
 * interacting beans. Most importantly, it allows stateful beans 
 * in one architectural tier of the application to synchronize 
 * their internal state with state changes that occur in a 
 * different tier.</p>
 * 
 * <p>An event comprises:</p>
 * 
 * <ul>
 * <li>A Java object, called the event object</li>
 * <li>A (possibly empty) set of instances of qualifier types, called 
 * the event qualifiers</li>
 * </ul>
 * 
 * <h3>Event objects and event types</h3>
 * 
 * <p>The event object acts as a payload, to propagate state from 
 * producer to consumer. An event object is an instance of a concrete 
 * Java class with no type variables.</p> 
 * 
 * <p>The event types of the event include all superclasses and 
 * interfaces of the runtime class of the event object. An event type 
 * may not contain a type variable.</p>
 * 
 * <h3>Event qualifiers</h3>
 * 
 * <p>The event qualifiers act as topic selectors, allowing the consumer 
 * to narrow the set of events it observes. An event qualfier may be an
 * instance of any {@linkplain javax.inject.Qualifier qualifier type}.</p>
 * 
 * <h3>Observer methods</h3>
 * 
 * <p>An {@linkplain javax.enterprise.event.Observes observer method} 
 * allows the application to receive and respond to event notifications. 
 * It acts as event consumer, observing events of a specific type, with a 
 * specific set of qualifiers. Any Java type may be observed by an 
 * observer method.</p>
 * 
 * <p>An observer method is a method of a bean class with a parameter
 * annotated {@link javax.enterprise.event.Observes &#064;Observes}.</p>
 * 
 * <p>An observer method will be notified of an event if:</p> 
 * 
 * <ul>
 * <li>the type observed by the observer method is one of the event types 
 * of the event, and</li>
 * <li>the observer method has all the event qualifiers of the event.</li>
 * </ul>
 * 
 * <p>The order in which observer methods are called is not defined, and 
 * so portable applications should not rely upon the order in which 
 * observers are called.</p>
 * 
 * <p>Observer methods may throw exceptions:</p>
 * 
 * <ul>
 * <li>If the observer method is a 
 * {@linkplain javax.enterprise.event.TransactionPhase transactional 
 * observer method}, any exception is caught and logged by the container.</li>
 * <li>Otherwise, the exception aborts processing of the event.
 * No other observer methods of that event will be called. The 
 * exception is rethrown. If the exception is a checked exception, 
 * it is wrapped and rethrown as an (unchecked) 
 * {@link javax.enterprise.event.ObserverException}.</li>
 * </ul>
 * 
 * @see javax.enterprise.event.Observes
 * @see javax.enterprise.event.Event
 * @see javax.inject.Qualifier
 */
package javax.enterprise.event;

