package outdoorapp.presentation.views.managersistema;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;

/**
 * Gestisce i manager di escursione da parte del Manager di Sistema. Il Manager
 * di Sistema può cercare un Manager di Escursione, e una volta selezionato,
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
	private TOFactory TOFact = null;
	private ManagerDiEscursioneTO mde = null;
	
	/**
	 * Costruttore della classe ControllerGestioneManagerEscursione
	 */
	public ControllerGestioneManagerEscursione() {
		 TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
		 mde = (ManagerDiEscursioneTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiEscursione);
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
    }
	
	private void allManagerDiEscursione(){
		Response response = this.sendRequest(new Request(mde, OUTDOORSPORT_GET_ALL_MDE));
		ArrayList<ManagerDiEscursioneTO> list_mde = (ArrayList<ManagerDiEscursioneTO>) response.getData();
		
		ObservableList<ManagerDiEscursioneTO> data = null;
		data.addAll(list_mde);
		mTableManagerEscursione.setItems(data);
		mTableManagerEscursione.getColumns().addAll(columnNomeManagerDiEscursione, 
				columnCognomeManagerDiEscursione, columnEmailManagerDiEscursione, columnCFManagerDiEscursione);
	}
	
	@FXML protected void getAllManagerDiEscursione(){
		allManagerDiEscursione();
	}

}
