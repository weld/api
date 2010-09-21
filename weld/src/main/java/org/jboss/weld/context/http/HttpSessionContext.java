package org.jboss.weld.context.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.SessionContext;

/**
 * <p>
 * A session context which can be bound to the {@link HttpServletRequest}. The
 * context is automatically attached to the map on activation, and detached when
 * {@link #invalidate()} is called.
 * </p>
 * 
 * <p>
 * This context is not thread safe, and provides no thread safety for the
 * underlying map.
 * </p>
 * 
 * 
 * @author Pete Muir
 * 
 */
public interface HttpSessionContext extends BoundContext<HttpServletRequest>, SessionContext
{

   /**
    * <p>
    * Mark the Session Context for destruction; the Session Context will be
    * detached from the underling Http Session, and instances marked for
    * destruction when the Http Request is destroyed.
    * </p>
    * 
    */
   public void invalidate();

   /**
    * <p>
    * Destroy the session and all conversations stored in the session.
    * </p>
    * 
    * <p>
    * If the context is not currently associated with a
    * {@link HttpServletRequest}, then the context will be associated with the
    * specified {@link HttpSession} (for this thread), activated, destroyed, and
    * then deactivated.
    * </p>
    * 
    * <p>
    * If the context is already associated with a {@link HttpServletRequest}
    * then this call will detach the context from the underlying Http Session,
    * and mark the context for destruction when the request is destroyed.
    * </p>
    * 
    * @param session the {@link HttpSession} in which to store the bean
    *           instances
    * @return true if the context was destroyed immediately
    */
   public boolean destroy(HttpSession session);

}
