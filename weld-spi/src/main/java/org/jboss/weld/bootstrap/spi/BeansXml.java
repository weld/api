package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

/**
 * Representation of parsed {@code beans.xml} file.
 */
public interface BeansXml {

    /**
     * Empty {@code beans.xml}
     */
    BeansXml EMPTY_BEANS_XML = new BeansXml() {

        public List<Metadata<String>> getEnabledInterceptors() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledDecorators() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledAlternativeStereotypes() {
            return emptyList();
        }

        public List<Metadata<String>> getEnabledAlternativeClasses() {
            return emptyList();
        }

        public Scanning getScanning() {
            return EMPTY_SCANNING;
        }

        public URL getUrl() {
            return null;
        }

        public BeanDiscoveryMode getBeanDiscoveryMode() {
            return BeanDiscoveryMode.ANNOTATED;
        }

        @Override
        public String getVersion() {
            return null;
        }

        public boolean isTrimmed() {
            return false;
        }
    };

    /**
     * Returns list of enabled alternative stereotypes
     *
     * @return {@link List} of enabled alternatives stereotypes; can be empty but never {@code null}
     */
    List<Metadata<String>> getEnabledAlternativeStereotypes();

    /**
     * Returns list of enabled alternative classes
     *
     * @return {@link List} of enabled alternative classes; can be empty but never {@code null}
     */
    List<Metadata<String>> getEnabledAlternativeClasses();

    /**
     * Returns list of enabled decorators
     *
     * @return {@link List} of enabled decorators; can be empty but never {@code null}
     */
    List<Metadata<String>> getEnabledDecorators();

    /**
     * Returns list of enabled interceptors
     *
     * @return {@link List} of enabled interceptors; can be empty but never {@code null}
     */
    List<Metadata<String>> getEnabledInterceptors();

    /**
     * @return Initialized {@link Scanning} instance or {@code Scanning.EMPTY_SCANNING} if empty. Never null.
     */
    Scanning getScanning();

    /**
     * Returns the {@link URL} of this {@code beans.xml}.
     * Can be {@code null} in certain including but not limited to {@link #EMPTY_BEANS_XML} and synthetic bean archives in Weld
     * SE
     *
     * @return {@link URL} of this {@code beans.xml}, or {@code null}
     */
    URL getUrl();

    /**
     * Note that since CDI 4.0, this method returns {@link BeanDiscoveryMode#ANNOTATED} if the file does not declare
     * discovery mode attribute.
     *
     * @return The value of the <code>bean-discovery-mode</code> attribute or {@link BeanDiscoveryMode#ANNOTATED} if the file
     *         does not
     *         contain the <code>bean-discovery-mode</code> attribute.
     */
    BeanDiscoveryMode getBeanDiscoveryMode();

    /**
     * @return the value of the <code>version</code> attribute or null if the version is not explicitly specified
     */
    String getVersion();

    /**
     *
     * @return true if the <code>trim</code> element is specified
     * @since 3.0
     */
    boolean isTrimmed();

}