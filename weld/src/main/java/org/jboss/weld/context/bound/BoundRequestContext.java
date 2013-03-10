package org.jboss.weld.context.bound;

import java.util.Map;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.RequestContext;

/**
 * <p>
 * A request context which can be bound to any Map. The context is automatically attached to the map on activation, and detached
 * when {@link #invalidate()} is called.
 * </p>
 *
 * <p>
 * This context is not thread safe, and provides no thread safety for the underlying map. A thread-safe map can be used to back
 * the context.
 * </p>
 *
 *
 * @author Pete Muir
 *
 */
public interface BoundRequestContext extends RequestContext, BoundContext<Map<String, Object>> {

}
