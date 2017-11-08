package outdoorapp.presentation.views.managerescursione;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerTableView;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.OptionalModel;
import outdoorapp.presentation.views.models.PartecipanteModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce l'aggiunta o la rimozione di uno o più optional
 * di una determina da una iscrizione di un partecipante.
 * Il Manager di escursione può aggiungere o rimuovere
 * optional scelti dal partecipante in fase di iscrizione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerSelezionaOptionalIscrizione extends ControllerTableView{

	@FXML private StackPane stpSelezionaOptional;
	@FXML private TableView<OptionalModel> tableOptionalInseriti;
	@FXML private TableColumn<OptionalModel, String> mColumnOptionalInseriti;
	@FXML private TableColumn<OptionalModel, String> mColumnPrezzoInseriti;
	@FXML private TableColumn<OptionalModel, String> mColumnTipoOptionalInseriti;
	@FXML private TableView<OptionalModel> tableOptionalDisponibili;
	@FXML private TableColumn<OptionalModel, String> mColumnOptionalDisponibili;
	@FXML private TableColumn<OptionalModel, String> mColumnPrezzoDisponibili;
	@FXML private TableColumn<OptionalModel, String> mColumnTipoOptionalDisponibili;
	@FXML private Button btnInserisciOptional;
	@FXML private Button btnRimuoviOptional;
	@FXML private Label lblPrezzoTotaleOptional;
	@FXML private Label lblPrezzoTotale;
	@FXML private Button btnIndietro;
	@FXML private Button btnConferma;
	private TOFactory TOFact = null;
	private IscrizioneTO iscrizione = null;
	private OptionalModel optional_disponibili_model = null;
	private OptionalModel optional_scelti_model = null;
	private OptionalEscursioneTO optional_escursione = null;
	/*private List<OptionalEscursioneTO> all_optional_scelti = new ArrayList<>();
	private List<OptionalTO> all_optional_disponibili = new ArrayList<>();
	private List<OptionalTO> all_current_optional_disponibili = new ArrayList<>();*/
	private Set<OptionalEscursioneTO> all_optional_scelti = new HashSet<>();
	private Set<OptionalTO> all_optional_disponibili = new HashSet<>();
	private Set<OptionalTO> all_current_optional_disponibili = new HashSet<>();
	
	public ControllerSelezionaOptionalIscrizione() {
		if(iscrizione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) TOFact.getGenericTO(GenericEnum.Iscrizione);
		}
		if(optional_escursione == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
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
					lblPrezzoTotale.setText("0 €");
					lblPrezzoTotaleOptional.setText("0 €");
					
					all_optional_disponibili.addAll(iscrizione.getEscursione().getOptionals());
					all_optional_scelti.addAll(iscrizione.getOptionals());
					all_current_optional_disponibili.addAll(all_optional_disponibili);
					
					for(OptionalTO optional : all_optional_disponibili){
						for(OptionalEscursioneTO oe : all_optional_scelti){
							if(optional.getNome().equals(oe.getOptional().getNome())){
								all_current_optional_disponibili.remove(optional);
							}
						}
					}
					setTables();
				}
			}
		};

		stpSelezionaOptional.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Metodo che carica gli optional disponibili per quella escursione nella tabella in alto, 
	 * mentre carica gli optional scelti nella tabella in basso, in base agli optional
	 * disponibili
	 */
	@SuppressWarnings("unchecked")
	private void setTables(){
		
		ObservableList<OptionalModel> dataDisponibili = FXCollections.observableArrayList(getListTabellaOptionalDisponibili(all_current_optional_disponibili));

		this.initColumn(mColumnOptionalDisponibili, "nome");
		this.initColumn(mColumnPrezzoDisponibili, "costo");
		this.initColumn(mColumnTipoOptionalDisponibili, "nomeTipoOptional");

		tableOptionalDisponibili.setItems(dataDisponibili);
		
		tableOptionalDisponibili.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				tableOptionalDisponibili = (TableView<OptionalModel>) event.getSource();
				optional_disponibili_model = tableOptionalDisponibili.getSelectionModel().getSelectedItem();
				if(optional_disponibili_model != null){
					if(event.getClickCount() == 2){
		                //sendRequest(new Request(partecipante_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
		            }
				}
			}
		});
		
		ObservableList<OptionalModel> dataScelti = FXCollections.observableArrayList(getListTabellaOptionalScelti(all_optional_scelti));
		
		this.initColumn(mColumnOptionalInseriti, "nome");
		this.initColumn(mColumnPrezzoInseriti, "costo");
		this.initColumn(mColumnTipoOptionalInseriti, "nomeTipoOptional");
		
		tableOptionalInseriti.setItems(dataScelti);
		
		tableOptionalInseriti.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				tableOptionalInseriti = (TableView<OptionalModel>) event.getSource();
				optional_scelti_model = tableOptionalInseriti.getSelectionModel().getSelectedItem();
				if(optional_scelti_model != null){
					if(event.getClickCount() == 2){
		                //sendRequest(new Request(partecipante_model, SessionCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
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
	private ObservableList<OptionalModel> getListTabellaOptionalDisponibili(Set<OptionalTO> param){
		ObservableList<OptionalModel> res = FXCollections.observableArrayList();

		for(OptionalTO optional : param){
			optional_scelti_model = new OptionalModel(optional);
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
			for(OptionalTO o : all_current_optional_disponibili){
				if(o.getNome().equals(optional_disponibili_model.getNome())){
					TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
					optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
					optional_escursione.setOptional(o);
					break;
				}
			}
			all_current_optional_disponibili.remove(optional_escursione.getOptional());
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
					TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
					optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
					optional_escursione = oe;
					break;
				}
			}
			all_optional_scelti.remove(optional_escursione);
			all_current_optional_disponibili.add(optional_escursione.getOptional());
			setTables();
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Nessun Optional Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}

}
