package outdoorapp.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerPartecipante extends GenericController{

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
	@Override
	protected void initialize() {
    }
	
	/**
	 * Metodo associato all'evento del controllo della view per la visualizzazione del profilo del partecipante
	 */
	@FXML protected void viewIlMioProfilo(){
		this.sendRequest(new Request(anchorContent, VIEW_IL_MIO_PROFILO));
	}
	
	/**
	 * Metodo associato all'evento del controllo della view per la visualizzazione delle escursione aperte
	 */
	@FXML protected void viewVisualizzaEscursioniAperte(){
		this.sendRequest(new Request(anchorContent, VIEW_VISUALIZZA_ESCURSIONI_APERTE));
	}
	
	/**
	 * Metodo associato all'evento del controllo della view per la visualizzazione delle escursione a cui
	 * il partecipante è iscritto
	 */
	@FXML protected void viewLeMieEscursioni(){
		this.sendRequest(new Request(anchorContent, VIEW_LE_MIE_ESCURSIONI));
	}

}
