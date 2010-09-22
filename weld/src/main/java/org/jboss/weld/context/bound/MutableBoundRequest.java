package org.jboss.weld.context.bound;

import java.util.Map;

public class MutableBoundRequest implements BoundRequest
{
   
   private final Map<String, Object> requestMap;
   private final Map<String, Object> sessionMap;

   public MutableBoundRequest(Map<String, Object> requestMap, Map<String, Object> sessionMap)
   {
      this.requestMap = requestMap;
      this.sessionMap = sessionMap;
   }

   public Map<String, Object> getRequestMap()
   {
      return requestMap;
   }

   public Map<String, Object> getSessionMap(boolean create)
   {
      return sessionMap;
   }

}
