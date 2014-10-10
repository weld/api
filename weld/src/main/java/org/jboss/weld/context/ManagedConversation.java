package org.jboss.weld.context;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.Conversation;

/**
 * <p>
 * Provides management operations for conversations, including locking, and expiration management.
 * </p>
 *
 * @author Pete Muir
 * @see ConversationContext
 *
 */
public interface ManagedConversation extends Conversation {

    /**
     * Attempts to unlock the conversation
     *
     * @throws ContextNotActiveException if the conversation context is not active
     * @return true if the unlock was successful, false otherwise
     */
    boolean unlock();

    /**
     * Attempts to lock the conversation for exclusive usage
     *
     * @param timeout The time in milliseconds to wait on the lock
     * @return True if lock was successful, false otherwise
     * @throws InterruptedException if the lock operation was unsuccessful
     * @throws ContextNotActiveException if the conversation context is not active
     */
    boolean lock(long timeout) throws InterruptedException;

    /**
     * Gets the last time the conversation was used (for data access)
     *
     * @return time (in ms) since the conversation was last used
     * @throws ContextNotActiveException if the conversation context is not active
     */
    long getLastUsed();

    /**
     * Touches the managed conversation, updating the "last used" timestamp
     *
     * @throws ContextNotActiveException if the conversation context is not active
     */
    void touch();

}
