package org.jboss.weld.experimental;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This API is experimental and will change!
 *
 * This annotation duplicates javax.annotation.Priority. Unlike javax.annotation.Priority, this annotation can also be applied on parameters
 * which makes it usable for specifying the priority of observer methods.
 *
 * In the future, javax.annotation.Priority will be updated and this annotation will be removed.
 *
 * @author Jozef Hartinger
 *
 */
@Target({ TYPE, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface Priority {
    int value();
}