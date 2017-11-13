package outdoorapp.business;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio, invocata dal Service Locator, per collegare 
 * l'Application Controller al Business Delegate. In questo modo si nascondono 
 * i vari passaggi della richiesta e non possono essere richiamati da altre
 * parti.
 * 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class ServiceBusinessDelegate extends Service{

	/**
	 * Richiama l'istanza di BusinessDelegate
	 */
	private static BusinessDelegate businessDelegate = BusinessDelegate.getInstance();

	public ServiceBusinessDelegate(){
	}
	
	@Override
	public ServiceType getType() {
		return ServiceType.BusinessDelegate;
	}

	@Override
	protected Response sendRequest(Request request) {
		return businessDelegate.lookup(request);
	}

}
