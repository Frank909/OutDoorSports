package outdoorapp.presentation.frontcontroller;

import outdoorapp.business.BusinessDelegate;
import outdoorapp.presentation.applicationcontroller.ApplicationController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

/**
 * Classe che implementa il Front Controller. Grazie a questa classe è possibile centralizzare
 * le richieste che provengono dal livello di presentation. Se non ci fosse un punto di
 * centralizzazione, i livelli sottostanti diventerebbero accessibili da più parti, rendendo
 * l'applicazione meno modulare e coesa. Inoltre, avere codice duplicato in diversi punti, significa avere un'enorme
 * difficoltà in termini di manutenzione (un singolo cambiamento potrebbe richiedere un numero elevato di
 * modifiche al codice).
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class FrontController{

	/**
	 * Riferimento all'application controller.Permette di gestire
	 * le richieste e le risposte.
	 */
	private ApplicationController applicationController;
	
	/**
	 * Costruttore della classe FrontController che inizializza
	 * l'application controller.
	 */
	public FrontController(){
		applicationController = new ApplicationController();
	}
	/*private Dispatcher dispatcher;
	private boolean isAuthenticUser = false;

	public FrontController() {
		dispatcher = new Dispatcher();
	}

	private boolean isAuthenticUser() {
		// here you have to write Authentication logic
		System.out.println("User is authenticated successfully.");
		return isAuthenticUser;
	}

	private void trackRequest(ReqResp request) {
		System.out.println("Page requested: " + request.getRequest());
		BusinessDelegate.getReqResp(request);
	}*/

	/**
	 * Metodo che gestisce la richiesta specificata. Il compito di inviare i dati per ottenere
	 * la risposta è delegato all'application controller, che libera il front controller della richiesta.
	 * 
	 * @param richiesta che viene passata al front controller
	 * @return la risposta in base alla richiesta
	 */
	public Response sendRequest(Request request) {
		return this.applicationController.getAction(request);
	}
}
