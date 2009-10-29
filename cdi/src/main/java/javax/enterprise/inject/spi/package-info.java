/**
 * <p>The portable extension integration SPI.</p>
 * 
 * <p>A portable extension may integrate with the container by:</p>
 * 
 * <ul>
 * <li>Providing its own beans, interceptors and decorators to the 
 * container</li>
 * <li>Injecting dependencies into its own objects using the 
 * dependency injection service</li>
 * <li>Providing a context implementation for a custom scope</li>
 * <li>Augmenting or overriding the annotation-based metadata with 
 * metadata from some other source</li>
 * </ul>
 * 
 * <h3>Interfaces representing enabled beans</h3>
 * 
 * <p>The interfaces 
 * {@link javax.enterprise.inject.spi.Bean},
 * {@link javax.enterprise.inject.spi.Decorator},
 * {@link javax.enterprise.inject.spi.Interceptor} and
 * {@link javax.enterprise.inject.spi.ObserverMethod}
 * define everything the container needs to manage instances of 
 * a bean, interceptor, decorator or observer method.</p>
 * 
 * <p>An instance of <tt>Bean</tt> exists for every  
 * {@linkplain javax.enterprise.inject enabled bean}. An application 
 * or portable extension may add support for new kinds of beans by 
 * implementing <tt>Bean</tt> and 
 * {@linkplain javax.enterprise.inject.spi.AfterBeanDiscovery#addBean(Bean) 
 * registering beans} with the container.</p>
 * 
 * <h3>The <tt>BeanManager</tt> object</h3>
 * 
 * <p>Portable extensions sometimes interact directly with the container 
 * via programmatic API call. The interface 
 * {@link javax.enterprise.inject.spi.BeanManager} provides operations 
 * for obtaining contextual references for beans, along with many other 
 * operations of use to portable extensions.</p>
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
 * <h3>Container lifecycle events</h3>
 * 
 * <p>During the application initialization process, the container fires 
 * a series of {@linkplain javax.enterprise.event events}, allowing 
 * portable extensions to integrate with the container initialization 
 * process. Observer methods of these events must belong to
 * {@linkplain javax.enterprise.inject.spi.Extension extensions} declared 
 * in <tt>META-INF/services</tt>.</p>
 * 
 * @see javax.enterprise.inject
 * @see javax.enterprise.context.spi
 */
package javax.enterprise.inject.spi;