package outdoorapp.services;

import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;

class InitialContext {
	
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
