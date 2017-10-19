package outdoorapp.presentation.views.managerescursione;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del manager di escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiEscursione implements Views{

	@FXML private AnchorPane anchorContent;
	@FXML private Label lblGestisciEscursione;
	@FXML private Label lblInserisciEscursione;
	private FrontController fc;
	private ViewCache vc;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerManagerDiEscursione() {
		fc = FrontController.getInstance();
		vc = ViewCache.getInstance();
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	@FXML protected void viewGestioneEscursioni(){
		vc.setNestedView(VIEW_GESTIONE_ESCURSIONI, anchorContent);
	}
	
	@FXML protected void viewInserisciEscursione(){
		vc.setNestedView(VIEW_INSERISCI_ESCURSIONE, anchorContent);
	}

}
