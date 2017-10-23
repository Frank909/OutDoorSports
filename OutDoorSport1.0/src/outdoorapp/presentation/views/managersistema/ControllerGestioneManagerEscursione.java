package outdoorapp.presentation.views.managersistema;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

/**
 * Gestisce i manager di escursione da parte del Manager di Sistema. Il Manager
 * di Sistema pu� cercare un Manager di Escursione, e una volta selezionato,
 * modificare i suoi dati oppure consultare i suoi dettagli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerGestioneManagerEscursione extends GenericViewController{

	@FXML private TextField txtSearchManagerEscursione;
	@FXML private Button btnSearchManagerEscursione;
	@FXML private TableView<ManagerDiEscursioneTO> mTableManagerEscursione;
	@FXML private TableColumn<ManagerDiEscursioneTO, String> columnNomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneTO, String> columnCognomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneTO, String> columnEmailManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneTO, String> columnCFManagerDiEscursione;
	@FXML private Button btnModificaManagerDiEscursione;
	@FXML private Button btnDettagliManagerDiEscursione;
	
	/**
	 * Costruttore della classe ControllerGestioneManagerEscursione
	 */
	public ControllerGestioneManagerEscursione() {}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		
    }

}
