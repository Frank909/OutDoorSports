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
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la visualizzazione di tutte le escursioni presenti nel sistema.
 * Il Manager di Sistema può visualizzare i dettagli di una Escursione
 * e visualizzare i Partecipanti iscritti
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerVisualizzaEscursioniSistema extends ControllerTableView{

	@FXML private StackPane stpVisualizzaEscursioniSistema;
	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private Button btnDetailEscursione;
	@FXML private TableView<EscursioneModel> mTableEscursioni;
	@FXML private TableColumn<EscursioneModel, String> mColumnNome;
	@FXML private TableColumn<EscursioneModel, String> mColumnTipoEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnDataEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnMin;
	@FXML private TableColumn<EscursioneModel, String> mColumnMax;
	@FXML private TableColumn<EscursioneModel, String> mColumnCosto;
	@FXML private TableColumn<EscursioneModel, String> mColumnStato;
	private TOFactory TOFact = null;
	private EscursioneTO escursione = null;
	private List<EscursioneTO> list_escursioni = null;
	private EscursioneModel escursione_model = null;
	
	/**
	 * Costruttore della classe ControllerVisualizzaEscursioniSistema
	 */
	public ControllerVisualizzaEscursioniSistema() {
		TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
		escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue)
					allEscursioni();
			}
		};

		stpVisualizzaEscursioniSistema.visibleProperty().addListener(visibilityListener);
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
					escursione.getData().contains(param) ||
					escursione.getDescrizione().contains(param) ||
					escursione.getTipoEscursione().getNome().contains(param.toUpperCase())||
					escursione.getStatoEscursione().getNome().contains(param.toUpperCase()))
				list_escursione.add(escursione);
			else
				try{
					if(escursione.getNumberMax() == Integer.parseInt(param) || 
							escursione.getNumberMin() == Integer.parseInt(param) ||
							escursione.getCosto() == Double.parseDouble(param))
						list_escursione.add(escursione);
				}catch(Exception e){
				}
		}
		
		ObservableList<EscursioneModel> data = FXCollections.observableArrayList(getListTabellaEscursioni(list_escursione));

		mTableEscursioni.setItems(data);
	}
	
	/**
	 * Evento associato alla visualizzazione dei dettagli di una escursione. Una volta selezionata
	 * l'escursione desiderata, il Manager di Sistema, premendo il tasto, potrà visualizzare i 
	 * dettagli dell'Escursione
	 */
	@FXML protected void dettagliEscursione(){
		escursione_model = mTableEscursioni.getSelectionModel().getSelectedItem();
		if(escursione_model != null)
			sendRequest(new Request(escursione_model, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_SISTEMA));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessuna Escursione Selezionata", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Metodo che carica tutte le escursioni presenti nel sistema
	 */
	@SuppressWarnings("unchecked")
	private void allEscursioni(){
		Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_GET_ALL_ESCURSIONI));
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
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
		                sendRequest(new Request(escursione_model.getEscursione(), ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_SISTEMA));
		            }
				}
			}
		});
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
}
