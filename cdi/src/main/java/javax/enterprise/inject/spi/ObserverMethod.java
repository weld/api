package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;

/**
 * <p>
 * This interface defines everything the container needs to know about an
 * observer method.
 * </p>
 * 
 * @author David Allen
 * @param <X> the type of bean with the observer method
 * @param <T> the type of event
 */
public interface ObserverMethod<X, T>
{
   /**
    * Obtains the {@linkplain Class class} of bean defining the observer method.
    * 
    * @return the defining {@linkplain Class class}
    */
   public Class<X> getBeanClass();

   /**
    * Obtains the {@linkplain Type type} of event being observed.
    * 
    * @return the event {@linkplain Type type}
    */
   public Type getObservedType();

   /**
    * Obtains the set of {@linkplain javax.inject.Qualifier qualifiers} for the
    * event being observed.
    * 
    * @return the {@linkplain javax.inject.Qualifier qualifiers} for the event
    */
   public Set<Annotation> getObservedQualifiers();

   /**
    * Obtains the specified {@link Reception} for the observer method. This
    * indicates if the observer is conditional or not.
    * 
    * @return the {@link Reception}
    */
   public Reception getReception();

   /**
    * Obtains the specified {@link TransactionPhase} for the observer method.
    * 
    * @return the {@link TransactionPhase}
    */
   public TransactionPhase getTransactionPhase();

   /**
    * Calls the observer method passing the given event object.
    * 
    * @param event The event object
    */
   public void notify(T event);
}
