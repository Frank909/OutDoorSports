package outdoorapp.presentation.views.partecipante;

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
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.EmailConfig;
import outdoorapp.utils.SessionCache;

/**
 * Classe controller che gestisce la visualizzazione delle escursioni
 * a cui il partecipante è iscritto.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerLeMieEscursioni extends ControllerTableView{

	@FXML private StackPane stpLeMieEscursioniPartecipante;
	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private TableView<EscursioneModel> mTableEscursioni;
	@FXML private TableColumn<EscursioneModel, String> mColumnNameEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnTipoEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnDataEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMinEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMaxEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnCostoEscursione;
	@FXML private Button btnCancelEscursione, btnModificaIscrizione;

	private List<EscursioneTO> list_escursioni = new ArrayList<>();
	private EscursioneModel escursione_model = null;
	private EscursioneTO escursione = null;
	private IscrizioneTO iscrizione = null;
	private TOFactory TOFact = null;
	private List<StatoIscrizioneTO> list_stato_iscrizione = new ArrayList<>();

	public ControllerLeMieEscursioni() {
		if(escursione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
		if(iscrizione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
	}

	/**
	 * Metodo di inizializzazione dell'interfaccia
	 */
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					allEscursioniIscritte();
				}
			}
		};

		stpLeMieEscursioniPartecipante.visibleProperty().addListener(visibilityListener);
	}

	/**
	 * Metodo di supporto che fornisce tutte le escursioni a cui l'utente è iscritto
	 */
	@SuppressWarnings("unchecked")
	private void allEscursioniIscritte(){
		PartecipanteTO partecipante = (PartecipanteTO) SessionCache.getCurrentData("Partecipante");
		Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_GET_ALL_ESCURSIONI_ISCRITTE));
		
		ArrayList<Object[]> objList = (ArrayList<Object[]>) response.getData();
		list_escursioni.clear();
		
		for(Object obj[] : objList){
			list_escursioni.add((EscursioneTO) obj[0]);
		}
		//list_escursioni = (List<EscursioneTO>) response.getData();

		ObservableList<EscursioneModel> data = FXCollections.observableArrayList(getListTabellaEscursioni(list_escursioni));

		this.initColumn(mColumnNameEscursione, "nome");
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
						TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
						iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
						iscrizione.setEscursione(escursione_model.getEscursione());
						iscrizione.setUtente((PartecipanteTO) SessionCache.getCurrentData("Partecipante"));
						Response response = sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ISCRIZIONE_FROM_ESCURSIONE));
						if(response.toString().equals(RESP_OK)){
							iscrizione = (IscrizioneTO) response.getData();
							sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE_PARTECIPANTE));
						}
					}
				}
			}
		});
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

	/**
	 * Metodo associato all'evento del click del bottone Cerca Escursioni.
	 */
	@FXML protected void cercaEscursione(){
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

	@FXML
	protected void cancellatiEscursione(){

		escursione_model = mTableEscursioni.getSelectionModel().getSelectedItem();
		if(escursione_model != null){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Sei sicuro di voler cancellare l'iscrizione selezionata?");
			alert.setTitle("OutDoorSport1.0");
			Optional<ButtonType> res = alert.showAndWait();

			if(res.get() == ButtonType.OK){
				PartecipanteTO partecipante = (PartecipanteTO) SessionCache.getCurrentData("Partecipante");
				iscrizione.setUtente(partecipante);
				iscrizione.setEscursione(escursione_model.getEscursione());
				Response response = sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ISCRIZIONE_FROM_ESCURSIONE));
				if(response.toString().equals(RESP_OK)){
					iscrizione = (IscrizioneTO) response.getData();
					Response rsp = sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ALL_STATO_ESCURSIONE));
					list_stato_iscrizione = (List<StatoIscrizioneTO>) rsp.getData();
					iscrizione.setStatoIscrizione(list_stato_iscrizione.get(0));
					Response resp = this.sendRequest(new Request(iscrizione, OUTDOORSPORT_DELETE_ISCRIZIONE_FROM_ESCURSIONE_PARTECIPANTE));
					if(resp.toString().equals(RESP_OK)){
						EmailConfig emailConfig = new EmailConfig();
						EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);
						ArrayList<UtenteTO> dest = new ArrayList<>();
						ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO)iscrizione.getEscursione().getUtente();
						dest.add(mde);
						String mailOggetto = "OutDoorSports | Disiscrizione Partecipante";
						String mailMessaggio = "Gentile ";
						mailMessaggio += mde.getNome() + " " + mde.getCognome() + ", \n";
						mailMessaggio += "Il partecipante " + partecipante.getNome() + " " + partecipante.getCognome();
						mailMessaggio += "con codice fiscale " + partecipante.getCodiceFiscale();
						mailMessaggio += "ha scelto di disiscriversi dall'escursione " + iscrizione.getEscursione().getNome();

						email.setOggetto(mailOggetto);
						email.setMessaggio(mailMessaggio);

						email.setListaDestinatari(dest);
						emailConfig.sendEmail(email);
						allEscursioniIscritte();
					}
				}

			}
		}else{
			Alert alert1 = new Alert(AlertType.ERROR, "Nessuna Escursione Selezionata", ButtonType.OK);
			alert1.setTitle("OutDoorSport1.0");
			alert1.showAndWait();
		}		

	}

	@FXML
	protected void modificaIscrizione(){
		escursione_model = mTableEscursioni.getSelectionModel().getSelectedItem();
		if(escursione_model != null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
			iscrizione.setEscursione(escursione_model.getEscursione());
			iscrizione.setUtente((PartecipanteTO) SessionCache.getCurrentData("Partecipante"));
			Response response = sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ISCRIZIONE_FROM_ESCURSIONE));
			if(response.toString().equals(RESP_OK)){
				iscrizione = (IscrizioneTO) response.getData();
				sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE_PARTECIPANTE));
			}
		}
		else{
			Alert alert = new Alert(AlertType.ERROR, "Nessuna Escursione Selezionata", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}	
	}


}
