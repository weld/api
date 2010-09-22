package org.jboss.weld.context;

import javax.enterprise.context.spi.Context;

/**
 * The built in application context, which is always active (not managed) and is
 * backed by an application scoped singleton. The context is bound to 
 * 
 * @author Pete Muir
 * 
 */
public interface ApplicationContext extends Context
{

   /**
    * Invalidate the context, causing all bean instances to be destroyed.
    */
   public void invalidate();

}
