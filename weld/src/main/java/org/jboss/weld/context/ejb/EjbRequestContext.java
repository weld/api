package org.jboss.weld.context.ejb;

import javax.interceptor.InvocationContext;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.RequestContext;

public interface EjbRequestContext extends RequestContext, BoundContext<InvocationContext>
{

}
