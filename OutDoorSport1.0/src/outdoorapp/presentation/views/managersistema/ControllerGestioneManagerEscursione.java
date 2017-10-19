package outdoorapp.presentation.views.managersistema;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.to.ManagerDiEscursione;

/**
 * Gestisce i manager di escursione da parte del Manager di Sistema. Il Manager
 * di Sistema può cercare un Manager di Escursione, e una volta selezionato,
 * modificare i suoi dati oppure consultare i suoi dettagli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerGestioneManagerEscursione {

	@FXML private TextField txtSearchManagerEscursione;
	@FXML private Button btnSearchManagerEscursione;
	@FXML private TableView<ManagerDiEscursione> mTableManagerEscursione;
	@FXML private TableColumn<ManagerDiEscursione, String> columnNomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursione, String> columnCognomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursione, String> columnEmailManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursione, String> columnCFManagerDiEscursione;
	@FXML private Button btnModificaManagerDiEscursione;
	@FXML private Button btnDettagliManagerDiEscursione;
	private FrontController fc;
	
	/**
	 * Costruttore della classe ControllerGestioneManagerEscursione
	 */
	public ControllerGestioneManagerEscursione() {
		fc = FrontController.getInstance();
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
    }

}
