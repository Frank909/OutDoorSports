package outdoorapp.presentation.views.managerescursione;

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
 * Gestisce la dashboard del manager di escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiEscursione extends GenericController{

	@FXML private Label lblGestisciEscursione;
	@FXML private Label lblInserisciEscursione;
	@FXML private Label lblLogout;
	@FXML private AnchorPane anchorContent;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerManagerDiEscursione() {
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
    }
	
	/**
	 * Metodo associato all'evento del controllo della view per la gestione delle escursioni
	 */
	@FXML protected void viewGestioneEscursioni(){
		this.sendRequest(new Request(anchorContent, VIEW_GESTIONE_ESCURSIONI));
	}
	
	/**
	 * Metodo associato all'evento del controllo della view per l'inserimento di una escursione
	 */
	@FXML protected void viewInserisciEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_INSERISCI_ESCURSIONE));
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
