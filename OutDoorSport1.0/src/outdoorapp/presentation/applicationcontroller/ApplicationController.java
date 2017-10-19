package outdoorapp.presentation.applicationcontroller;

import java.lang.reflect.Method;

import javafx.scene.layout.AnchorPane;
import outdoorapp.business.BusinessDelegate;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Classe che rappresenta un'implementazione dell'Application Controller
 * 
 * Nel livello di presentazione � importante risolvere la richiesta di arrivo con una azione
 * che la porti a termine, e capire quale view visualizzare in base alla risposta.
 * Grazie all'Application Controller � possibile realizzare questo. Tale strategia migliora
 * la modularit�, l'estendibilit� e la riusabilit� del codice.
 * 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ApplicationController{

	/**
	 * Riferimento al Business Delegate.Permette di gestire, in 
	 * base alla richiesta, l'application service da utilizzare.
	 */
	private BusinessDelegate businessDelegate;
	private ViewCache viewCache = ViewCache.getInstance(); 
	
	/**
	 * Costruttore della classe che inizializza l'istanza di BusinessDelegate
	 */
	public ApplicationController() {
		businessDelegate = new BusinessDelegate();
	}
	
	/**
	 * Metodo che invia la richiesta al business delegate e ottiene come risposta
	 * una azione da mandare al front controller
	 * 
	 * @param richiesta che viene passata all'Application Controller
	 * @return la risposta in base alla richiesta
	 */
	public Response getAction(Request request){
		return businessDelegate.lookup(request);
	}
}
