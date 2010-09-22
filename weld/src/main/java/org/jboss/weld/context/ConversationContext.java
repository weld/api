package org.jboss.weld.context;

import java.util.Collection;

import javax.enterprise.context.Conversation;

import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.http.HttpConversationContext;

/**
 * <p>
 * The conversation context, providing various options for controlling (default)
 * conversation configuration.
 * </p>
 * 
 * <p>
 * Currently Weld comes with two implementation of the conversation context,
 * {@link HttpConversationContext} in which conversations are isolated to the
 * Http Session and {@link BoundConversationContext} in which conversations are
 * backed by a pair of maps.
 * </p>
 */
public interface ConversationContext extends ManagedContext
{

   /**
    * Cause any expired conversations to be ended, and therefore marked for
    * destruction when deactivate is called.
    * 
    */
   public void invalidate();

   /**
    * Activate the conversation context, using the id provided to attempt to
    * restore a long-running conversation
    * 
    * @param cid if the cid is null, a transient conversation will be created,
    *           otherwise the conversation will be restored
    */
   public void activate(String cid);

   /**
    * Activate the conversation context, starting a new transient conversation
    * 
    */
   public void activate();

   /**
    * Set the name of the parameter used to propagate the conversation id
    * 
    * @param cid the name of the conversation id parameter
    */
   public void setParameterName(String cid);

   /**
    * Get the name of the parameter used to propagate the conversation id
    * 
    * @return the name of the conversation id parameter
    */
   public String getParameterName();

   /**
    * Set the concurrent access timeout
    * 
    * @param timeout the timeout (in ms) for the concurrent access lock
    */
   public void setConcurrentAccessTimeout(long timeout);

   /**
    * Get the current concurrent access timeout
    * 
    * @return the timeout (in ms) for the concurrent access lock
    */
   public long getConcurrentAccessTimeout();

   /**
    * Set the default inactivity timeout. This may be overridden on a per
    * conversation basis using {@link Conversation#setTimeout(long)}
    * 
    * @param timeout the default inactivity timeout (in ms)
    */
   public void setDefaultTimeout(long timeout);

   /**
    * Get the default inactivity timeout. This may have been overridden on a per
    * conversation basis.
    * 
    * @return the default inactivity timeout (in ms)
    */
   public long getDefaultTimeout();

   /**
    * Get conversations currently known to the context. This will include any
    * long running conversations accessible, as well as any transient
    * conversations which have yet to be destroyed.
    * 
    * @return a collection containing the conversations known
    */
   public Collection<ManagedConversation> getConversations();

   /**
    * Get the conversation with the given id.
    * 
    * @param id the id of the conversation to get
    * @return the conversation, or null if no conversation is known
    */
   public ManagedConversation getConversation(String id);

   /**
    * Generate a new, unique, conversation id
    * 
    * @return
    */
   public String generateConversationId();

   /**
    * Get a handle the current conversation (transient or otherwise).
    * 
    * @return
    */
   public ManagedConversation getCurrentConversation();

}
