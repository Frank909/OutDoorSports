package outdoorapp.presentation.views.managerescursione;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.models.EscursioneModel;
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
 * Gestisce la visualizzazione di tutti i Partecipanti Iscritti 
 * a una determinata Escursione. Il Manager di Escursione pu� 
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
	@FXML private Button btnModificaDatiEscursione;
	@FXML private Button btnEliminaIscrizione;
	@FXML private StackPane stpVisualizzaPartecipantiIscrittiEscursione;
	private TOFactory TOFact = null;
	private PartecipanteTO partecipante = null;
	private List<PartecipanteTO> list_partecipanti = new ArrayList<>();
	private List<IscrizioneTO> list_iscrizioni = null;
	private IscrizioneTO iscrizione = null;
	private PartecipanteModel partecipante_model = null;
	private EscursioneModel escursione = new EscursioneModel();
	private TableView<PartecipanteModel> table_iscritti = null;
	
	public ControllerVisualizzaIscritti() {
		if(partecipante == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		}
		if(iscrizione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneModel) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					lblNomeEscursione.setText(escursione.getEscursione().getNome());
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
				table_iscritti = (TableView<PartecipanteModel>) event.getSource();
				partecipante_model = table_iscritti.getSelectionModel().getSelectedItem();
				if(partecipante_model != null){
					if(event.getClickCount() == 2){
		                //sendRequest(new Request(partecipante_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
		            }
				}
			}
		});
	}
	
	/**
	 * Metodo che inizializza il modello che servir� per visualizzare i dati nella tabella
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
	 * La tabella presente nella View verr� ricaricata in base 
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
			   partecipante.getCertificatoSrc().equals(param) ||
			   partecipante.getUsername().equals(param)){
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
		partecipante_model = table_iscritti.getSelectionModel().getSelectedItem();
	}

}