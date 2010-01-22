/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.servlet.api.helpers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;

import org.jboss.weld.servlet.api.ServletListener;

public abstract class ForwardingServletListener implements ServletListener
{
   
   protected abstract ServletListener delegate();

   public void contextDestroyed(ServletContextEvent sce)
   {
      delegate().contextDestroyed(sce);
   }

   public void contextInitialized(ServletContextEvent sce)
   {
      delegate().contextInitialized(sce);
   }

   public void requestDestroyed(ServletRequestEvent sre)
   {
      delegate().requestDestroyed(sre);
   }

   public void requestInitialized(ServletRequestEvent sre)
   {
      delegate().requestInitialized(sre);
   }

   public void sessionCreated(HttpSessionEvent se)
   {
      delegate().sessionCreated(se);
   }

   public void sessionDestroyed(HttpSessionEvent se)
   {
      delegate().sessionDestroyed(se);
   }
   
}
