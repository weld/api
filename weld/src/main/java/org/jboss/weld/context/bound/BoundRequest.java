package org.jboss.weld.context.bound;

import java.util.Map;

/**
 * <p>
 * A conversation is used to span multiple requests, however is shorter than a
 * session. The {@link BoundConversationContext} uses one Map to represent a
 * request, and a second to represent the session, which are encapsulated in a
 * {@link BoundRequest}.
 * </p>
 * 
 * @author Pete Muir
 * 
 */
public interface BoundRequest
{

   /**
    * Get the current request map.
    * 
    * @return
    */
   public Map<String, Object> getRequestMap();

   /**
    * <p>
    * Get the current session map.
    * </p>
    * 
    * <p>
    * A {@link BoundRequest} may be backed by a data store that only creates
    * sessions on demand. It is recommended that if the session is not created
    * on demand, or that the session has already been created (but is not
    * required by this access) that the session is returned as it allows the
    * conversation context to work more efficiently.
    * </p>
    * 
    * @param create if true, then a session must be created
    * @return the session map; null may be returned if create is false
    */
   public Map<String, Object> getSessionMap(boolean create);

}
