package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;

/**
 * Gestisce l'inserimento di una nuova Escursione da
 * parte del Manager di Escursione. Vengono aggiunti anche 
 * eventuali Optional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerInserimentoEscursione extends ControllerEscursione{

	@FXML private StackPane stpInserimentoEscursione;
	@FXML private TextField txtNomeEscursione;
	@FXML private TextField txtMin;
	@FXML private TextField txtMax;
	@FXML private TextField txtCostoBase;
	@FXML private DatePicker dataEscursione;
	@FXML private TextArea txtDescrizione;
	@FXML private ChoiceBox<String> chbSelezionaOptional;
	@FXML private ChoiceBox<String> chbTipoEscursione;
	@FXML private ListView<String> listAreaOptionalScelti;
	@FXML private Button btnRimuoviOptional;
	@FXML private Button btnInviaDati;
	@FXML private Label lblErrore;
	
	private EscursioneTO escursione = null;
	private TipoEscursioneTO tipo_escursione = null;
	private List<TipoEscursioneTO> list_tipo_escursione = new ArrayList<>();
	private OptionalTO optional = null;
	private List<OptionalTO> list_optional = new ArrayList<>();
	private ObservableList<String> data = null;
	private ArrayList<String> strings = null;
	
	/**
	 * Costruttore della classe ControllerInserimentoEscursione
	 */
	public ControllerInserimentoEscursione() {
		if(escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
		if(tipo_escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
			tipo_escursione = (TipoEscursioneTO) TOFact.getTipoTO(TipoEnum.TipoEscursione);
		}
		if(optional == null){
			TOFactory TOfact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
			optional = (OptionalTO) TOfact.getOptionalTO(OptionalEnum.Optional); 
		}
	}

	@Override
	protected void initialize() {
		lblErrore.setText("");
		
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@SuppressWarnings("unchecked")
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					if(list_tipo_escursione.isEmpty()){
						Response response = sendRequest(new Request(tipo_escursione, OUTDOORSPORT_GET_ALL_TIPI_ESCURSIONE));
						list_tipo_escursione = (List<TipoEscursioneTO>) response.getData();
						strings = new ArrayList<>();
						for(TipoEscursioneTO tipoescursione: list_tipo_escursione){
							strings.add(tipoescursione.getNome());
						}
						data = FXCollections.observableArrayList(strings);
						chbTipoEscursione.setItems(data);
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
				}
			}
		};
		
		stpInserimentoEscursione.visibleProperty().addListener(visibilityListener);
		
		chbSelezionaOptional.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String choice = chbSelezionaOptional.getItems().get(newValue.intValue());
				if(!listAreaOptionalScelti.getItems().contains(choice)){
					listAreaOptionalScelti.getItems().add(listAreaOptionalScelti.getItems().size(), choice);
					listAreaOptionalScelti.scrollTo(choice);
					listAreaOptionalScelti.edit(listAreaOptionalScelti.getItems().size() - 1);
				}
			}
		});
		
		txtCostoBase.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtCostoBase.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		txtMin.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtMin.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		txtMax.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtMax.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	/**
	 * Evento associato alla rimozione di un optional dalla lista, prima
	 * del salvataggio della escursione
	 */
	@FXML protected void rimuoviSelezione(){
		String choice = listAreaOptionalScelti.getSelectionModel().getSelectedItem();
		if(choice != null)
			listAreaOptionalScelti.getItems().remove(choice);
		else{
			Alert alert = new Alert(AlertType.ERROR, "Errore! Nessun optional scelto!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}
	
	@Override
	protected void registra() {
		execInserisciEscursione();
	}
	
	/**
	 * Metodo che implementa la logica di inserimento di una nuova
	 * escursione. Vengono effettuati tutti i controlli prima di 
	 * mandare i dati al database
	 */
	private void execInserisciEscursione() {
		try{
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
			for(int i=0; i<listAreaOptionalScelti.getItems().size(); i++){
				if(listAreaOptionalScelti.getItems().get(i).equals(op.getNome()))
					temp.add(op);
			}
		}
		escursione.setOptionals(temp);
		
		String result = checkErrors(escursione);
		if(result.equals("")){
			Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_SAVE_ESCURSIONE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
			else
				lblErrore.setText("Errore! L'Escursione è già presente nel Sistema!");
		}else
			lblErrore.setText(result);
		}catch (Exception e){
			e.printStackTrace();
		}
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
