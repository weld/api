package org.jboss.weld.bootstrap.spi;

/**
 * <p>
 * {@link ClassAvailableActivation} is a data structures representing the &lt;if-class-available&gt; element in Weld's
 * extensions to beans.xml. See the XSD for Weld's extensions to beans.xml for details of the semantics of
 * &lt;if-class-available&gt;.
 * </p>
 *
 * @author Pete Muir
 * @see Filter
 */
public interface ClassAvailableActivation {

    /**
     * The name attribute
     *
     * @return the name attribute
     */
    String getClassName();

    /**
     * Returns true if the filter is inverted (via {@code !}), false otherwise
     *
     * @return true if inverted, false otherwise
     */
    boolean isInverted();

}
