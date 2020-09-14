package org.jboss.weld.context.http;

import java.util.function.Consumer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.ConversationContext;

/**
 * An Http Session backed conversation context. A transient conversation will be detached from the underlying session. If the
 * conversation is promoted to long running, context will be attached to the underlying Http Session at the end of the request.
 *
 * @author Pete Muir
 *
 */
public interface HttpConversationContext extends BoundContext<HttpServletRequest>, ConversationContext {

    /**
     * <p>
     * If the context is not currently associated with a {@link HttpServletRequest}, then the context will be associated with
     * the specified {@link HttpSession} (for this thread), activated, destroyed, and then deactivated. Any conversations
     * associated with the context will also be destroyed.
     * </p>
     *
     * <p>
     * If the context is already associated with a {@link HttpServletRequest} then this call will detach the context from the
     * underlying Http Session, and mark the context for destruction when the request is destroyed.
     * </p>
     *
     * <p>
     * This will cause any transient conversations, and any long running conversations associated with the session, to be
     * destroyed.
     * </p>
     *
     * @param session the {@link HttpSession} in which to store the bean instances
     * @return true if the context was destroyed immediately
     */
    boolean destroy(HttpSession session);

    /**
     * <p>
     * Activate the conversation context lazily - neither determine the conversation id, nor initialize the context. The context is only initialized when a
     * conversation-scoped bean is accessed for the first time. The callback, if specified, is executed during initialization of a transient conversation. The
     * implementation must throw a {@link RuntimeException} if the lazy initialization is not supported.
     * </p>
     *
     * @param transientConversationInitializationCallback the callback which is invoked during initialization of a transient conversation
     */
    default void activateLazily(Consumer<HttpServletRequest> transientConversationInitializationCallback) {
        throw new UnsupportedOperationException();
    }

}
