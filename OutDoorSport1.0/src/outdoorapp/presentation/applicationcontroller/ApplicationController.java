package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceLocator;
import outdoorapp.services.ServiceType;

/**
 * Classe che rappresenta un'implementazione dell'Application Controller
 * 
 * Nel livello di presentazione è importante risolvere la richiesta di arrivo con una azione
 * che la porti a termine, e capire quale view visualizzare in base alla risposta.
 * Grazie all'Application Controller è possibile realizzare questo. Tale strategia migliora
 * la modularità, l'estendibilità e la riusabilità del codice.
 * 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationController extends Service{
	private static Service service = null;
	
	private static ApplicationController applicationController = new ApplicationController();
	
	private static Dispatcher dispatcher = Dispatcher.getInstance();
	
	/**
	 * Costruttore privato
	 */
	private ApplicationController() {}
	
	/**
	 * 
	 * @return restituisce l'istanza dell'applicationController
	 */
	static ApplicationController getInstance(){
		if(service == null){
			try {
				service = ServiceLocator.getService(ServiceType.BusinessDelegate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return applicationController;
	}
	
	/**
	 * Metodo che invia la richiesta al servizio del business delegate e ottiene come risposta
	 * una azione da mandare al front controller.
	 * Nel caso in cui ricevesse una richiesta di visualizzazione di una view, la manda al dispatcher.
	 * 
	 * @param richiesta che viene passata all'Application Controller
	 * @return la risposta in base alla richiesta
	 */
	Response getAction(Request request){
		return this.sendRequest(request);
	}

	@Override
	public ServiceType getType() {
		return null;
	}

	@Override
	protected Response sendRequest(Request request) {
		if(request.toString().contains("VIEW_")){
			return dispatcher.dispatch(request);
		}else
			return this.handle(request, service);
	}
}
