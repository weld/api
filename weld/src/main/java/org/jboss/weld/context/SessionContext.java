package org.jboss.weld.context;

import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.servlet.http.HttpSession;

import org.jboss.weld.context.bound.BoundSessionContext;
import org.jboss.weld.context.http.HttpSessionContext;

/**
 * <p>
 * The built in session context is associated with {@link SessionScoped}. It can be activated, invalidated and deactivated.
 * </p>
 *
 * <p>
 * Weld comes with two implementation of the session context. The {@link HttpSessionContext}, in which conversations are bound
 * to the {@link HttpSession}, can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Http SessionContext sessionContext;
 * </pre>
 *
 * <p>
 * Alternatively the {@link BoundSessionContext} in which conversations are bound a {@link Map} can be injected:
 * </p>
 *
 * <pre>
 * &#064; Inject &#064; Bound SessionContext sessionContext;
 * </pre>
 *
 * @author Pete Muir
 * @see org.jboss.weld.context.bound.BoundSessionContext
 * @see jakarta.servlet.http.HttpSession
 * @see jakarta.enterprise.context.SessionScoped
 *
 */
public interface SessionContext extends ManagedContext {

}
