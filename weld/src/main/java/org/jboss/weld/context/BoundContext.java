package org.jboss.weld.context;

import javax.enterprise.context.spi.AlterableContext;

/**
 * <p>
 * Allows a thread-based context to be bound to some external instance storage (such as an HttpSession).
 * </p>
 *
 * <p>
 * A context may be <em>detachable</em> in which case a call to {@link ManagedContext#invalidate()} will detach the context from
 * it's associated storage. A detached context is still usable (instances may be added or removed) however changes will not be
 * written through to the underlying data store.
 * </p>
 *
 * <p>
 * Normally, a detachable context will have it's underlying bean store attached on a call to {@link ManagedContext#activate()}
 * and detached on a call to {@link ManagedContext#deactivate()} however a subtype of {@link BoundContext} may change this
 * behavior.
 * </p>
 *
 * <p>
 * If you call {@link #associate(Object)} you must ensure that you call {@link #dissociate(Object)} in all cases, otherwise you
 * risk memory leaks.
 * </p>
 *
 * @author Pete Muir
 *
 * @param <S> the type of the external instance storage
 * @see ManagedContext
 */
public interface BoundContext<S> extends AlterableContext {

    /**
     * Associate the context with the storage (for this thread). Once {@link #associate(Object)} has been called, further calls
     * to {@link #associate(Object)} will be ignored, until the context has been subsequently {@link #dissociate(Object)} from
     * the storage.
     *
     * @param storage the external storage
     * @return true if the storage was attached, otherwise false
     */
    boolean associate(S storage);

    /**
     * Dissociate the context from the storage (for this thread). The context will only dissociate from the same storage it
     * associated with.
     *
     * @param storage the external storage
     * @return true if the storage was dissociated
     */
    boolean dissociate(S storage);

}
