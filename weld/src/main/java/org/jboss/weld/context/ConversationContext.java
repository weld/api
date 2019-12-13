package org.jboss.weld.context;

import java.util.Collection;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.BoundRequest;
import org.jboss.weld.context.http.HttpConversationContext;

/**
 * <p>
 * The built in conversation context is associated with {@link ConversationScoped}. It can be activated, invalidated and
 * deactivated. and provides various options for configuring conversation defaults.
 * </p>
 *
 * <p>
 * Weld comes with two implementation of the conversation context. The {@link HttpConversationContext}, in which conversations
 * are bound to the {@link HttpSession}, can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Http ConversationContext conversationContext;
 * </pre>
 *
 * <p>
 * Alternatively the {@link BoundConversationContext} in which conversations are bound a {@link BoundRequest} can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Bound ConversationContext conversationContext;
 * </pre>
 *
 * @author Pete Muir
 * @see BoundConversationContext
 * @see HttpConversationContext
 * @see ConversationScoped
 *
 */
public interface ConversationContext extends ManagedContext {

    /**
     * Cause any expired conversations to be ended, and therefore marked for destruction when deactivate is called.
     *
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    void invalidate();

    /**
     * Activate the conversation context, using the id provided to attempt to restore a long-running conversation
     *
     * @param cid if the cid is null, a transient conversation will be created, otherwise the conversation will be restored
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    void activate(String cid);

    /**
     * Activate the conversation context, starting a new transient conversation
     *
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    void activate();

    /**
     * Set the name of the parameter used to propagate the conversation id
     *
     * @param cid the name of the conversation id parameter
     */
    void setParameterName(String cid);

    /**
     * Get the name of the parameter used to propagate the conversation id
     *
     * @return the name of the conversation id parameter
     */
    String getParameterName();

    /**
     * Set the concurrent access timeout
     *
     * @param timeout the timeout (in ms) for the concurrent access lock
     */
    void setConcurrentAccessTimeout(long timeout);

    /**
     * Get the current concurrent access timeout
     *
     * @return the timeout (in ms) for the concurrent access lock
     */
    long getConcurrentAccessTimeout();

    /**
     * Set the default inactivity timeout. This may be overridden on a per conversation basis using
     * {@link Conversation#setTimeout(long)}
     *
     * @param timeout the default inactivity timeout (in ms)
     */
    void setDefaultTimeout(long timeout);

    /**
     * Get the default inactivity timeout. This may have been overridden on a per conversation basis.
     *
     * @return the default inactivity timeout (in ms)
     */
    long getDefaultTimeout();

    /**
     * Get conversations currently known to the context. This will include any non transient conversations, as well as any
     * conversations which were previously long running and have been made transient during this request.
     *
     * @return a collection containing the conversations
     *
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    Collection<ManagedConversation> getConversations();

    /**
     * Get the conversation with the given id.
     *
     * @param id the id of the conversation to get
     * @return the conversation, or null if no conversation is known
     *
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    ManagedConversation getConversation(String id);

    /**
     * Generate a new, unique, conversation id
     *
     * @return a new, unique conversation id
     *
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    String generateConversationId();

    /**
     * Get a handle the current conversation (transient or otherwise).
     *
     * @return the current conversation
     * @throws IllegalStateException if the context is unable to access the underlying data store
     */
    ManagedConversation getCurrentConversation();

}
