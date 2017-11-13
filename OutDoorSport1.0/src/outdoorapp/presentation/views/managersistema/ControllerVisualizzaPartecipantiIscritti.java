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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.models.PartecipanteModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la visualizzazione dei Partecipanti iscritti ad una Escursione 
 * da parte del Manager di Sistema. Il Manager di Sistema può cercare un Partecipante, 
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
	@FXML private Button btnIndietro;
	@FXML private TableView<PartecipanteModel> mTablePartecipanti;
	@FXML private TableColumn<PartecipanteModel, String> mColumnName;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCognome;
	@FXML private TableColumn<PartecipanteModel, String> mColumnEmail;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCF;
	@FXML private Button btnDatiPartecipante;
	@FXML private Label lblNomeEscursione;
	private TOFactory TOFact = null;
	private PartecipanteTO partecipante = null;
	private List<PartecipanteTO> list_partecipanti = new ArrayList<>();
	private PartecipanteModel partecipante_model = null;
	private List<IscrizioneTO> list_iscrizioni = null;
	private IscrizioneTO iscrizione = null;
	private EscursioneTO escursione = null;
	
	public ControllerVisualizzaPartecipantiIscritti() {
		if(partecipante == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		}
		if(iscrizione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
		if(escursione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					iscrizione.setEscursione(escursione);
					lblNomeEscursione.setText(escursione.getNome());
					allPartecipanti();
				}
					
			}
		};

		stpPartecipantiIscrittiEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Metodo associato all'evento che ritorna alla view precedente
	 */
	@FXML protected void indietro(){
		sendRequest(new Request(escursione, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_SISTEMA));
	}

	/**
	 * Metodo che carica la view con i dettagli del partecipante iscritto
	 */
	@FXML protected void visualizzaDatiPartecipante(){
		partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
		if(partecipante_model != null)
			sendRequest(new Request(partecipante_model.getPartecipante(), ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_PARTECIPANTE_SISTEMA));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Partecipante Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Evento associato al Button per la ricerca di un Manager di Escursione. La tabella presente nella View
	 * verrà ricaricata in base ai parametri inseriti nella casella di testo.
	 */
	@FXML protected void cercaPartecipante(){
		String param = txtSearchPartecipante.getText();
		List<PartecipanteTO> list_partecipanti = new ArrayList<>();
		
		for(PartecipanteTO partecipante : this.list_partecipanti){
			if(partecipante.getCognome().contains(param) ||
			   partecipante.getNome().contains(param) ||
			   partecipante.getEmail().contains(param) ||
			   partecipante.getCodiceFiscale().contains(param)){
				list_partecipanti.add(partecipante);
			}
		}
		
		ObservableList<PartecipanteModel> data = FXCollections.observableArrayList(getListTabellaPartecipanti(list_partecipanti));

		mTablePartecipanti.setItems(data);
	}
	
	/**
	 * Metodo che carica tutti i Partecipanti iscritti all'escursione presente nel database
	 */
	@SuppressWarnings("unchecked")
	private void allPartecipanti(){
		Response response = this.sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ALL_ISCRITTI_FROM_ESCURSIONE));
		list_iscrizioni = (ArrayList<IscrizioneTO>) response.getData();
		
		list_partecipanti.clear();
		for(IscrizioneTO i : list_iscrizioni){
			list_partecipanti.add((PartecipanteTO) i.getUtente());
		}

		ObservableList<PartecipanteModel> data = FXCollections.observableArrayList(getListTabellaPartecipanti(list_partecipanti));

		this.initColumn(mColumnName, "nome");
		this.initColumn(mColumnCognome, "cognome");
		this.initColumn(mColumnEmail, "email");
		this.initColumn(mColumnCF, "codice_fiscale");

		mTablePartecipanti.setItems(data);
		
		mTablePartecipanti.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				mTablePartecipanti = (TableView<PartecipanteModel>) event.getSource();
				partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
				if(partecipante_model != null){
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
		                visualizzaDatiPartecipante();
		            }
				}
			}
		});
	}
	
	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista di Partecipanti
	 * @return res: il modello per la tabella
	 */
	private ObservableList<PartecipanteModel> getListTabellaPartecipanti(List<PartecipanteTO> param){
		ObservableList<PartecipanteModel> res = FXCollections.observableArrayList();
		PartecipanteModel partecipante_model = null;
		
		for(PartecipanteTO partecipante : param){
			partecipante_model = new PartecipanteModel(partecipante);
			res.add(partecipante_model);
		}
		return res;
	}
}
