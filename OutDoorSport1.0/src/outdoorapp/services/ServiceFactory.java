package outdoorapp.services;

import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;

/**
 * Classe che consente di creare il servizio richiesto e usufruirne.
 * Rappresenta il pattern del Factory Method.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ServiceFactory{
	private static ServiceFactory serviceCreator = null;
	
	/**
	 * Costruttore privato
	 */
	private ServiceFactory(){}
	
	/**
	 * @return restuisce il creator del ServiceFactory
	 */
	public static ServiceFactory getServiceCreator(){
		if(serviceCreator == null)
			serviceCreator = new ServiceFactory();
		return serviceCreator;
	}
	
	/**
	 * 
	 * @param serviceType: Tipo di servizio richiesto.
	 * @return restituisce il servizio richiesto in base al tipo dello stesso dato in ingresso.
	 * @throws Exception: Lancia l'eccezione nel caso non esista il servizio richiesto.
	 */
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
