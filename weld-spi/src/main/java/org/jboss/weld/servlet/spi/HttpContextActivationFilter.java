package org.jboss.weld.servlet.spi;

import javax.servlet.http.HttpServletRequest;

import org.jboss.weld.bootstrap.api.Service;

/**
 * Enables an integrator to control if CDI contexts should be activated for a particular {@link HttpServletRequest}. An integrator may be interested in filtering
 * out requests where CDI is not necessary to eliminate the overhead of context activation/deactivation. For example, an integrator may want to use this filter
 * to filter out static resource requests from CDI processing.
 *
 * This service is optional. If not present, every request is accepted.
 *
 * @author Jozef Hartinger
 *
 */
public interface HttpContextActivationFilter extends Service {

    /**
     * Determines whether CDI contexts should be active during processing of this request
     *
     * @param request the request
     * @return true if CDI contexts should be active during processing of this request
     */
    boolean accepts(HttpServletRequest request);
}
