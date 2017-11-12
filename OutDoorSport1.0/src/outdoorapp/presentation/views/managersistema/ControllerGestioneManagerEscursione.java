package outdoorapp.presentation.views.managersistema;

import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
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

public class ControllerGestioneManagerEscursione extends ControllerTableView{
	
	@FXML private TextField txtSearchManagerEscursione;
	@FXML private Button btnSearchManagerEscursione;
	@FXML private StackPane stpGestioneManagerEscursione;
	@FXML private TableView<ManagerDiEscursioneModel> mTableManagerEscursione;
	@FXML private TableColumn<ManagerDiEscursioneModel, String> columnNomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneModel, String> columnCognomeManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneModel, String> columnEmailManagerDiEscursione;
	@FXML private TableColumn<ManagerDiEscursioneModel, String> columnCFManagerDiEscursione;
	@FXML private Button btnModificaManagerDiEscursione;
	@FXML private Button btnDettagliManagerDiEscursione;
	private TOFactory TOFact = null;
	private ManagerDiEscursioneTO mde = null;
	private List<ManagerDiEscursioneTO> list_mde = null;
	private ManagerDiEscursioneModel mde_model = null;
	
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
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue)
					allManagerDiEscursione();
			}
		};

		stpGestioneManagerEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	@FXML protected void getDettagliManagerDiEscursione(){
		dettagliManagerDiEscursione();
	}
	
	/**
	 * Evento associato al Button per la modifica di un Manager di Escursione. Viene inviata una 
	 * richiesta per il caricamento della view desiderata e vengono mandati i dati necessari
	 */
	@FXML protected void modificaManagerDiEscursione(){
		mde_model = mTableManagerEscursione.getSelectionModel().getSelectedItem();
		if(mde_model != null)
			sendRequest(new Request(mde_model.getManagerDiEscursione(), ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_MANAGER_DI_ESCURSIONE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Manager di Escursione Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Evento associato al Button per la ricerca di un Manager di Escursione. La tabella presente nella View
	 * verrà ricaricata in base ai parametri inseriti nella casella di testo.
	 */
	@FXML protected void cercaManagerDiEscursione(){
		String param = txtSearchManagerEscursione.getText();
		List<ManagerDiEscursioneTO> list_mde = new ArrayList<>();
		String upperCaseParam = capitalizeWord(param);
		
		for(ManagerDiEscursioneTO mde : this.list_mde){
			if(mde.getCognome().contains(param) || mde.getCognome().contains(upperCaseParam) ||
			   mde.getNome().contains(param) || mde.getNome().contains(upperCaseParam) ||
			   param.contains(mde.getCognome()) || param.contains(mde.getNome()) ||
			   upperCaseParam.contains(mde.getCognome()) || upperCaseParam.contains(mde.getNome()) ||
			   mde.getEmail().contains(param) ||
			   mde.getCodiceFiscale().contains(param)){
				list_mde.add(mde);
			}
		}
		
		ObservableList<ManagerDiEscursioneModel> data = FXCollections.observableArrayList(getListTabellaManagerDiEscursione(list_mde));

		mTableManagerEscursione.setItems(data);
	}
	
	/**
	 * Metodo che carica la View con i dettagli di un determinato Manager di Escursione.
	 */
	private void dettagliManagerDiEscursione(){
		mde_model = mTableManagerEscursione.getSelectionModel().getSelectedItem();
		if(mde_model != null)
			sendRequest(new Request(mde_model, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Manager di Escursione Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Metodo che carica tutti i Manager di Escursione presente nel database
	 */
	@SuppressWarnings("unchecked")
	private void allManagerDiEscursione(){
		Response response = this.sendRequest(new Request(mde, OUTDOORSPORT_GET_ALL_MDE));
		list_mde = (ArrayList<ManagerDiEscursioneTO>) response.getData();

		ObservableList<ManagerDiEscursioneModel> data = FXCollections.observableArrayList(getListTabellaManagerDiEscursione(list_mde));

		this.initColumn(columnNomeManagerDiEscursione, "nome");
		this.initColumn(columnCognomeManagerDiEscursione, "cognome");
		this.initColumn(columnEmailManagerDiEscursione, "email");
		this.initColumn(columnCFManagerDiEscursione, "codice_fiscale");

		mTableManagerEscursione.setItems(data);
		
		mTableManagerEscursione.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				TableView<ManagerDiEscursioneModel> table = (TableView<ManagerDiEscursioneModel>) event.getSource();
				mde_model = table.getSelectionModel().getSelectedItem();
				if(mde_model != null){
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
		                sendRequest(new Request(mde_model, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE));
		            }
				}
			}
		});
	}
	
	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista di Manager di Escursione
	 * @return res: il modello per la tabella
	 */
	private ObservableList<ManagerDiEscursioneModel> getListTabellaManagerDiEscursione(List<ManagerDiEscursioneTO> list_mde){
		ObservableList<ManagerDiEscursioneModel> res = FXCollections.observableArrayList();

		for(ManagerDiEscursioneTO mde : list_mde){
			mde_model = new ManagerDiEscursioneModel(mde);
			res.add(mde_model);
		}

		return res;
	}
}
