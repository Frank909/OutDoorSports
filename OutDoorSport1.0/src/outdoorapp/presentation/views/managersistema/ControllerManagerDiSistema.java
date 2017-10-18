package outdoorapp.presentation.views.managersistema;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import outdoorapp.utils.Forms;
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
	
	/**
	 * Costruttore della classe ControllerManagerDiSistema
	 */
	public ControllerManagerDiSistema() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }

	@FXML protected void viewGestioneManagerEscursione(){
		try {
			loadPanel(anchorContent, stpGestioneManagerEscursione, VIEW_GESTIONE_MANAGER_ESCURSIONE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML protected void viewVisualizzaEscursioniSistema(){
		try {
			loadPanel(anchorContent, stpVisualizzaEscursioniSistema, VIEW_VISUALIZZA_ESCURSIONI_SISTEMA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML protected void viewInserisciManagerEscursione(){
		try {
			loadPanel(anchorContent, stpRegistrazioneManagerEscursione, VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadPanel(AnchorPane anchorpane, StackPane panel, String panelName) throws IOException{
		panel = (StackPane)FXMLLoader.load(Forms.class.getResource(panelName + ".fxml"));
		AnchorPane.setLeftAnchor(panel, 0.0);
		AnchorPane.setRightAnchor(panel, 0.0);
		AnchorPane.setTopAnchor(panel, 0.0);
		AnchorPane.setBottomAnchor(panel, 0.0);
		anchorpane.getChildren().clear();
		anchorpane.getChildren().add(panel);
	}
}
