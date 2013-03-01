package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

public interface BeansXml
{
   
   public static final BeansXml EMPTY_BEANS_XML = new BeansXml()
   {
      
      public List<Metadata<String>> getEnabledInterceptors()
      {
         return emptyList();
      }
      
      public List<Metadata<String>> getEnabledDecorators()
      {
         return emptyList();
      }
      
      public List<Metadata<String>> getEnabledAlternativeStereotypes()
      {
         return emptyList();
      }
      
      public List<Metadata<String>> getEnabledAlternativeClasses()
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

   public List<Metadata<String>> getEnabledAlternativeStereotypes();

   public List<Metadata<String>> getEnabledAlternativeClasses();

   public List<Metadata<String>> getEnabledDecorators();

   public List<Metadata<String>> getEnabledInterceptors();
   
   public Scanning getScanning();

   public URL getUrl();

}