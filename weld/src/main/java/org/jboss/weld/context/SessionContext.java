package org.jboss.weld.context;

import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.bound.BoundSessionContext;
import org.jboss.weld.context.http.HttpSessionContext;

/**
 * <p>
 * The built in session context is associated with {@link SessionScoped}. It can
 * be activated, invalidated and deactivated.
 * </p>
 * 
 * <p>
 * Weld comes with two implementation of the session context. The
 * {@link HttpSessionContext}, in which conversations are bound to the
 * {@link HttpSession}, can be injected:
 * </p>
 * 
 * <pre>
 * &#064Inject &#064Http SessionContext sessionContext;
 * </pre>
 * 
 * <p>
 * Alternatively the {@link BoundSessionContext} in which conversations are
 * bound a {@link Map} can be injected:
 * </p>
 * 
 * <pre>
 * &#064Inject &#064Bound SessionContext sessionContext;
 * </pre>
 * 
 * @author Pete Muir
 * @see BoundSessionContext
 * @see HttpSessionContext
 * @sees {@link SessionScoped}
 * 
 */
public interface SessionContext extends ManagedContext
{

}
