package outdoorapp.services;

import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;

public class ServiceFactory{
	private static ServiceFactory serviceCreator = null;
	
	private ServiceFactory(){}
	
	public static ServiceFactory getServiceCreator(){
		if(serviceCreator == null)
			serviceCreator = new ServiceFactory();
		return serviceCreator;
	}
	
	public Service getService(ServiceType serviceType) throws Exception{
		Service service;
		switch(serviceType){
			case BusinessLookUp:
				service = ServiceBusinessLookUp.getInstance();
				break;
			case BusinessDelegate:
				service = ServiceBusinessDelegate.getInstance();
				break;
			case ApplicationController:
				service = ServiceApplicationController.getInstance();
				break;
			default:
				throw new Exception("Servizio richiesto errato");	
		}
		return service;
		
	}
}
