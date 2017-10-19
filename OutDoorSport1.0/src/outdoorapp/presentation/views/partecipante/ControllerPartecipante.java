package outdoorapp.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la dashboard del partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerPartecipante implements Views{

	@FXML private AnchorPane anchorContent;
	@FXML private Label lblLeMieEscursioni;
	@FXML private Label lblVisulizzaEscursioniAperte;
	@FXML private Label lblIlMioProfilo;
	private FrontController fc;
	private ViewCache vc;
	
	/**
	 * Costruttore della classe ControllerManagerDiEscursione
	 */
	public ControllerPartecipante() {
		fc = FrontController.getInstance();
		vc = ViewCache.getInstance();
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	@FXML protected void viewIlMioProfilo(){
		vc.setNestedView(VIEW_IL_MIO_PROFILO, anchorContent);
	}
	
	@FXML protected void viewVisualizzaEscursioniAperte(){
		vc.setNestedView(VIEW_VISUALIZZA_ESCURSIONI_APERTE, anchorContent);
	}
	
	@FXML protected void viewLeMieEscursioni(){
		vc.setNestedView(VIEW_LE_MIE_ESCURSIONI, anchorContent);
	}

}
