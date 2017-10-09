package outdoorapp.presentation.applicationcontroller;

import outdoorapp.business.BusinessDelegate;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

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

public class ApplicationController {

	/**
	 * Riferimento al Business Delegate.Permette di gestire, in 
	 * base alla richiesta, l'application service da utilizzare.
	 */
	BusinessDelegate businessDelegate;
	
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
