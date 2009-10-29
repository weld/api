/**
 * <p>The custom context SPI.</p>
 * 
 * <p>Associated with every 
 * {@linkplain javax.enterprise.context scope type} is a 
 * {@linkplain javax.enterprise.context.spi.Context context object}.
 * The context object implements the semantics of the scope type.</p>
 * 
 * <p>The context implementation collaborates with the container via 
 * the {@link javax.enterprise.context.spi.Context Context} and 
 * {@link javax.enterprise.context.spi.Contextual Contextual} 
 * interfaces to create and destroy contextual instances.</p>
 * 
 * @see javax.enterprise.context
 * @see javax.enterprise.inject.spi
 */
package javax.enterprise.context.spi;