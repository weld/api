package org.jboss.weld.resources.spi;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.weld.bootstrap.api.Service;

public interface ScheduledExecutorServiceFactory extends Service
{
   
   public ScheduledExecutorService get();
   
}