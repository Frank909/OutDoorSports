package outdoorapp.services;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

/**
 * Classe astratta che rappresenta un servizio generico
 * richiamato dal service locator
 * 
 * @author andreese
 *
 */

public abstract class Service implements ServiceInterface{
	
	/**
	 * Metodo astratto che conterrà l'implementazione della richiesta
	 * da inviare, in base alla classe che lo eredita
	 * 
	 * @param request
	 * @return una risposta in base alla richiesta
	 */
	protected abstract Response sendRequest(Request request);
	
	/**
	 * @param request: Richiesta in ingresso
	 * @param service: Servizio richiesto
	 * @return restituisce una risposta gestendo le informazioni in ingresso di servizio e richiesta.
	 */
	protected Response handle(Request request, Service service){
		return service.sendRequest(request);
	}
}
