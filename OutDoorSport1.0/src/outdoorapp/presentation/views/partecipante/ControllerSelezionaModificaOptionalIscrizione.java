package outdoorapp.presentation.views.partecipante;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.models.OptionalModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe Controller che gestisce la scelta degli
 * optional da parte di una Partecipante.
 * Il Partecipante può scegliere gli optional, e verrà
 * calcolato il costo risultante in base agli optional scelti.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerSelezionaModificaOptionalIscrizione extends ControllerTableView{

	@FXML private StackPane stpSelezionaModificaOptional;
	@FXML private TableView<OptionalModel> tableOptionalInseriti;
	@FXML private TableColumn<OptionalModel, String> columnOptionalInseriti;
	@FXML private TableColumn<OptionalModel, String> columnPrezzoInseriti;
	@FXML private TableColumn<OptionalModel, String> columnTipoOptionalInseriti;
	@FXML private TableView<OptionalModel> tableOptionalDisponibili;
	@FXML private TableColumn<OptionalModel, String> columnOptionalDisponibili;
	@FXML private TableColumn<OptionalModel, String> columnPrezzoDisponibili;
	@FXML private TableColumn<OptionalModel, String> columnTipoOptionalDisponibili;
	@FXML private Button btnInserisciOptional;
	@FXML private Button btnRimuoviOptional;
	@FXML private Label lblPrezzoTotaleOptional;
	@FXML private Label lblPrezzoTotale;
	@FXML private Button btnIndietro;
	@FXML private Button btnConferma;
	private OptionalModel optional_disponibili_model = null;
	private OptionalModel optional_scelti_model = null;
	private OptionalEscursioneTO optional_escursione;
	private OptionalTO optional = null;
	private IscrizioneTO iscrizione = null;
	private IscrizioneTO iscrizioneOld = null;
	private Set<OptionalEscursioneTO> all_optional_scelti = null;
	private Set<OptionalEscursioneTO> all_optional_disponibili = null;
	private Set<OptionalEscursioneTO> all_current_optional_disponibili = null;
	private StatoOptionalTO stato_optional = null;
	private List<StatoOptionalTO> list_stato_optional = new ArrayList<>();


	public ControllerSelezionaModificaOptionalIscrizione() {
		if(optional == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
			optional = (OptionalTO) TOFact.getOptionalTO(OptionalEnum.Optional);
		}
		if(stato_optional == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
			stato_optional = (StatoOptionalTO) TOFact.getStatoTO(StatoEnum.StatoOptional);
		}
		if(iscrizione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
		if(iscrizioneOld == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizioneOld = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
		if(optional_escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
			optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					iscrizione = (IscrizioneTO) SessionCache.getCurrentData(iscrizione.getClass().getSimpleName());
					iscrizioneOld = (IscrizioneTO) SessionCache.getCurrentData(iscrizione.getClass().getSimpleName());
					Response response = sendRequest(new Request(stato_optional, OUTDOORSPORT_GET_ALL_STATI_OPTIONAL));
					list_stato_optional = (List<StatoOptionalTO>) response.getData();
					lblPrezzoTotale.setText("0 €");
					lblPrezzoTotaleOptional.setText("0 €");

					optional_escursione.setEscursione(iscrizione.getEscursione());
					Response resp = sendRequest(new Request(optional_escursione, OUTDOORSPORT_GET_OPTIONAL_FROM_ESCURSIONE));
					Set<OptionalEscursioneTO> temp = new HashSet<>();
					temp.addAll((Collection<? extends OptionalEscursioneTO>) resp.getData());
					all_optional_disponibili = new HashSet<>();
					all_optional_disponibili.addAll(temp);
					all_optional_scelti = new HashSet<>();
					if(iscrizione.getOptionals() != null)
						all_optional_scelti.addAll(iscrizione.getOptionals());
					all_current_optional_disponibili = new HashSet<>();
					all_current_optional_disponibili.addAll(all_optional_disponibili);

					for(OptionalEscursioneTO optional : all_optional_disponibili){
						for(OptionalEscursioneTO oe : all_optional_scelti){
							if(optional.getOptional().getNome().equals(oe.getOptional().getNome())){
								all_current_optional_disponibili.remove(optional);
							}
						}
					}
					setTables();
				}
			}
		};

		stpSelezionaModificaOptional.visibleProperty().addListener(visibilityListener);
	}

	/**
	 * Metodo che carica gli optional disponibili per quella escursione nella tabella in alto, 
	 * mentre carica gli optional scelti nella tabella in basso, in base agli optional
	 * disponibili
	 */
	@SuppressWarnings("unchecked")
	private void setTables(){

		ObservableList<OptionalModel> dataDisponibili = FXCollections.observableArrayList(getListTabellaOptionalDisponibili(all_current_optional_disponibili));

		this.initColumn(columnOptionalDisponibili, "nome");
		this.initColumn(columnPrezzoDisponibili, "costo");
		this.initColumn(columnTipoOptionalDisponibili, "nomeTipoOptional");

		tableOptionalDisponibili.setItems(dataDisponibili);

		tableOptionalDisponibili.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				tableOptionalDisponibili = (TableView<OptionalModel>) event.getSource();
				optional_disponibili_model = tableOptionalDisponibili.getSelectionModel().getSelectedItem();
				if(optional_disponibili_model != null){
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
						inserisciOptional();
					}
				}
			}
		});

		ObservableList<OptionalModel> dataScelti = FXCollections.observableArrayList(getListTabellaOptionalScelti(all_optional_scelti));

		this.initColumn(columnOptionalInseriti, "nome");
		this.initColumn(columnPrezzoInseriti, "costo");
		this.initColumn(columnTipoOptionalInseriti, "nomeTipoOptional");

		tableOptionalInseriti.setItems(dataScelti);

		tableOptionalInseriti.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				tableOptionalInseriti = (TableView<OptionalModel>) event.getSource();
				optional_scelti_model = tableOptionalInseriti.getSelectionModel().getSelectedItem();
				if(optional_scelti_model != null){
					if(event.getClickCount() == 2  && !event.isConsumed()){
						event.consume();
						rimuoviOptional();
					}
				}
			}
		});

		double prezzoTotale = iscrizione.getEscursione().getCosto();
		double prezzoTotaleOptional = 0;
		for(OptionalEscursioneTO oe : all_optional_scelti){
			prezzoTotale = prezzoTotale + oe.getOptional().getTipoOptional().getCosto();
			prezzoTotaleOptional = prezzoTotaleOptional + oe.getOptional().getTipoOptional().getCosto();
		}
		lblPrezzoTotale.setText(Double.toString(prezzoTotale) + " €");
		lblPrezzoTotaleOptional.setText(Double.toString(prezzoTotaleOptional) + " €");
	}

	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati
	 * nella tabella degli optional disponibili
	 * 
	 * @param lista Optional
	 * @return res: il modello per la tabella
	 */
	private ObservableList<OptionalModel> getListTabellaOptionalDisponibili(Set<OptionalEscursioneTO> param){
		ObservableList<OptionalModel> res = FXCollections.observableArrayList();

		for(OptionalEscursioneTO optional : param){
			optional_scelti_model = new OptionalModel(optional.getOptional());
			res.add(optional_scelti_model);
		}

		return res;
	}

	/**
	 * Metodo che inizializza il modello che servirà per visualizzare i dati
	 * nella tabella degli optional scelti dal partecipante iscritto
	 * 
	 * @param lista Optional
	 * @return res: il modello per la tabella
	 */
	private ObservableList<OptionalModel> getListTabellaOptionalScelti(Set<OptionalEscursioneTO> param){
		ObservableList<OptionalModel> res = FXCollections.observableArrayList();

		for(OptionalEscursioneTO optional : param){
			optional_scelti_model = new OptionalModel(optional.getOptional());
			res.add(optional_scelti_model);
		}

		return res;
	}

	/**
	 * Evento associato all'inserimento di un optional nella lista degli optional
	 * scelti tra quelli disponibili per quella escursione
	 */
	@FXML protected void inserisciOptional(){
		optional_disponibili_model = tableOptionalDisponibili.getSelectionModel().getSelectedItem();
		if(optional_disponibili_model != null){
			for(OptionalEscursioneTO o : all_current_optional_disponibili){
				if(o.getOptional().getNome().equals(optional_disponibili_model.getNome())){
					TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
					optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
					optional_escursione = o;
					break;
				}
			}
			all_current_optional_disponibili.remove(optional_escursione);
			all_optional_scelti.add(optional_escursione);
			setTables();
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Optional Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}

	/**
	 * Evento associato alla rimozione di un optional dalla lista degli optional
	 * scelti tra quelli disponibili per quella escursione
	 */
	@FXML protected void rimuoviOptional(){
		optional_scelti_model = tableOptionalInseriti.getSelectionModel().getSelectedItem();
		if(optional_scelti_model != null){
			for(OptionalEscursioneTO oe : all_optional_scelti){
				if(oe.getOptional().getNome().equals(optional_scelti_model.getNome())){
					TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
					optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
					optional_escursione = oe;
					break;
				}
			}
			all_optional_scelti.remove(optional_escursione);
			all_current_optional_disponibili.add(optional_escursione);
			setTables();
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Optional Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}

	/**
	 * Evento associato alla conferma gli optional scelti. 
	 * Torna alla view precedente
	 */
	@FXML protected void btnConferma(){
		iscrizione.setOptionals(all_optional_scelti);
		sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE_PARTECIPANTE));
	}

	/**
	 * Evento associato all'annullamento delle
	 * modifiche. Torna alla view precedente
	 */
	@FXML protected void btnIndietro(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "Sei sicuro di voler annullare le modifiche?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			sendRequest(new Request(iscrizioneOld, ViewCache.getNestedAnchorPane(), VIEW_MODIFICA_ISCRIZIONE_ESCURSIONE_PARTECIPANTE));
		} else {
			alert.close();
		}
	}
}
