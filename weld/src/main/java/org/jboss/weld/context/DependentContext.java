package org.jboss.weld.context;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.AlterableContext;

/**
 * <p>
 * The built in dependent context, associated with {@link Dependent}. It is always active.
 * </p>
 *
 * <p>
 * Weld comes with one Dependent context which can be injected using:
 * </p>
 *
 * <pre>
 * &#064; Inject DependentContext dependentContext;
 * </pre>
 *
 * @author Pete Muir
 *
 */
public interface DependentContext extends AlterableContext {

}
