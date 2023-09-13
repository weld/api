package org.jboss.weld.bootstrap.spi;

import java.util.Collection;

/**
 * <p>
 * {@link Filter} is a data structures representing the &lt;exclude&gt; elements beans.xml (Since CDI 1.1). See the XSD for
 * details of the semantics of &lt;exclude&gt;.
 * </p>
 *
 * @see <a href="https://github.com/jboss/cdi/blob/master/api/src/main/resources/beans_1_1.xsd">Beans 1.1</a>
 *
 * @author Pete Muir
 * @see Scanning
 */
public interface Filter {

    /**
     * The name attribute
     *
     * @return the name attribute
     */
    String getName();

    /**
     * Nested &lt;if-system-property&gt; elements
     *
     * @return nested &lt;if-system-property&gt; elements
     */
    Collection<Metadata<SystemPropertyActivation>> getSystemPropertyActivations();

    /**
     * Nested &lt;if-class-available&gt; elements
     *
     * @return nested &lt;if-class-available&gt; elements
     */
    Collection<Metadata<ClassAvailableActivation>> getClassAvailableActivations();

}