package outdoorapp.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta la cache dei servizi riguardante il Service
 * Locator
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Cache {
	/**
	 * Lista dei Servizi
	 */
	private List<Service> services;

	public Cache(){
		services = new ArrayList<Service>();
	}

	/**
	 * @param serviceType: tipo di servizio
	 * @return una nuova istanza del servizio in base al tipo
	 */
	public Service getService(ServiceType serviceType){

		for (Service service : services) {
			if(service.getType().equals(serviceType)){
				return service;
			}
		}
		return null;
	}

	/**
	 * Metodo che aggiunge un servizio nella cache dei servizi.
	 * Se esiste non lo inserisce.
	 * 
	 * @param newService: servizio da aggiungere
	 */
	public void addService(Service newService){
		boolean exists = false;

		for (Service service : services) {
			if(service.getType().equals(newService.getType())){
				exists = true;
			}
		}
		
		if(!exists){
			services.add(newService);
		}
	}
}
