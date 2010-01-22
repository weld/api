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
package org.jboss.weld.transaction.spi;

import javax.transaction.Synchronization;
import javax.transaction.UserTransaction;

import org.jboss.weld.bootstrap.api.Service;

/**
 * <p>
 * The container must implement the services related to transactional behavior
 * used in JSR-299, if that behavior is going to be used.
 * </p>
 * 
 * <p>
 * The event framework specified by CDI includes the ability to create observer 
 * methods which are activated based on the phase and status of a currently 
 * active transaction. In order to use these abilities, the container must 
 * provide these intermediary services which in turn may interact with an 
 * application server and JTA.
 * </p>
 * 
 * <p>Required in a Java EE environment</p>
 * 
 * <p> {@link TransactionServices} is a per-deployment service.</p>
 * 
 * @author David Allen
 * 
 */
public interface TransactionServices extends Service
{
   /**
    * Registers a synchronization object with the currently executing
    * transaction.
    * 
    * @see javax.transaction.Synchronization
    * @param synchronizedObserver
    */
   public void registerSynchronization(Synchronization synchronizedObserver);

   /**
    * Queries the status of the current execution to see if a transaction is
    * currently active.
    * 
    * @return true if a transaction is active
    */
   public boolean isTransactionActive();
   
   /**
    * Obtain a reference to the JTA UserTransaction
    * 
    * @return a reference to the JTA UserTransaction
    */
   public UserTransaction getUserTransaction();
}
