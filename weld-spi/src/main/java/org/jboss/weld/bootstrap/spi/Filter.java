package org.jboss.weld.bootstrap.spi;

import java.util.Collection;

/**
 * <p>
 * {@link Filter} is a data structures representing the &lt;include&gt; and &lt;exclude&gt; elements in Weld's extensions to
 * beans.xml. See the XSD for Weld's extensions to beans.xml for details of the semantics of &lt;include&gt; and
 * &lt;exclude&gt;.
 * </p>
 *
 * @author Pete Muir
 * @see Scanning
 */
public interface Filter {

    /**
     * The name attribute
     */
    String getName();

    /**
     * The pattern attribute
     */
    String getPattern();

    /**
     * Nested &lt;if-system-property&gt; elements
     */
    Collection<Metadata<SystemPropertyActivation>> getSystemPropertyActivations();

    /**
     * Nested &lt;if-class-available&gt; elements
     */
    Collection<Metadata<ClassAvailableActivation>> getClassAvailableActivations();

}