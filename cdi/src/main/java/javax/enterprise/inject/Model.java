package javax.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * <p>The built-in stereotype intended for use with beans 
 * that define the model layer of an MVC web application 
 * architecture such as JSF.</p>
 * 
 * @see javax.enterprise.inject.Stereotype
 * @author Gavin King
 */

@Named
@RequestScoped
@Documented
@Stereotype
@Target( { TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface Model
{
}