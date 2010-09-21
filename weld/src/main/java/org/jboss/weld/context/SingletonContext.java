package org.jboss.weld.context;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.Context;

/**
 * <p>
 * The built in singleton context, associated with {@link Singleton}.
 * It is always active (not managed) and is backed by an application scoped
 * singleton.
 * </p>
 * 
 * <p>
 * Weld comes with one Singleton context which can be injected using:
 * </p>
 * 
 * <pre>
 * &#064Inject SingletonContext singletonContext;
 * </pre>
 * 
 * @author Pete Muir
 * @see SingletonContext
 * @see ApplicationScoped
 * 
 */
public interface SingletonContext extends Context
{

}
