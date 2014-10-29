package org.jboss.weld.context.bound;

import java.util.Map;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.ConversationContext;

/**
 * <p>
 * A conversation context which can be bound to a pair of Maps encapsulated by {@link BoundRequest}. The context is
 * automatically attached to the bound request on activation, and detached when {@link #invalidate()} is called.
 * </p>
 *
 * <p>
 * The {@link BoundConversationContext} is detachable, and transient conversations are only attached at the end of a request.
 * </p>
 *
 * <p>
 * This context is not thread safe, and provides no thread safety for the underlying map. A thread-safe map can be used to back
 * the context - in this case the map can be used as an underlying store in multiple threads safely.
 * </p>
 *
 *
 * @author Pete Muir
 *
 */
public interface BoundConversationContext extends ConversationContext, BoundContext<BoundRequest> {

    /**
     * Destroy all conversations in the session.
     *
     * @param session the session for which to destroy all conversations
     * @return true if all conversations in the session have been destroyed
     */
    boolean destroy(Map<String, Object> session);

}
