package outdoorapp.presentation.views.managersistema;

import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericViewController;
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

public class ControllerGestioneManagerEscursione extends GenericViewController{

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
	private TableView<ManagerDiEscursioneModel> table = null;
	
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
	
	@FXML protected void modificaManagerDiEscursione(){
		mde_model = mTableManagerEscursione.getSelectionModel().getSelectedItem();
		if(mde_model != null)
			sendRequest(new Request(mde_model, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_MANAGER_DI_ESCURSIONE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Manager di Escursione Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	@FXML protected void cercaManagerDiEscursione(){
		String param = txtSearchManagerEscursione.getText();
		List<ManagerDiEscursioneTO> list_mde = new ArrayList<>();
		
		for(ManagerDiEscursioneTO mde : this.list_mde){
			if(mde.getCognome().contains(param) ||
			   mde.getNome().contains(param) ||
			   mde.getEmail().contains(param) ||
			   mde.getCodiceFiscale().contains(param)){
				list_mde.add(mde);
			}
		}
		
		ObservableList<ManagerDiEscursioneModel> data = FXCollections.observableArrayList(getListTabellaManagerDiEscursione(list_mde));
		
		this.initColumn(columnNomeManagerDiEscursione, "nome");
		this.initColumn(columnCognomeManagerDiEscursione, "cognome");
		this.initColumn(columnEmailManagerDiEscursione, "email");
		this.initColumn(columnCFManagerDiEscursione, "codice_fiscale");

		mTableManagerEscursione.setItems(data);
	}
	
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
				table = (TableView<ManagerDiEscursioneModel>) event.getSource();
				mde_model = table.getSelectionModel().getSelectedItem();
				if(mde_model != null){
					if(event.getClickCount() == 2){
		                sendRequest(new Request(mde_model, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE));
		            }
				}
			}
		});
	}
	
	private ObservableList<ManagerDiEscursioneModel> getListTabellaManagerDiEscursione(List<ManagerDiEscursioneTO> list_mde){
		ObservableList<ManagerDiEscursioneModel> res = FXCollections.observableArrayList();

		for(ManagerDiEscursioneTO mde : list_mde){
			mde_model = new ManagerDiEscursioneModel(mde);
			res.add(mde_model);
		}

		return res;
	}
	
	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}
}
