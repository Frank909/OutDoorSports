package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio, invocata dal Service Locator, per collegare il 
 * FrontController all'Application Controller. In questo modo si nascondono 
 * i vari passaggi della richiesta e non possono essere richiamati da altre
 * parti.
 * 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceApplicationController extends Service{

	/**
	 * Richiama l'istanza di ApplicationController
	 */
	private static ApplicationController applicationController = ApplicationController.getInstance();
	
	public ServiceApplicationController(){}
	
	@Override
	public ServiceType getType() {
		return ServiceType.ApplicationController;
	}

	@Override
	protected Response sendRequest(Request request) {
		return applicationController.getAction(request);
	}

}
