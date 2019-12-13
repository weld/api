package org.jboss.weld.context;

import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.interceptor.InvocationContext;
import javax.servlet.ServletRequest;

import org.jboss.weld.context.bound.BoundRequestContext;
import org.jboss.weld.context.ejb.EjbRequestContext;
import org.jboss.weld.context.http.HttpRequestContext;

/**
 * <p>
 * The built in request context is associated with {@link RequestScoped} and is a managed context which can be activated,
 * invalidated and deactivated.
 * </p>
 *
 * <p>
 * Weld comes with four implementation of the request context. The {@link HttpRequestContext}, in which conversations are bound
 * to the {@link ServletRequest}, can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Http RequestContext requestContext;
 * </pre>
 *
 * <p>
 * Alternatively the {@link BoundRequestContext} in which requests are bound a {@link Map} can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Bound RequestContext requestContext;
 * </pre>
 *
 * <p>
 * Additionally, Weld provides an unbound request context (which is automatially bound to the thread) which can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Unbound RequestContext requestContext;
 * </pre>
 *
 * <p>
 * Finally, Weld provides a request context which is bound to an {@link InvocationContext} and can be injected:
 * </p>
 *
 *
 * <pre>
 * &#064; Inject &#064; Ejb RequestContext requestContext;
 * </pre>
 *
 * @author Pete Muir
 * @see BoundRequestContext
 * @see HttpRequestContext
 * @see EjbRequestContext
 * @see RequestScoped
 *
 */
public interface RequestContext extends ManagedContext {

}
