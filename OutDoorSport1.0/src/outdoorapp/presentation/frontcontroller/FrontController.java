package outdoorapp.presentation.frontcontroller;

import javafx.scene.layout.AnchorPane;
import outdoorapp.business.BusinessDelegate;
import outdoorapp.presentation.applicationcontroller.ApplicationController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

/**
 * Classe che implementa il Front Controller. Grazie a questa classe � possibile centralizzare
 * le richieste che provengono dal livello di presentation. Se non ci fosse un punto di
 * centralizzazione, i livelli sottostanti diventerebbero accessibili da pi� parti, rendendo
 * l'applicazione meno modulare e coesa. Inoltre, avere codice duplicato in diversi punti, significa avere un'enorme
 * difficolt� in termini di manutenzione (un singolo cambiamento potrebbe richiedere un numero elevato di
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
	private ApplicationController applicationController = new ApplicationController();
	
	/**
	 * Riferimento all'istanza di FrontController
	 */
	private static FrontController fc = new FrontController();
	
	/**
	 * Costruttore della classe FrontController privato
	 */
	private FrontController(){}
	
	/**
	 * Metodo che restituisce l'istanza del FrontController (Singleton)
	 * @return fc: istanza del FrontController
	 */
	public static FrontController getInstance(){
		return fc;
	}

	/**
	 * Metodo che gestisce la richiesta specificata. Il compito di inviare i dati per ottenere
	 * la risposta � delegato all'application controller, che libera il front controller della richiesta.
	 * 
	 * @param richiesta che viene passata al front controller
	 * @return la risposta in base alla richiesta
	 */
	public Response sendRequest(Request request) {
		return applicationController.getAction(request);
	}
}
