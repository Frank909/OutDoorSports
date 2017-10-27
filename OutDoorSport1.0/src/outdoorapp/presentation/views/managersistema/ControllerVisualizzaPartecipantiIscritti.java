package outdoorapp.presentation.views.managersistema;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.presentation.views.models.PartecipanteModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la visualizzazione dei Partecipanti iscritti ad una Escursione 
 * da parte del Manager di Sistema. Il Manager di Sistema può cercare un Partecipane, 
 * e, una volta selezionato, consultare i suoi dettagli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerVisualizzaPartecipantiIscritti extends ControllerTableView{

	@FXML private TextField txtSearchPartecipante;
	@FXML private Button btnSearchPartecipante;
	@FXML private StackPane stpPartecipantiIscrittiEscursione;
	@FXML private TableView<PartecipanteModel> mTablePartecipanti;
	@FXML private TableColumn<PartecipanteModel, String> mColumnName;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCognome;
	@FXML private TableColumn<PartecipanteModel, String> mColumnEmail;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCF;
	@FXML private Button btnDatiPartecipante;
	private TOFactory TOFact = null;
	private PartecipanteTO partecipante = null;
	private List<PartecipanteTO> list_partecipanti = null;
	private PartecipanteModel partecipante_model = null;
	private ManagerDiEscursioneTO mde = null;
	
	public ControllerVisualizzaPartecipantiIscritti() {
		TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
		partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		mde = (ManagerDiEscursioneTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiEscursione);
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		/*ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue)
					allPartecipanti();
			}
		};

		stpPartecipantiIscrittiEscursione.visibleProperty().addListener(visibilityListener);*/
	}
	
	@FXML protected void getDettagliPartecipante(){
		dettagliPartecipante();
	}
	
	/**
	 * Evento associato al Button per la ricerca di un Manager di Escursione. La tabella presente nella View
	 * verrà ricaricata in base ai parametri inseriti nella casella di testo.
	 */
	@FXML protected void cercaPartecipante(){
		/*String param = txtSearchPartecipante.getText();
		List<PartecipanteTO> list_partecipanti = new ArrayList<>();
		
		for(PartecipanteTO partecipante : this.list_partecipanti){
			if(partecipante.getCognome().contains(param) ||
			   partecipante.getNome().contains(param) ||
			   partecipante.getEmail().contains(param) ||
			   partecipante.getCodiceFiscale().contains(param)){
				list_partecipanti.add(partecipante);
			}
		}
		
		ObservableList<PartecipanteModel> data = FXCollections.observableArrayList(getListTabellaDettagliPartecipante(list_partecipanti));

		mTablePartecipanti.setItems(data);*/
	}
	
	/**
	 * Metodo che carica la View con i dettagli di un determinato Manager di Escursione.
	 */
	private void dettagliPartecipante(){
		partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
		if(partecipante_model != null)
			sendRequest(new Request(partecipante_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_PARTECIPANTE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Partecipante Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Metodo che carica tutti i Manager di Escursione presente nel database
	 */
	@SuppressWarnings("unchecked")
	private void allManagerDiEscursione(){
		/*Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_GET_ALL_PARTECIPANTI_FOR_MDE));
		list_partecipanti = (ArrayList<PartecipanteTO>) response.getData();

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
					if(event.getClickCount() == 2){
		                sendRequest(new Request(mde_model, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE));
		            }
				}
			}
		});*/
	}
	
	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista di Manager di Escursione
	 * @return res: il modello per la tabella
	 */
	/*private ObservableList<ManagerDiEscursioneModel> getListTabellaManagerDiEscursione(List<ManagerDiEscursioneTO> list_mde){
		ObservableList<ManagerDiEscursioneModel> res = FXCollections.observableArrayList();

		for(ManagerDiEscursioneTO mde : list_mde){
			mde_model = new ManagerDiEscursioneModel(mde);
			res.add(mde_model);
		}

		return res;
	}*/

}
