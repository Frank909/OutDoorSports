package outdoorapp.presentation.views.generic;

import javafx.fxml.FXML;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe astratta GenericViewController che conterr� i metodi in comune per gli eventi di registrazione 
 * di un generico utente.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class GenericViewController implements Actions, Views{
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML protected abstract void initialize();
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	protected Response sendRequest(Request request){
		return FrontController.getInstance().sendRequest(request);
	}
}