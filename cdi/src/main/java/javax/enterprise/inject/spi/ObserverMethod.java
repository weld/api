package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;

/**
 * <p>Represents an observer method of an enabled bean. Defines 
 * everything the container needs to know about an observer method.</p>
 * 
 * @author Gavin King
 * @author David Allen
 * @param <X> the type of the bean with the observer method
 * @param <T> the event type 
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
    * Obtains the observed event {@linkplain Type type}.
    * 
    * @return the observed event {@linkplain Type type}
    */
   public Type getObservedType();

   /**
    * Obtains the set of observed event {@linkplain javax.inject.Qualifier qualifiers}.
    * 
    * @return the observed event {@linkplain javax.inject.Qualifier qualifiers}
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
    * @param event the event object
    */
   public void notify(T event);
}
