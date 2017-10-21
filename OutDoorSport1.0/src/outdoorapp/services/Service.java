package outdoorapp.services;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

/**
 * Interfaccia che rappresenta un generico servizio
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public interface Service {
	/**
	 * 
	 * @param request: richiesta in ingresso
	 * @param serviceType: tipo di servizio richiesto
	 * @return restituisce la risposta in base alla richiesta e al tipo di servizio richiesto
	 */
	Response sendRequest(Request request, ServiceType serviceType);
}
