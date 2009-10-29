/**
 * <p>The portable extension integration SPI.</p>
 * 
 * <h3>Alternate metadata sources</h3>
 * 
 * <p>A portable extension may provide an alternative metadata 
 * source, such as configuration by XML. 
 * {@link javax.enterprise.inject.spi.Annotated}
 * and its subtypes allow a portable extension to specify 
 * metadata that overrides the annotations that exist on a 
 * bean class. The portable extension is responsible for 
 * implementing the interfaces, thereby exposing the metadata 
 * to the container. The container must use the operations of 
 * <tt>Annotated</tt> and its subinterfaces to discover program 
 * element types and annotations, instead of directly calling the 
 * Java Reflection API.</p>
 * 

 */
package javax.enterprise.inject.spi;