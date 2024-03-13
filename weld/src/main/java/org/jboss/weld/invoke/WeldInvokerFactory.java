package org.jboss.weld.invoke;

import jakarta.enterprise.inject.build.compatible.spi.BeanInfo;
import jakarta.enterprise.inject.build.compatible.spi.InvokerFactory;
import jakarta.enterprise.inject.build.compatible.spi.InvokerInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;

/**
 * Weld specific version of {@link InvokerFactory}.
 * Allows access to {@link WeldInvokerBuilder} without the need to manually type cast.
 */
public interface WeldInvokerFactory extends InvokerFactory {

    @Override
    WeldInvokerBuilder<InvokerInfo> createInvoker(BeanInfo bean, MethodInfo method);

}
