package outdoorapp.presentation.views.managersistema;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

	@FXML protected void viewGestioneManagerEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_GESTIONE_MANAGER_ESCURSIONE));
	}
	
	@FXML protected void viewVisualizzaEscursioniSistema(){
		this.sendRequest(new Request(anchorContent, VIEW_VISUALIZZA_ESCURSIONI_SISTEMA));
	}
	
	@FXML protected void viewInserisciManagerEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE));
	}


}
