package org.jboss.weld.context.bound;

import java.util.Map;

/**
 * An implementation of {@link BoundRequest} backed by a pair of maps.
 *
 * @author Pete Muir
 *
 */
public class MutableBoundRequest implements BoundRequest {

    private final Map<String, Object> requestMap;
    private final Map<String, Object> sessionMap;

    /**
     * The parameters are a backing storage for current request and session respectively
     *
     * @param requestMap represents backing storage for current request
     * @param sessionMap represents backing storage for current session
     */
    public MutableBoundRequest(Map<String, Object> requestMap, Map<String, Object> sessionMap) {
        this.requestMap = requestMap;
        this.sessionMap = sessionMap;
    }

    public Map<String, Object> getRequestMap() {
        return requestMap;
    }

    public Map<String, Object> getSessionMap(boolean create) {
        return sessionMap;
    }

}
