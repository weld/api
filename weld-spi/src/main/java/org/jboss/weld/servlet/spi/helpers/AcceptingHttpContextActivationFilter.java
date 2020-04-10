package org.jboss.weld.servlet.spi.helpers;

import jakarta.servlet.http.HttpServletRequest;

import org.jboss.weld.servlet.spi.HttpContextActivationFilter;

/**
 * A helper implementation of {@link HttpContextActivationFilter} that accepts every request.
 *
 * @author Jozef Hartinger
 *
 */
public class AcceptingHttpContextActivationFilter implements HttpContextActivationFilter {

    public static final AcceptingHttpContextActivationFilter INSTANCE = new AcceptingHttpContextActivationFilter();

    private AcceptingHttpContextActivationFilter() {
    }

    @Override
    public void cleanup() {
    }

    @Override
    public boolean accepts(HttpServletRequest request) {
        return true;
    }

}
