package outdoorapp.presentation.views.generic;

import javafx.fxml.FXML;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe astratta GenericViewController che conterrà i metodi in comune per gli eventi di registrazione 
 * di un generico utente.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class GenericController implements Actions, Views{
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */	
	@FXML protected abstract void initialize();
	
	/**
	 * Metodo che invia una richiesta ai livelli più bassi per
	 * reperire delle informazioni. Restituisce una risposta
	 * in base alla richiesta 
	 * 
	 * @param request: richiesta da inviare
	 * @return response: risposta in base alla richiesta
	 */
	protected Response sendRequest(Request request){
		return FrontController.getInstance().sendRequest(request);
	}
}
