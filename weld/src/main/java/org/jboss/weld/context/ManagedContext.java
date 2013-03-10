package org.jboss.weld.context;

import javax.enterprise.context.spi.AlterableContext;

/**
 * <p>
 * Lifecycle management for built in contexts. {@link ManagedContext} only allows a context to be activated, deactivated and
 * destroyed. It does not allow the context to be associated with an underlying data store. These operations are defined on
 * {@link BoundContext}.
 * </p>
 *
 * <p>
 * Weld provides a number of managed contexts: {@link SessionContext}, {@link ConversationContext}, {@link RequestContext}. All
 * these managed contexts are scoped to the thread, and propagation of the backing store between threads is the responsibility
 * of the managed context user.
 * </p>
 *
 * @author Pete Muir
 * @see BoundContext
 *
 */
public interface ManagedContext extends AlterableContext {

    /**
     * Activate the Context.
     */
    void activate();

    /**
     * Deactivate the Context, destroying any instances if the context is invalid.
     */
    void deactivate();

    /**
     * Mark the context as due for destruction when deactivate is called.
     */
    void invalidate();

}
