package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.SessionCache;

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
	ObservableList<String> data = null;
	
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
	}

	@Override
	protected void initialize() {
		lblErrore.setText("");
		
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					if(SessionCache.getCurrentData("TipiEscursione") != null){
						list_tipo_escursione.addAll((ArrayList<TipoEscursioneTO>) SessionCache.getCurrentData("TipiEscursione"));
						ArrayList<String> strings = new ArrayList<>();
						for(TipoEscursioneTO tipoescursione: list_tipo_escursione){
							strings.add(tipoescursione.getNome());
						}
						data = FXCollections.observableArrayList(strings);
						chbTipoEscursione.setItems(data);
					}
				}
			}
		};

		stpInserimentoEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	@Override
	protected void registra() {
		execInserisciEscursione();
	}
	
	private void execInserisciEscursione() {
		escursione.setNome(txtNomeEscursione.getText());
		escursione.setNumberMin(Integer.parseInt(txtMin.getText()));
		escursione.setNumberMax(Integer.parseInt(txtMax.getText()));
		escursione.setCosto(Double.parseDouble(txtCostoBase.getText()));
		escursione.setDescrizione(txtDescrizione.getText());

		//DA COMPLETARE CON L'INSERIMENTO DEGLI OPTIONAL
		
		String result = checkErrors(escursione);
		if(result.equals("")){
			Response response = this.sendRequest(new Request(escursione, OUTDOORSPORT_SAVE_ESCURSIONE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
			else
				lblErrore.setText("Errore! Email o Username già presenti!");
		}else
			lblErrore.setText(result);
	}
	
	@Override
	public void checkDatePicker(EscursioneTO escursione) {
		if(dataEscursione.getValue() == null)
			escursione.setData("");
		else{
			if(!(dataEscursione.getValue().getYear() <= LocalDate.now().getYear()))
				escursione.setData(dataEscursione.getValue().toString());
		}
	}

}
