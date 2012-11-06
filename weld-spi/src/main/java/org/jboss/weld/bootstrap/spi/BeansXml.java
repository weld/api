package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

public interface BeansXml
{
   
   public static final BeansXml EMPTY_BEANS_XML = new BeansXml()
   {
      
      public List<Metadata<BeansXmlRecord>> getEnabledInterceptors()
      {
         return emptyList();
      }
      
      public List<Metadata<BeansXmlRecord>> getEnabledDecorators()
      {
         return emptyList();
      }
      
      public List<Metadata<BeansXmlRecord>> getEnabledAlternativeStereotypes()
      {
         return emptyList();
      }
      
      public List<Metadata<BeansXmlRecord>> getEnabledAlternativeClasses()
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

   public List<Metadata<BeansXmlRecord>> getEnabledAlternativeStereotypes();

   public List<Metadata<BeansXmlRecord>> getEnabledAlternativeClasses();

   public List<Metadata<BeansXmlRecord>> getEnabledDecorators();

   public List<Metadata<BeansXmlRecord>> getEnabledInterceptors();
   
   public Scanning getScanning();

   public URL getUrl();

}