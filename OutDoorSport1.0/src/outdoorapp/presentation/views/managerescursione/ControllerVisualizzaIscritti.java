package outdoorapp.presentation.views.managerescursione;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.PartecipanteModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;
/**
 * Gestisce la visualizzazione di tutti i Partecipanti Iscritti 
 * a una determinata Escursione. Il Manager di Escursione può 
 * modificare l'Iscrizione o eliminare l'Iscrizione
 * operare su di essa.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerVisualizzaIscritti extends ControllerTableView{

	@FXML private TextField txtSearchPartecipante;
	@FXML private Button btnSearchPartecipante;
	@FXML private TableView<PartecipanteModel> mTablePartecipanti;
	@FXML private TableColumn<PartecipanteModel, String> mColumnNomePartecipante;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCognomePartecipante;
	@FXML private TableColumn<PartecipanteModel, String> mColumnEmailPartecipante;
	@FXML private TableColumn<PartecipanteModel, String> mColumnCFPartecipante;
	@FXML private TableColumn<PartecipanteModel, String> mColumnDataCertificatoPartecipante;
	@FXML private TableColumn<PartecipanteModel, String> mColumnUsernamePartecipante;
	@FXML private Label lblNomeEscursione;
	@FXML private Button btnIndietro;
	@FXML private Button btnModificaDatiEscursione;
	@FXML private Button btnEliminaIscrizione;
	@FXML private StackPane stpVisualizzaPartecipantiIscrittiEscursione;
	private TOFactory TOFact = null;
	private PartecipanteTO partecipante = null;
	private List<PartecipanteTO> list_partecipanti = new ArrayList<>();
	private List<IscrizioneTO> list_iscrizioni = null;
	private IscrizioneTO iscrizione = null;
	private PartecipanteModel partecipante_model = null;
	private EscursioneTO escursione = null;
	
	public ControllerVisualizzaIscritti() {
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

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					iscrizione.setEscursione(escursione);
					lblNomeEscursione.setText(escursione.getNome());
					allPartecipantiIscritti();
				}
			}
		};

		stpVisualizzaPartecipantiIscrittiEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	@FXML protected void cercaPartecipante(){
		findPartecipante();
	}
	
	/**
	 * Metodo che carica tutti i Partecipanti Iscritti all'Escursione
	 * all'Escursione
	 */
	@SuppressWarnings("unchecked")
	private void allPartecipantiIscritti(){
		Response response = this.sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ALL_ISCRITTI_FROM_ESCURSIONE));
		list_iscrizioni = (ArrayList<IscrizioneTO>) response.getData();
		
		list_partecipanti.clear();
		for(IscrizioneTO i : list_iscrizioni){
			list_partecipanti.add((PartecipanteTO) i.getUtente());
		}

		ObservableList<PartecipanteModel> data = FXCollections.observableArrayList(getListTabellaPartecipanti(list_partecipanti));

		this.initColumn(mColumnNomePartecipante, "nome");
		this.initColumn(mColumnCognomePartecipante, "cognome");
		this.initColumn(mColumnEmailPartecipante, "email");
		this.initColumn(mColumnCFPartecipante, "codice_fiscale");
		this.initColumn(mColumnDataCertificatoPartecipante, "dataCertificatoSRC");
		this.initColumn(mColumnUsernamePartecipante, "username");

		mTablePartecipanti.setItems(data);
		
		mTablePartecipanti.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				mTablePartecipanti = (TableView<PartecipanteModel>) event.getSource();
				partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
				if(partecipante_model != null){
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
		                updateDatiIscrizione();
		            }
				}
			}
		});
	}
	
	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista di Partecipanti iscritti
	 * @return res: il modello per la tabella
	 */
	private ObservableList<PartecipanteModel> getListTabellaPartecipanti(List<PartecipanteTO> list_partecipanti){
		ObservableList<PartecipanteModel> res = FXCollections.observableArrayList();

		for(PartecipanteTO iscritto : list_partecipanti){
			partecipante_model = new PartecipanteModel(iscritto);
			res.add(partecipante_model);
		}
		
		return res;
	}
	
	/**
	 * Evento associato al Button per la ricerca di un Partecipante iscritto. 
	 * La tabella presente nella View verrà ricaricata in base 
	 * ai parametri inseriti nella casella di testo.
	 */
	@FXML protected void findPartecipante(){
		String param = txtSearchPartecipante.getText();
		List<PartecipanteTO> list_partecipante = new ArrayList<>();
		
		for(PartecipanteTO partecipante : this.list_partecipanti){
			if(partecipante.getNome().contains(param) ||
			   partecipante.getCognome().contains(param) ||
			   partecipante.getEmail().contains(param) ||
			   partecipante.getCodiceFiscale().contains(param) ||
			   partecipante.getCertificatoSrc().contains(param) ||
			   partecipante.getUsername().contains(param)){
			   list_partecipante.add(partecipante);
			}
		}
		
		ObservableList<PartecipanteModel> data = FXCollections.observableArrayList(getListTabellaPartecipanti(list_partecipante));

		mTablePartecipanti.setItems(data);
	}
	
	/**
	 * Metodo che cancella l'iscrizione di un determinato Partecipante,
	 * e gli viene inviata una mail di conferma.
	 */
	private void deleteIscrizione(){
		partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
		for(IscrizioneTO iscrizione : list_iscrizioni){
			if(iscrizione.getUtente().equals(partecipante_model.getPartecipante())){
				this.iscrizione = iscrizione;
			}
		}
		
		if(!(partecipante_model == null)){
			Response response = this.sendRequest(new Request(this.iscrizione, OUTDOORSPORT_ANNULLA_ISCRIZIONE));
			allPartecipantiIscritti();
			if(response.getData() != null){
				Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Sei sicuro di voler annullare l'iscrizione?");
				Optional<ButtonType> result = alertConfirm.showAndWait();
				if (result.get() == ButtonType.OK){
				    Alert alert = new Alert(AlertType.INFORMATION, "Iscrizione annullata con successo!", ButtonType.OK);
				    alert.setTitle("OutDoorSport1.0");
				    alert.showAndWait();
				} else {
					alertConfirm.close();
					Alert alert = new Alert(AlertType.INFORMATION, "Non è stata apportata nessuna modifica all'iscrizione.", ButtonType.OK);
					alert.setTitle("OutDoorSport1.0");
					alert.showAndWait();
				}
			}else{
				Alert alert = new Alert(AlertType.INFORMATION, "Non è stata apportata nessuna modifica all'iscrizione.", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
			}

		}else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Partecipante Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Evento associato all'eliminazione di una iscrizione in una escursione
	 * da parte del Manager di Escursione
	 */
	@FXML protected void eliminaIscrizione(){
		deleteIscrizione();
	}
	
	/**
	 * Ritorna alla schermata precedente
	 */
	@FXML protected void indietro(){
		sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
	}
	
	/**
	 * Visualizza i dettagli dell'iscrizione per un determinato iscritto
	 * e può modificare gli optional e di conseguenza, il costo risultante
	 */
	@FXML protected void modificaDatiIscrizione(){
		partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
		if(partecipante_model != null){
			for(IscrizioneTO iscrizione : list_iscrizioni){
				if(iscrizione.getUtente().equals(partecipante_model.getPartecipante())){
					this.iscrizione = iscrizione;
				}
			}
			sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE));
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Partecipante Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Visualizza i dettagli dell'iscrizione di un iscritto cliccando
	 * direttamente sulla tabella. Può modificare gli optional
	 * e, di conseguenza, il costo risultante.
	 */
	private void updateDatiIscrizione(){
		partecipante_model = mTablePartecipanti.getSelectionModel().getSelectedItem();
		for(IscrizioneTO iscrizione : list_iscrizioni){
			if(iscrizione.getUtente().equals(partecipante_model.getPartecipante())){
				this.iscrizione = iscrizione;
			}
		}
		sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE));
	}

}
