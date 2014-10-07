package org.jboss.weld.context;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.AlterableContext;

/**
 * <p>
 * The built in application context, associated with {@link ApplicationScoped}. It is always active (not managed) and is backed
 * by an application scoped singleton.
 * </p>
 *
 * <p>
 * Weld comes with one Application context which can be injected using:
 * </p>
 *
 * <pre>
 * &#064; Inject ApplicationContext applicationContext;
 * </pre>
 *
 * @author Pete Muir
 * @see SingletonContext
 * @see ApplicationScoped
 *
 */
public interface ApplicationContext extends AlterableContext {

    /**
     * Invalidate the context, causing all bean instances to be destroyed.
     */
    void invalidate();

}
