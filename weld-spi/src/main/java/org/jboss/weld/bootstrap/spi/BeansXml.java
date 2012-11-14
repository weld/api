package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

public interface BeansXml
{
   
   public static final BeansXml EMPTY_BEANS_XML = new BeansXml()
   {
      
      public List<Metadata<EnabledClass>> getEnabledInterceptors()
      {
         return emptyList();
      }
      
      public List<Metadata<EnabledClass>> getEnabledDecorators()
      {
         return emptyList();
      }
      
      public List<Metadata<EnabledClass>> getEnabledAlternatives()
      {
         return emptyList();
      }
      
      public Scanning getScanning() 
      {
         return EMPTY_SCANNING;
      }

      public URL getUrl() {
          return null;
      }
      
   };

   public List<Metadata<EnabledClass>> getEnabledAlternatives();

   public List<Metadata<EnabledClass>> getEnabledDecorators();

   public List<Metadata<EnabledClass>> getEnabledInterceptors();
   
   public Scanning getScanning();

   public URL getUrl();

}