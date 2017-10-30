package outdoorapp.presentation.views.managerescursione;

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
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la visualizzazione di tutte le escursioni del Manager di Escursione.
 * Il Manager di Escursione può visualizzare i dettagli di una Escursione e 
 * operare su di essa.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerGestioneEscursioni extends ControllerTableView{

	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private Button btnDettagliEscursione;
	@FXML private TableView<EscursioneModel> mTableEscursioni;
	@FXML private TableColumn<EscursioneModel, String> mColumnNome;
	@FXML private TableColumn<EscursioneModel, String> mColumnTipoEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnDataEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnMin;
	@FXML private TableColumn<EscursioneModel, String> mColumnMax;
	@FXML private TableColumn<EscursioneModel, String> mColumnCosto;
	@FXML private TableColumn<EscursioneModel, String> mColumnStato;
	@FXML private StackPane stpGestioneEscursioniMDE;
	private TOFactory TOFact = null;
	private EscursioneTO escursione = null;
	private List<EscursioneTO> list_escursioni = null;
	private EscursioneModel escursione_model = null;
	
	
	public ControllerGestioneEscursioni() {
		TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
		escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue);
					allEscursioniFromMDE();
			}
		};

		stpGestioneEscursioniMDE.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Metodo che carica tutte le escursioni presenti nel sistema di un determinato Manager di Escursione
	 */
	@SuppressWarnings("unchecked")
	private void allEscursioniFromMDE(){
		Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_GET_ALL_ESCURSIONI_FROM_MDE));
		list_escursioni = (ArrayList<EscursioneTO>) response.getData();

		ObservableList<EscursioneModel> data = FXCollections.observableArrayList(getListTabellaEscursioni(list_escursioni));

		this.initColumn(mColumnNome, "nome");
		this.initColumn(mColumnTipoEscursione, "tipoEscursione");
		this.initColumn(mColumnDataEscursione, "data");
		this.initColumn(mColumnMin, "numberMin");
		this.initColumn(mColumnMax, "numberMax");
		this.initColumn(mColumnCosto, "costo");
		this.initColumn(mColumnStato, "statoEscursione");

		mTableEscursioni.setItems(data);
		
		mTableEscursioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				TableView<EscursioneModel> table_escursioni = (TableView<EscursioneModel>) event.getSource();
				escursione_model = table_escursioni.getSelectionModel().getSelectedItem();
				if(escursione_model != null){
					if(event.getClickCount() == 2){
		                sendRequest(new Request(escursione_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
		            }
				}
			}
		});
	}
	
	/**
	 * Evento associato al Button per la ricerca di una Escursione. La tabella presente nella View
	 * verrà ricaricata in base ai parametri inseriti nella casella di testo.
	 */
	@FXML protected void cercaEscursione(){
		String param = txtSearchEscursione.getText();
		List<EscursioneTO> list_escursione = new ArrayList<>();
		
		for(EscursioneTO escursione : this.list_escursioni){
			if(escursione.getNome().contains(param) ||
			   escursione.getTipoEscursione().getNome().contains(param) ||
			   escursione.getData().contains(param) ||
			   escursione.getCosto() == Double.parseDouble(param) ||
			   escursione.getStatoEscursione().getNome().equals(param)){
				list_escursione.add(escursione);
			}
		}
		
		ObservableList<EscursioneModel> data = FXCollections.observableArrayList(getListTabellaEscursioni(list_escursione));

		mTableEscursioni.setItems(data);
	}
	
	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista di Escursioni
	 * @return res: il modello per la tabella
	 */
	private ObservableList<EscursioneModel> getListTabellaEscursioni(List<EscursioneTO> list_escursione){
		ObservableList<EscursioneModel> res = FXCollections.observableArrayList();

		for(EscursioneTO escursione : list_escursione){
			escursione_model = new EscursioneModel(escursione);
			res.add(escursione_model);
		}

		return res;
	}
	
	
	/**
	 * Evento associato alla visualizzazione dei dettagli di una escursione. Una volta selezionata
	 * l'escursione desiderata, il Manager di Escursione, premendo il tasto, potrà visualizzare i 
	 * dettagli dell'Escursione
	 */
	@FXML protected void dettagliEscursione(){
		escursione_model = mTableEscursioni.getSelectionModel().getSelectedItem();
		if(escursione_model != null)
			sendRequest(new Request(escursione_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessuna Escursione Selezionata", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}

}
