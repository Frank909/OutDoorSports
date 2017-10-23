package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio/supporto al businessLookUp realizzato allo scopo di nascondere dettagli
 * implementativi fungendo da tunneling fra FrontController e ApplicationController
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceApplicationController extends Service{

	private static ApplicationController applicationController = ApplicationController.getInstance();
	
	public ServiceApplicationController(){
	}
	
	
	@Override
	public ServiceType getType() {
		return ServiceType.ApplicationController;
	}

	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta all'applicationController e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */
	@Override
	protected Response sendRequest(Request request) {
		return applicationController.getAction(request);
	}

}
