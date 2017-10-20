package outdoorapp.presentation.views.generic;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.RequestView;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Classe astratta ControllerRegistrazione che conterrà i metodi in comune per gli eventi di registrazione 
 * di un generico utente.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class GenericController implements Actions, Views{

	private FrontController fc = FrontController.getInstance();
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML protected abstract void initialize();
	
	protected Response sendRequest(Request request){
		return fc.sendRequest(request);
	}
}
