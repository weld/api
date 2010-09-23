package org.jboss.weld.context.ejb;

import javax.interceptor.InvocationContext;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.RequestContext;

/**
 * <p>
 * A request context which can be bound to the {@link InvocationContext}. The
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
public interface EjbRequestContext extends RequestContext, BoundContext<InvocationContext>
{

}
