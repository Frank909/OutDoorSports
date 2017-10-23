package outdoorapp.services;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

public abstract class Service implements ServiceInterface{
	
	protected abstract Response sendRequest(Request request);
	
	/**
	 * 
	 * @param request: Richiesta in ingresso
	 * @param service: Servizio richiesto
	 * @return restituisce una risposta gestendo le informazioni in ingresso di servizio e richiesta.
	 */
	protected Response handle(Request request, Service service){
		return service.sendRequest(request);
	}
	
}
