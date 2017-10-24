package outdoorapp.services;

import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;

/**
 * Classe di supporto al Service Locator che istanzia
 * il servizio, in base al tipo di servizio richiesto
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class InitialContext {
	
	/**
	 * @param serviceType: tipo di servizio richiesto
	 * @return una istanza del servizio richiesto
	 * @throws Exception: nel caso il servizio non esiste
	 */
	public Object lookup(ServiceType serviceType) throws Exception{
		switch(serviceType){
		case ApplicationController:
			return new ServiceApplicationController();
			
		case BusinessDelegate:
			return new ServiceBusinessDelegate();
			
		case BusinessLookUp:
			return new ServiceBusinessLookUp();
			
		default:
			throw new Exception("Servizio richiesto errato");	
		}	
	}
}
