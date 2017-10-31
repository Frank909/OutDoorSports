package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerEscursione;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la modifica di una escursione da parte del
 * Manager di Escursione, solo se lo stato dell'escursione
 * è APERTA.
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
	
	private EscursioneTO escursione = null;
	private EscursioneModel escursioneModel = new EscursioneModel();
	private TipoEscursioneTO tipo_escursione = null;
	private List<TipoEscursioneTO> list_tipo_escursione = new ArrayList<>();
	private ObservableList<String> data = null;
	private ArrayList<String> strings = null;
	private TOFactory TOFact = null;
	
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
	}

	@Override
	protected void initialize() {
		lblErrore.setText("");
		
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursioneModel = (EscursioneModel) SessionCache.getCurrentData(escursioneModel.getClass().getSimpleName());
					txtNomeEscursione.setText(escursioneModel.getNome());
					txtMin.setText(Integer.toString(escursioneModel.getNumberMin()));
					txtMax.setText(Integer.toString(escursioneModel.getNumberMax()));
					txtCostoBase.setText(Double.toString(escursioneModel.getCosto()));
					dataEscursione.setValue(LocalDate.parse(escursioneModel.getData()));
					txtDescrizione.setText(escursioneModel.getDescrizione());
					if(SessionCache.getCurrentData("TipiEscursione") != null){
						list_tipo_escursione.addAll((ArrayList<TipoEscursioneTO>) SessionCache.getCurrentData("TipiEscursione"));
						strings = new ArrayList<>();
						for(TipoEscursioneTO tipoescursione: list_tipo_escursione){
							strings.add(tipoescursione.getNome());
						}
						data = FXCollections.observableArrayList(strings);
						chbTipoEscursione.setItems(data);
						chbTipoEscursione.getSelectionModel().select(escursioneModel.getTipoEscursione().getNome());
					}
					
					//COMPLETARE PER GLI OPTIONAL//
				}
			}
		};

		stpModificaEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	@Override
	protected void registra() {
		execModificaEscursione();
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
		escursione.setIdEscursione(escursioneModel.getIdEscursione());
		escursione.setUtente(escursioneModel.getUtente());
		escursione.setStatoEscursione(escursioneModel.getStatoEscursione());
		escursione.setIdMde(escursioneModel.getUtente().getIdUtente());

		//DA COMPLETARE CON L'INSERIMENTO DEGLI OPTIONAL
		
		String result = checkErrors(escursione);
		if(result.equals("")){
			Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_UPDATE_ESCURSIONE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
			else
				lblErrore.setText("Errore! L'Escursione è già presente nel Sistema!");
		}else
			lblErrore.setText(result);
	}
	
	@Override
	public void checkDatePicker(EscursioneTO escursione) {
		if(dataEscursione.getValue() == null)
			escursione.setData("");
		else{
			if(dataEscursione.getValue().getYear() > LocalDate.now().getYear())
				escursione.setData(dataEscursione.getValue().toString());
		}
	}
}
