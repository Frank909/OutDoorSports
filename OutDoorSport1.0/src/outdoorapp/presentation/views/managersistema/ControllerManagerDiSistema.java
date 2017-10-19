package outdoorapp.presentation.views.managersistema;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.views.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del manager di sistema
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerManagerDiSistema implements Views{

	@FXML private Label lblGestioneManagerEscursione;
	@FXML private Label lblVisulizzaEscursioniSistema;
	@FXML private Label lblInserisciManagerEscursione;
	@FXML private StackPane stpGestioneManagerEscursione;
	@FXML private StackPane stpVisualizzaEscursioniSistema;
	@FXML private StackPane stpRegistrazioneManagerEscursione;
	@FXML private AnchorPane anchorContent;
	private ViewCache viewCache; 
	
	/**
	 * Costruttore della classe ControllerManagerDiSistema
	 */
	public ControllerManagerDiSistema() {
		// TODO Auto-generated constructor stub
		viewCache = ViewCache.getInstance();
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
		viewCache.setNestedView(VIEW_GESTIONE_MANAGER_ESCURSIONE, anchorContent);
    }

	@FXML protected void viewGestioneManagerEscursione(){
		viewCache.setNestedView(VIEW_GESTIONE_MANAGER_ESCURSIONE, anchorContent);
	}
	
	@FXML protected void viewVisualizzaEscursioniSistema(){
		viewCache.setNestedView(VIEW_VISUALIZZA_ESCURSIONI_SISTEMA, anchorContent);
	}
	
	@FXML protected void viewInserisciManagerEscursione(){		
		viewCache.setNestedView(VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE, anchorContent);
	}

}
