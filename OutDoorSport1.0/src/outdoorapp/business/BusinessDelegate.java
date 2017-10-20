package outdoorapp.business;

import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;

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
	private static ServiceBusinessLookUp serviceBusinessLookUp = ServiceBusinessLookUp.getInstance();
	
	private BusinessDelegate(){
	}
	
	/**
	 * 
	 * @return
	 */
	static BusinessDelegate getInstance(){
		return businessDelegate;
	}
	
	/**
	 * Metodo che esegue la ricerca dell'opportuno application service. Una volta indentificato il 
	 * corretto application service, viene utilizzata una reflection per capire quale operazione deve
	 * essere eseguita, sempre in base alla richiesta. In questo modo l'informazione è ben nascosta.
	 * 
	 * @param richiesta dal quale verrà identificato l'application service opportuno
	 * @return la risposta in base alla richiesta
	 */
	Response lookup(Request request, Service service){
		return serviceBusinessLookUp.sendRequest(request, service);
	}
}
