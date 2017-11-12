package outdoorapp.presentation.views.partecipante;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe Controller che gestisce l'iscrizione del partecipante
 * all'escursione precedentemente scelta.
 * Il Partecipante può scegliere gli optional, e verrà
 * calcolato il costo risultante in base agli optional scelti.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerIscrizioneEscursione extends GenericController{

	@FXML private StackPane stpIscrizioneEscursione;
	@FXML private Label lblNomeEscursione;
	@FXML private Label lblCosto;
	@FXML private Label lblData;
	@FXML private Label lblNMax;
	@FXML private Label lblNMin;
	@FXML private Label lblCostoTotale; 
	@FXML private Label lblTipoEscursione;
	@FXML private Label lblOptionalScelti;
	@FXML private Button btnSelezionaOptional;
	@FXML private Button btnConfermaIscrizione;
	@FXML private Button btnBack;
	private IscrizioneTO iscrizione = null;
	private EscursioneTO escursione = null;
	private PartecipanteTO partecipante = null;
	
	/**
	 * Costruttore
	 */
	public ControllerIscrizioneEscursione() {
		if(iscrizione == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) toFactory.getGenericTO(GenericEnum.Iscrizione);
		}
		if(escursione == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) toFactory.getGenericTO(GenericEnum.Escursione);
		}
		if(partecipante == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) toFactory.getUtenteTO(UtenteEnum.Partecipante);
		}
	}
	
	/**
	 * Metodo che inizializza la schermata dell'iscrizione all'escursione
	 */
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					iscrizione = (IscrizioneTO) SessionCache.getCurrentData(iscrizione.getClass().getSimpleName());
					partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
					if(iscrizione == null){
						escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
						TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
						iscrizione = (IscrizioneTO) toFactory.getGenericTO(GenericEnum.Iscrizione);
						iscrizione.setEscursione(escursione);
					}
					lblNomeEscursione.setText(iscrizione.getEscursione().getNome());
					lblTipoEscursione.setText(iscrizione.getEscursione().getTipoEscursione().getNome());
					lblData.setText(iscrizione.getEscursione().getData());
					lblNMin.setText(Integer.toString(iscrizione.getEscursione().getNumberMin()));
					lblNMax.setText(Integer.toString(iscrizione.getEscursione().getNumberMax()));
					lblCosto.setText(Double.toString(iscrizione.getEscursione().getCosto()) + " €");
					String string = "";
					double costoTotale = iscrizione.getEscursione().getCosto();
					Set<OptionalEscursioneTO> optionals = new HashSet<>();
					optionals = (Set<OptionalEscursioneTO>) iscrizione.getOptionals();
					if(optionals == null)
						string = "Nessun Optional Scelto";
					else{
						for(OptionalEscursioneTO optional : optionals){
							string += optional.getOptional().getNome() + " | ";
							costoTotale += optional.getOptional().getTipoOptional().getCosto();
						}
					}
					lblOptionalScelti.setText(string);
					lblCostoTotale.setText(Double.toString(costoTotale) + " €");
					iscrizione.setEscursione(escursione);
					iscrizione.setUtente(partecipante);
				}
			}
		};

		stpIscrizioneEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Evento associato alla gestione degli optional per 
	 * quella escursione, di un determinato partecipante
	 */
	@FXML protected void btnSelezOptClicked(){
		this.sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_SELEZIONA_OPTIONAL_ISCRIZIONE_PARTECIPANTE));
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Conferma Iscrizione
	 */
	@FXML
	protected void btnIscrivitiClicked(){
		Response response;
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Vuoi confermare le modifiche per questa iscrizione?");
		Optional<ButtonType> result = alertConfirm.showAndWait();
		if (result.get() == ButtonType.OK){
		    response = this.sendRequest(new Request(iscrizione, OUTDOORSPORT_CREATE_OPTIONAL_FROM_ISCRIZIONE));
		    if(response.toString().equals(RESP_OK)){
		    	iscrizione = (IscrizioneTO) response.getData();
		    	this.sendRequest(new Request(iscrizione.getEscursione(), OUTDOORSPORT_UPDATE_ESCURSIONE));
		    	this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_LE_MIE_ESCURSIONI));
		    }else{
		    	Alert alert = new Alert(AlertType.ERROR, "Errore! Qualcosa è andata storta durante l'iscrizione!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
		    }
		} else {
			alertConfirm.close();
		}
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Indietro
	 */
	@FXML
	protected void btnBackClicked(){
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Attenzione! Perderai tutte le modifiche non confermate");
		Optional<ButtonType> result = alertConfirm.showAndWait();
		if (result.get() == ButtonType.OK){
			this.sendRequest(new Request(escursione, ViewCache.getNestedAnchorPane(), VIEW_VISUALIZZA_ESCURSIONI_APERTE));
		} else {
			alertConfirm.close();
		}	
	}

}
