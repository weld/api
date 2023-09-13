package org.jboss.weld.context;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.spi.AlterableContext;
import jakarta.inject.Singleton;

/**
 * <p>
 * The built in singleton context, associated with {@link Singleton}. It is always active (not managed) and is backed by an
 * application scoped singleton.
 * </p>
 *
 * <p>
 * Weld comes with one Singleton context which can be injected using:
 * </p>
 *
 * <pre>
 * &#064;Inject
 * SingletonContext singletonContext;
 * </pre>
 *
 * @author Pete Muir
 * @see SingletonContext
 * @see ApplicationScoped
 *
 */
public interface SingletonContext extends AlterableContext {

    /**
     * Invalidate the context, causing all bean instances to be destroyed.
     */
    void invalidate();

}
