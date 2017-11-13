package outdoorapp.presentation.views.managersistema;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;

/**
 * Gestisce la dashboard del manager di sistema
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiSistema extends GenericController{

	@FXML private Label lblGestioneManagerEscursione;
	@FXML private Label lblVisulizzaEscursioniSistema;
	@FXML private Label lblInserisciManagerEscursione;
	@FXML private AnchorPane anchorContent;
	@FXML private AnchorPane anchorManagerSistema;
	@FXML private Label lblLogout;
	
	/**
	 * Costruttore della classe ControllerManagerDiSistema
	 */
	public ControllerManagerDiSistema() {
		
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {	
	}

	/**
	 * Metodo associato all'evento del controllo della view per la gestione di un manager di escursione
	 */
	@FXML protected void viewGestioneManagerEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_GESTIONE_MANAGER_ESCURSIONE));
	}
	
	/**
	 * Metodo associato all'evento del controllo della view per la visualizzazione delle 
	 * escursioni di presenti nel sistema
	 */
	@FXML protected void viewVisualizzaEscursioniSistema(){
		this.sendRequest(new Request(anchorContent, VIEW_VISUALIZZA_ESCURSIONI_SISTEMA));
	}
	
	/**
	 * Metodo associato all'evento del controllo della view per la 
	 * registrazione di un nuovo manager di escursione
	 */
	@FXML protected void viewInserisciManagerEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE));
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
