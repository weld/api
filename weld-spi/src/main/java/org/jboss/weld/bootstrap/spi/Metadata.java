package org.jboss.weld.bootstrap.spi;

/**
 * A piece of metadata, of type T
 *
 * @author Pete Muir
 *
 * @param <T> the type of the metadata
 */
public interface Metadata<T> {

    /**
     * The metadata value
     */
    T getValue();

    /**
     * The location of the metadata, used in error and log messages
     */
    String getLocation();

}
