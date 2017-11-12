package outdoorapp.presentation.views.partecipante;

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
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe controller che gestisce e visualizza le escursione aperte al partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerVisualizzaEscursioniAperte extends ControllerTableView{

	@FXML private StackPane stpEscursioniAperte;
	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private TableView<EscursioneModel> mTableEscursioni;
	@FXML private TableColumn<EscursioneModel, String> mColumnNomeEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnTipoEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnDataEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMinEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMaxEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnCostoEscursione;
	
	private List<EscursioneTO> list_escursioni = new ArrayList<>();
	private EscursioneModel escursione_model = null;
	private EscursioneTO escursione = null;
	private IscrizioneTO iscrizione = null;
	
	/**
	 * Costruttore
	 */
	public ControllerVisualizzaEscursioniAperte() {
		if(escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
		if(iscrizione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
	}

	/**
	 * Metodo di inizializzazione della schermata a video
	 */
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					allEscursioniAperte();
				}
			}
		};

		stpEscursioniAperte.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Metodo di supporto che fornisce tutte le escursioni a cui l'utente si può iscrivere
	 */
	@SuppressWarnings("unchecked")
	private void allEscursioniAperte(){
		
		Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_GET_ALL_ESCURSIONI_APERTE));
		/*
		ArrayList<Object[]> objList = (ArrayList<Object[]>) response.getData();
		list_escursioni.clear();
		
		for(Object obj[] : objList){
			list_escursioni.add((EscursioneTO) obj[0]);
		}*/
		
		list_escursioni = (List<EscursioneTO>) response.getData();

		ObservableList<EscursioneModel> data = FXCollections.observableArrayList(getListTabellaEscursioni(list_escursioni));

		this.initColumn(mColumnNomeEscursione, "nome");
		this.initColumn(mColumnTipoEscursione, "nomeTipoEscursione");
		this.initColumn(mColumnDataEscursione, "data");
		this.initColumn(mColumnNMinEscursione, "numberMin");
		this.initColumn(mColumnNMaxEscursione, "numberMax");
		this.initColumn(mColumnCostoEscursione, "costo");

		mTableEscursioni.setItems(data);

		mTableEscursioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				TableView<EscursioneModel> table_escursioni = (TableView<EscursioneModel>) event.getSource();
				escursione_model = table_escursioni.getSelectionModel().getSelectedItem();
				if(escursione_model != null){
					if(event.getClickCount() == 2){
						TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
						iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
						iscrizione.setEscursione(escursione_model.getEscursione());
						iscrizione.setUtente((PartecipanteTO) SessionCache.getCurrentData("Partecipante"));
						sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_ISCRIZIONE_ESCURSIONE));
					}
				}
			}
		});
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Iscriviti ad una Escursione
	 */
	@FXML
	protected void iscrizioneEscursione(){
		escursione_model = mTableEscursioni.getSelectionModel().getSelectedItem();
		if(escursione_model != null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
			iscrizione.setEscursione(escursione_model.getEscursione());
			iscrizione.setUtente((PartecipanteTO) SessionCache.getCurrentData("Partecipante"));
			sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_ISCRIZIONE_ESCURSIONE));
		}
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessuna Escursione Selezionata", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}	
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Cerca Escursioni.
	 */
	@FXML 
	protected void cercaEscursione(){
		String param = txtSearchEscursione.getText();
		List<EscursioneTO> list_escursione = new ArrayList<>();
		
		
		for(EscursioneTO escursione : this.list_escursioni){
			if(escursione.getNome().contains(param) ||
			   escursione.getData().contains(param) ||
			   escursione.getDescrizione().contains(param) ||
			   escursione.getTipoEscursione().getNome().contains(param.toUpperCase()))
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
	 * Metodo che inizializza il modello che servirà per visualizzare i dati nella tabella
	 * 
	 * @param lista delle escursione
	 * @return res: il modello per la tabella
	 */
	private ObservableList<EscursioneModel> getListTabellaEscursioni(List<EscursioneTO> list_escursioni){
		ObservableList<EscursioneModel> res = FXCollections.observableArrayList();

		for(EscursioneTO escursione : list_escursioni){
			escursione_model = new EscursioneModel(escursione);
			res.add(escursione_model);
		}
		
		return res;
	}

}
