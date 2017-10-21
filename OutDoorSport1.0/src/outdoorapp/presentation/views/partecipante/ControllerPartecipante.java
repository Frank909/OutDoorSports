package outdoorapp.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerPartecipante extends GenericViewController{

	@FXML private AnchorPane anchorContent;
	@FXML private Label lblLeMieEscursioni;
	@FXML private Label lblVisulizzaEscursioniAperte;
	@FXML private Label lblIlMioProfilo;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerPartecipante() {}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML protected void initialize() {
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	@FXML protected void viewIlMioProfilo(){
		this.sendRequest(new Request(anchorContent, VIEW_IL_MIO_PROFILO));
	}
	
	@FXML protected void viewVisualizzaEscursioniAperte(){
		this.sendRequest(new Request(anchorContent, VIEW_VISUALIZZA_ESCURSIONI_APERTE));
	}
	
	@FXML protected void viewLeMieEscursioni(){
		this.sendRequest(new Request(anchorContent, VIEW_LE_MIE_ESCURSIONI));
	}

}
