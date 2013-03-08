package org.jboss.weld.bootstrap.spi;

import static java.util.Collections.emptyList;
import static org.jboss.weld.bootstrap.spi.Scanning.EMPTY_SCANNING;

import java.net.URL;
import java.util.List;

public interface BeansXml {

    public static final BeansXml EMPTY_BEANS_XML = new BeansXml() {

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
            return BeanDiscoveryMode.ALL;
        }

        @Override
        public String getVersion() {
            return null;
        }
    };

    public List<Metadata<String>> getEnabledAlternativeStereotypes();

    public List<Metadata<String>> getEnabledAlternativeClasses();

    public List<Metadata<String>> getEnabledDecorators();

    public List<Metadata<String>> getEnabledInterceptors();

    public Scanning getScanning();

    public URL getUrl();

    /**
     * @return The value of the <code>bean-discovery-mode</code> attribute or {@link BeanDiscoveryMode#ALL} if the file does not
     *         contain the <code>bean-discovery-mode</code> attribute.
     */
    public BeanDiscoveryMode getBeanDiscoveryMode();

    /**
     * @return the value of the <code>version</code> attribute or null if the version is not explicitly specified
     */
    public String getVersion();

}