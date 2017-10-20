package outdoorapp.presentation.applicationcontroller;

import java.lang.reflect.Method;

import javafx.scene.layout.AnchorPane;
import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.RequestView;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

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

class ApplicationController{
	private static ServiceBusinessDelegate serviceBusinessDelegate = ServiceBusinessDelegate.getInstance();
	private static ApplicationController applicationController = new ApplicationController();
	private Dispatcher dispatcher = new Dispatcher();
	private ApplicationController() {}
	
	/**
	 * Metodo che invia la richiesta al business delegate e ottiene come risposta
	 * una azione da mandare al front controller
	 * 
	 * @param richiesta che viene passata all'Application Controller
	 * @return la risposta in base alla richiesta
	 */
	Response getAction(Request request, Service service){
		if(request.toString().contains("VIEW_")){
			return dispatcher.dispatch(request);
		}else
			return serviceBusinessDelegate.sendRequest(request, service);
	}
	
	static ApplicationController getInstance(){
		return applicationController;
	}
}
