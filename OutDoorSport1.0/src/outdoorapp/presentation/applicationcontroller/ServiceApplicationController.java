package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;
import outdoorapp.services.AbstractService;

/**
 * Classe di servizio/supporto al businessLookUp realizzato allo scopo di nascondere dettagli
 * implementativi fungendo da tunneling fra FrontController e ApplicationController
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceApplicationController extends AbstractService{

	private static ApplicationController applicationController = ApplicationController.getInstance();
	private static ServiceApplicationController serviceApplicationController = new ServiceApplicationController();
	
	/**
	 * Costruttore privato
	 */
	private ServiceApplicationController(){
	}
	
	/**
	 * 
	 * @return restituisce l'istanza del ServiceApplicationController
	 */
	public static ServiceApplicationController getInstance(){
		return serviceApplicationController;
	}
	
	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta all'applicationController e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */
	@Override
	public Response sendRequest(Request req, ServiceType serviceType) {
		if(serviceType.equals(ServiceType.FrontController)){
			setTunneled(true);
			return applicationController.getAction(req, ServiceType.ApplicationController);
		}
		else 	
			return null;
	}

}
