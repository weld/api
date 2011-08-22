package org.jboss.weld.bootstrap.api.helpers;

import java.util.Hashtable;
import java.util.Map;
import org.jboss.weld.bootstrap.api.Singleton;
import org.jboss.weld.bootstrap.api.SingletonProvider;

/**
 *
 * @author mathieuancelin
 */
public class RegistrySingletonProvider extends SingletonProvider
{
   public static final String STATIC_INSTANCE = "STATIC_INSTANCE";

   @Override
   public <T> Singleton<T> create(Class<? extends T> type)
   {
      return new RegistrySingleton<T>(type);
   }

   private static class RegistrySingleton<T> implements Singleton<T>
   {
      // use Hashtable for concurrent access
      private final Map<String, T> store = new Hashtable<String, T>();
      
      private final Class<? extends T> clazz;

      public RegistrySingleton(Class<? extends T> clazz) 
      {
          this.clazz = clazz;
      }
      
      public T get(String id)
      {
         T instance = store.get(id);
         if (instance == null)
         {
            throw new IllegalStateException("Singleton not set for " + id + " => " + store.keySet());
         }
         return instance;
      }

      public void set(String id, T object)
      {
         store.put(id, object);
      }
      
      public void clear(String id)
      {
         store.remove(id);
      }
      
      public boolean isSet(String id)
      {
         return store.containsKey(id);
      }
   }
}
