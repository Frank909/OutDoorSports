package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerEscursione;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la modifica di una escursione da parte del
 * Manager di Escursione, solo se lo stato dell'escursione
 * � APERTA.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerModificaEscursione extends ControllerEscursione{

	@FXML private StackPane stpModificaEscursione;
	@FXML private TextField txtNomeEscursione;
	@FXML private TextField txtMin;
	@FXML private TextField txtMax;
	@FXML private TextField txtCostoBase;
	@FXML private DatePicker dataEscursione;
	@FXML private TextArea txtDescrizione;
	@FXML private ChoiceBox<String> chbTipoEscursione;
	@FXML private ChoiceBox<String> chbSelezionaOptional;
	@FXML private ListView<String> listOptionalScelti;
	@FXML private Button btnRimuoviOptional;
	@FXML private Button btnInviaDati;
	@FXML private Label lblErrore;
	@FXML private ListView<String> listOptionalPresenti;
	@FXML private Button btnDisabilita;
	
	private EscursioneTO escursione = null;
	private EscursioneModel escursioneModel = new EscursioneModel();
	private TipoEscursioneTO tipo_escursione = null;
	private OptionalTO optional = null;
	private List<TipoEscursioneTO> list_tipo_escursione = new ArrayList<>();
	private List<OptionalTO> list_optional = new ArrayList<>();
	private ObservableList<String> data = null;
	private ArrayList<String> strings = null;
	private OptionalEscursioneTO optional_escursione = null;
	private List<StatoOptionalTO> list_stati_optional = null;
	private StatoOptionalTO stato_optional = null;
	
	/**
	 * Costruttore della classe ControllerModificaEscursione
	 */
	public ControllerModificaEscursione() {
		if(escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
		if(tipo_escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
			tipo_escursione = (TipoEscursioneTO) TOFact.getTipoTO(TipoEnum.TipoEscursione);
		}
		if(optional_escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
			optional_escursione = (OptionalEscursioneTO) TOFact.getOptionalTO(OptionalEnum.OptionalEscursione);
		}
		if(stato_optional == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
			stato_optional = (StatoOptionalTO) TOFact.getStatoTO(StatoEnum.StatoOptional);
		}
		if(optional == null){
			TOFactory TOfact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
			optional = (OptionalTO) TOfact.getOptionalTO(OptionalEnum.Optional); 
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@SuppressWarnings("unchecked")
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					lblErrore.setText("");
					listOptionalPresenti.getItems().clear();
					listOptionalScelti.getItems().clear();
					
					escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					txtNomeEscursione.setText(escursione.getNome());
					txtMin.setText(Integer.toString(escursione.getNumberMin()));
					txtMax.setText(Integer.toString(escursione.getNumberMax()));
					txtCostoBase.setText(Double.toString(escursione.getCosto()));
					dataEscursione.setValue(LocalDate.parse(escursione.getData()));
					txtDescrizione.setText(escursione.getDescrizione());
					if(list_tipo_escursione.isEmpty()){
						Response response = sendRequest(new Request(tipo_escursione, OUTDOORSPORT_GET_ALL_TIPI_ESCURSIONE));
						list_tipo_escursione = (List<TipoEscursioneTO>) response.getData();
						strings = new ArrayList<>();
						for(TipoEscursioneTO tipoescursione: list_tipo_escursione){
							strings.add(tipoescursione.getNome());
						}
						data = FXCollections.observableArrayList(strings);
						chbTipoEscursione.setItems(data);
						chbTipoEscursione.getSelectionModel().select(escursione.getTipoEscursione().getNome());
					}
					if(list_optional.isEmpty()){
						Response response = sendRequest(new Request(optional, OUTDOORSPORT_GET_ALL_OPTIONALS));
						list_optional = (List<OptionalTO>) response.getData();
						strings = new ArrayList<>();
						for(OptionalTO optional: list_optional){
							strings.add(optional.getNome());
						}
						data = FXCollections.observableArrayList(strings);
						chbSelezionaOptional.setItems(data);
					}
					
					optional_escursione.setEscursione(escursione);
					Response response = sendRequest(new Request(optional_escursione, OUTDOORSPORT_GET_OPTIONAL_FROM_ESCURSIONE));
					List<OptionalEscursioneTO> list_optional_escursione = (List<OptionalEscursioneTO>) response.getData();
					List<String> strings = new ArrayList<>();
					for(OptionalEscursioneTO op : list_optional_escursione)
						strings.add(op.getOptional().getNome() + " | " + op.getStatoOptional().getNome());
					listOptionalPresenti.getItems().addAll(strings);
				}
			}
		};

		stpModificaEscursione.visibleProperty().addListener(visibilityListener);
		
		chbSelezionaOptional.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String choice = chbSelezionaOptional.getItems().get(newValue.intValue());
				if(!listOptionalScelti.getItems().contains(choice)){
					boolean flag = false;
					List<String> strings = listOptionalPresenti.getItems();
					for(String s : strings)
						if(s.contains(choice)){
							flag = true;
							break;
						}
					if(!flag){
						listOptionalScelti.getItems().add(listOptionalScelti.getItems().size(), choice);
						listOptionalScelti.scrollTo(choice);
						listOptionalScelti.edit(listOptionalScelti.getItems().size() - 1);
					}
				}
			}
		});
	}
	
	@Override
	protected void registra() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Confermare le modifiche?");
		alert.setTitle("OutDoorSport1.0");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK)
			execModificaEscursione();
	}
	
	@FXML protected void indietro(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "Attenzione! Perderai tutte le modifiche effettuate! Sei Sicuro?");
		alert.setTitle("OutDoorSport1.0");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    sendRequest(new Request(escursioneModel, ViewCache.getNestedAnchorPane(), VIEW_DETTAGLI_ESCURSIONI_FROM_MDE));
		} else {
		    alert.close();
		}
	}
	
	
	/**
	 * Evento associato alla disabilitazione di un optional dalla lista,
	 * prima della modifica dell'escursione
	 */
	@SuppressWarnings("unchecked")
	@FXML protected void disabilitaOptional(){
		if(list_stati_optional == null){
			Response response = sendRequest(new Request(stato_optional, OUTDOORSPORT_GET_ALL_STATI_OPTIONAL));
			list_stati_optional = (List<StatoOptionalTO>) response.getData();
		}
		String choice = listOptionalPresenti.getSelectionModel().getSelectedItem();
		if(choice != null){
		if(choice.contains(list_stati_optional.get(0).getNome())){
			String newChoice = choice.replace(list_stati_optional.get(0).getNome(), list_stati_optional.get(1).getNome());
			listOptionalPresenti.getItems().add(listOptionalPresenti.getItems().size(), newChoice);
			listOptionalPresenti.scrollTo(choice);
			listOptionalPresenti.edit(listOptionalPresenti.getItems().size() - 1);
			listOptionalPresenti.getItems().remove(choice);
		}else{
			String newChoice = choice.replace(list_stati_optional.get(1).getNome(), list_stati_optional.get(0).getNome());
			listOptionalPresenti.getItems().add(listOptionalPresenti.getItems().size(), newChoice);
			listOptionalPresenti.scrollTo(choice);
			listOptionalPresenti.edit(listOptionalPresenti.getItems().size() - 1);
			listOptionalPresenti.getItems().remove(choice);
		}
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Attenzione! Nessun Optional Selezionato", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	/**
	 * Evento associato alla rimozione di un optional dalla lista, prima
	 * della modifica dell'escursione
	 */
	@FXML protected void rimuoviSelezione(){
		String choice = listOptionalScelti.getSelectionModel().getSelectedItem();
		listOptionalScelti.getItems().remove(choice);
	}
	
	/**
	 * Metodo che implementa la logica di modifica di una escursione
	 * esistente. Vengono effettuati tutti i controlli prima di 
	 * mandare i dati al database
	 */
	private void execModificaEscursione() {
		escursione.setNome(txtNomeEscursione.getText());
		escursione.setNumberMin(Integer.parseInt(txtMin.getText()));
		escursione.setNumberMax(Integer.parseInt(txtMax.getText()));
		escursione.setCosto(Double.parseDouble(txtCostoBase.getText()));
		escursione.setDescrizione(txtDescrizione.getText());
		for(TipoEscursioneTO te : list_tipo_escursione){
			if(te.getNome().equals(chbTipoEscursione.getValue())){
				escursione.setTipoEscursione(te);
				break;
			}
		}

		Set<OptionalTO> temp = new HashSet<>();
		for(OptionalTO op : list_optional){
			for(int i=0; i<listOptionalScelti.getItems().size(); i++){
				if(listOptionalScelti.getItems().get(i).equals(op.getNome()))
					temp.add(op);
			}
		}
		
		for(OptionalTO op : list_optional){
			for(int i=0; i<listOptionalPresenti.getItems().size(); i++){
				if(listOptionalPresenti.getItems().get(i).contains(op.getNome())){
					TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
					OptionalTO newOptional = (OptionalTO) TOFact.getOptionalTO(OptionalEnum.Optional);
					newOptional.setIdOptional(op.getIdOptional());
					newOptional.setNome(listOptionalPresenti.getItems().get(i));
					newOptional.setDescrizione(op.getDescrizione());
					newOptional.setTipoOptional(op.getTipoOptional());
					temp.add(newOptional);
				}
			}
		}
		escursione.setOptionals(temp);
		
		String result = checkErrors(escursione);
		if(result.equals("")){
			Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_UPDATE_ESCURSIONE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
			else
				lblErrore.setText("Errore! L'Escursione � gi� presente nel Sistema!");
		}else
			lblErrore.setText(result);
	}
	
	@Override
	protected void checkDatePicker(EscursioneTO escursione) {
		if(dataEscursione.getValue() == null)
			escursione.setData("");
		else{
			if(dataEscursione.getValue().getYear() > LocalDate.now().getYear())
				escursione.setData(dataEscursione.getValue().toString());
		}
	}
}
