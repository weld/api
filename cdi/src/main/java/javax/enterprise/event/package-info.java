/**
 * <p>This contains annotations and interfaces relating to events.<p>
 * 
 * <p>Beans may produce and consume events. This facility allows 
 * beans to interact in a completely decoupled fashion, with no 
 * compile-time dependency between the two beans. Most importantly, 
 * it allows stateful beans in one architectural or physical tier 
 * of the application to synchronize their internal state with 
 * state changes that occur in a different tier.</p>
 * 
 * <p>An event comprises:</p>
 * 
 * <ul>
 * <li>A Java object—the event object</li>
 * <li>A (possibly empty) set of instances of qualifier types—the event qualifiers</li>
 * </ul>
 * 
 * <p>The event object acts as a payload, to propagate state from 
 * producer to consumer. The event qualifiers act as topic selectors, 
 * allowing the consumer to narrow the set of events it observes.</p>
 * 
 * <p>An event object is an instance of a concrete Java class with 
 * no type variables. The event types of the event include all 
 * superclasses and interfaces of the runtime class of the event object.
 * An event type may not contain a type variable.</p>
 * 
 * <p>An event qualifier type is just an ordinary qualifier type</p>
 * 
 * <p>An {@linkplain javax.enterprise.event.Observes observer method} 
 * acts as event consumer, observing events of a specific type, the 
 * observed event type, with a specific set of qualifiers, the observed 
 * event qualifiers. Any Java type may be an observed event type.</p>
 * 
 * <p>An observer method will be notified of an event if the observed 
 * event type it specifies is one of the event types of the event, and 
 * if all the observed event qualifiers it specifies are event qualifiers 
 * of the event.</p>
 * 
 * @see javax.enterprise.event.Observes
 * @see javax.enterprise.event.Event
 */
package javax.enterprise.event;

