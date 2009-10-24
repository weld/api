package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;

public interface ObserverMethod<X, T>
{
   public Bean<X> getBean();

   public Type getObservedType();

   public Set<Annotation> getObservedQualifiers();

   public Reception getReception();

   public TransactionPhase getTransactionPhase();

   public void notify(T event);
}
