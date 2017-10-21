package outdoorapp.presentation.views.managersistema;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del manager di sistema
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiSistema extends GenericViewController{

	@FXML private Label lblGestioneManagerEscursione;
	@FXML private Label lblVisulizzaEscursioniSistema;
	@FXML private Label lblInserisciManagerEscursione;
	@FXML private AnchorPane anchorContent;
	
	/**
	 * Costruttore della classe ControllerManagerDiSistema
	 */
	public ControllerManagerDiSistema() {
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
		//viewCache.setNestedView(VIEW_GESTIONE_MANAGER_ESCURSIONE, anchorContent);
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
