package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;

import java.util.Collection;

/**
 * <p>
 * Scanning is a data structures representing the &lt;scan&gt; element in Weld's extensions to beans.xml. See the XSD for Weld's
 * extensions to beans.xml for details of the semantics of &lt;scan&gt;. {@link Scanning} contains an include {@link Filter}
 * list and an exclude {@link Filter} list.
 * </p>
 *
 * @author Pete Muir
 *
 */
public interface Scanning {

    Scanning EMPTY_SCANNING = new Scanning() {

        public Collection<Metadata<Filter>> getIncludes() {
            return emptyList();
        }

        public Collection<Metadata<Filter>> getExcludes() {
            return emptyList();
        }
    };

    /**
     * The &lt;include&gt; element
     *
     * @return the include element
     */
    Collection<Metadata<Filter>> getIncludes();

    /**
     * The &lt;exclude&gt; element
     *
     * @return the exclude element
     */
    Collection<Metadata<Filter>> getExcludes();

}