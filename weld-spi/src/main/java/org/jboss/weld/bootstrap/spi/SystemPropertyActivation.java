package org.jboss.weld.bootstrap.spi;

/**
 * <p>
 * {@link SystemPropertyActivation} is a data structures representing the
 * &lt;if-system-property&gt; element in Weld's extensions to beans.xml. See the
 * XSD for Weld's extensions to beans.xml for details of the semantics of
 * &lt;if-system-property&gt;.
 * </p>
 * 
 * @author Pete Muir
 * @see Filter
 */
public interface SystemPropertyActivation
{

   /**
    * The name attribute
    */
   public String getName();

   /**
    * The value attribute
    */
   public String getValue();

}
