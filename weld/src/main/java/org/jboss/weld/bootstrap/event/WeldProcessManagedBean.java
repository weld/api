package org.jboss.weld.bootstrap.event;

import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.ProcessManagedBean;
import jakarta.enterprise.invoke.Invoker;

import org.jboss.weld.invoke.WeldInvokerBuilder;

/**
 * Represents an enhanced version of {@link ProcessManagedBean}.
 *
 * Allows access to {@link WeldInvokerBuilder} without the need to manually type cast.
 */
public interface WeldProcessManagedBean<X> extends ProcessManagedBean<X> {

    @Override
    WeldInvokerBuilder<Invoker<X, ?>> createInvoker(AnnotatedMethod<? super X> method);

}
