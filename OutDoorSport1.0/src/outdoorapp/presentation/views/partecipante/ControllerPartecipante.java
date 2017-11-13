package outdoorapp.presentation.views.partecipante;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;

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
	@FXML private Label lblLogout;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerPartecipante() {}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {}
	
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
	
	/**
	 * Metodo che esegue il logout
	 */
	@FXML protected void viewLogin(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "Sei sicuro?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			this.sendRequest(new Request(anchorContent, VIEW_DASHBOARD_WELCOME));
			this.sendRequest(new Request(VIEW_LOGIN));
		} else {
		    alert.close();
		}
	}
}
