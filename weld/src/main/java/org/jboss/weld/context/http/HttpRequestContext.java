package org.jboss.weld.context.http;

import javax.servlet.ServletRequest;

import org.jboss.weld.context.BoundContext;
import org.jboss.weld.context.RequestContext;

public interface HttpRequestContext extends BoundContext<ServletRequest>, RequestContext
{

}
