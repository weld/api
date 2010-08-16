package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;

import java.util.List;

public interface BeansXml
{
   
   public static final BeansXml EMPTY_BEANS_XML = new BeansXml()
   {
      
      public List<String> getEnabledInterceptors()
      {
         return emptyList();
      }
      
      public List<String> getEnabledDecorators()
      {
         return emptyList();
      }
      
      public List<String> getEnabledAlternativeStereotypes()
      {
         return emptyList();
      }
      
      public List<String> getEnabledAlternativeClasses()
      {
         return emptyList();
      }
   };

   public List<String> getEnabledAlternativeStereotypes();

   public List<String> getEnabledAlternativeClasses();

   public List<String> getEnabledDecorators();

   public List<String> getEnabledInterceptors();

}