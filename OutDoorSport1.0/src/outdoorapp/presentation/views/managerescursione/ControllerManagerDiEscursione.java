package outdoorapp.presentation.views.managerescursione;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del manager di escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiEscursione extends GenericViewController{

	@FXML private AnchorPane anchorContent;
	@FXML private Label lblGestisciEscursione;
	@FXML private Label lblInserisciEscursione;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerManagerDiEscursione() {
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	@FXML protected void viewGestioneEscursioni(){
		this.sendRequest(new Request(anchorContent, VIEW_GESTIONE_ESCURSIONI));
	}
	
	@FXML protected void viewInserisciEscursione(){
		this.sendRequest(new Request(anchorContent, VIEW_INSERISCI_ESCURSIONE));
	}

}
