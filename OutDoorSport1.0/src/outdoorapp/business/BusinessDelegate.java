package outdoorapp.business;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;
import outdoorapp.services.ServiceType;

/**
 * Classe che rappresenta il Business Delegate, ovvero un'astrazione client-side 
 * dei servizi di business. Viene creata una astrazione dove si nascondono i dettagli 
 * implementativi dei servizi di business. Utilizzando il Business Delegate è possibile 
 * ridurre l'accoppiamento tra i client e i servizi offerti dal sistema. 
 * L'obiettivo della classe è quindi quello di incapsulare l'accesso ai vari application service,
 * nascondendo i dettagli di implementazione della strategia di lookup (ritrovamento) e di accesso 
 * ad un servizio.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class BusinessDelegate{
	

	private static BusinessDelegate businessDelegate = new BusinessDelegate();
	private static Service serviceBusinessLookUp = null;
	
	private BusinessDelegate(){
	}
	
	/**
	 * @return restituisce l'istanza del BusinessDelegate
	 */
	static BusinessDelegate getInstance(){
		if(serviceBusinessLookUp == null){
			ServiceFactory serviceCreator = ServiceFactory.getServiceCreator();
			try {
				serviceBusinessLookUp = serviceCreator.getService(ServiceType.BusinessLookUp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return businessDelegate;
	}
	
	/**
	 * Metodo che richiede la ricerca del servizio opportuno, mandando un richiesta alla classe di servizio del
	 * businessLookUp e ricevendo l'opportuna risposta
	 * 
	 * @param richiesta dal quale verrà identificato l'application service opportuno
	 * @return la risposta in base alla richiesta
	 */
	Response lookup(Request request, ServiceType serviceType){
		return serviceBusinessLookUp.sendRequest(request, serviceType);
	}
}
