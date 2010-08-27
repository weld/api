package org.jboss.weld.bootstrap.spi;

import java.util.Collection;

/**
 * <p>
 * {@link Filter} is a data structures representing the &lt;include&gt; and
 * &lt;exclude&gt; elements in Weld's extensions to beans.xml. See the XSD for
 * Weld's extensions to beans.xml for details of the semantics of
 * &lt;include&gt; and &lt;exclude&gt;.
 * </p>
 * 
 * @author Pete Muir
 * @see Scanning
 */
public interface Filter
{

   /**
    * The name attribute
    */
   public String getName();

   /**
    * The pattern attribute
    */
   public String getPattern();

   /**
    * Nested &lt;if-system-property&gt; elements
    */
   public Collection<Metadata<SystemPropertyActivation>> getSystemPropertyActivations();

   /**
    * Nested &lt;if-class-available&gt; elements
    */
   public Collection<Metadata<ClassAvailableActivation>> getClassAvailableActivations();

}