package outdoorapp.presentation.views.partecipante;

import java.util.HashSet;
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
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe Controller che gestisce la modifica di una iscrizione 
 * del partecipante all'escursione precedentemente scelta.
 * Il Partecipante può scegliere gli optional, e verrà
 * calcolato il costo risultante in base agli optional scelti.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerModificaIscrizioneEscursione extends GenericController{

	@FXML private StackPane stpModificaIscrizioneEscursione;
	@FXML private Label lblNomeEscursione;
	@FXML private Label lblCostoEscursione;
	@FXML private Label lblDataEscursione;
	@FXML private Label lblNMaxEscursione;
	@FXML private Label lblNMinEscursione;
	@FXML private Label lblCostoTotale; 
	@FXML private Label lblTipoEscursione;
	@FXML private Label lblOptionalScelti;
	@FXML private Button btnSelezionaOptional;
	@FXML private Button btnConfermaModifiche;
	@FXML private Button btnIndietro;
	private IscrizioneTO iscrizione = null;
	private EscursioneTO escursione = null;
	private PartecipanteTO partecipante = null;
	
	/**
	 * Costruttore
	 */
	public ControllerModificaIscrizioneEscursione() {
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

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					iscrizione = (IscrizioneTO) SessionCache.getCurrentData(iscrizione.getClass().getSimpleName());
					if(iscrizione == null){
						TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
						iscrizione = (IscrizioneTO) toFactory.getGenericTO(GenericEnum.Iscrizione);
						escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
						partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
						iscrizione.setEscursione(escursione);
						iscrizione.setUtente(partecipante);
						Response response = sendRequest(new Request(iscrizione, OUTDOORSPORT_GET_ISCRIZIONE_FROM_ESCURSIONE));
						if(response.toString().equals(RESP_OK)){
							iscrizione = (IscrizioneTO) response.getData();
						}
					}
					lblNomeEscursione.setText(iscrizione.getEscursione().getNome());
					lblTipoEscursione.setText(iscrizione.getEscursione().getTipoEscursione().getNome());
					lblDataEscursione.setText(iscrizione.getEscursione().getData());
					lblNMinEscursione.setText(Integer.toString(iscrizione.getEscursione().getNumberMin()));
					lblNMaxEscursione.setText(Integer.toString(iscrizione.getEscursione().getNumberMax()));
					lblCostoEscursione.setText(Double.toString(iscrizione.getEscursione().getCosto()) + " €");
					String string = "";
					double costoTotale = iscrizione.getEscursione().getCosto();
					Set<OptionalEscursioneTO> optionals = new HashSet<>();
					optionals = (Set<OptionalEscursioneTO>) iscrizione.getOptionals();
					if(optionals.isEmpty())
						string = "Nessun Optional Scelto";
					else{
						for(OptionalEscursioneTO optional : optionals){
							string += optional.getOptional().getNome() + " | ";
							costoTotale += optional.getOptional().getTipoOptional().getCosto();
						}
					}
					lblOptionalScelti.setText(string);
					lblCostoTotale.setText(Double.toString(costoTotale) + " €");
				}
			}
		};

		stpModificaIscrizioneEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Evento associato alla gestione degli optional per 
	 * quella escursione, di un determinato partecipante
	 */
	@FXML protected void btnSelezOptClicked(){
		this.sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_SELEZIONA_MODIFICA_OPTIONAL_ISCRIZIONE_PARTECIPANTE));
	}

	/**
	 * Metodo associato all'evento del click del bottone Conferma Modifica Iscrizione
	 */
	@FXML
	protected void btnModificaClicked(){
		Response response;
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Vuoi confermare le modifiche per questa iscrizione?");
		Optional<ButtonType> result = alertConfirm.showAndWait();
		if (result.get() == ButtonType.OK){
		    response = this.sendRequest(new Request(iscrizione, OUTDOORSPORT_UPDATE_OPTIONAL_FROM_ISCRIZIONE));
		    if(response.toString().equals(RESP_OK)){
		    	iscrizione = (IscrizioneTO) response.getData();
		    	this.sendRequest(new Request(iscrizione.getEscursione(), OUTDOORSPORT_UPDATE_ESCURSIONE));
		    	this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_LE_MIE_ESCURSIONI));
		    }else{
		    	Alert alert = new Alert(AlertType.ERROR, "Errore! Qualocosa è andato storto durante l'Iscrizione!", ButtonType.OK);
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
			this.sendRequest(new Request(escursione, ViewCache.getNestedAnchorPane(), VIEW_LE_MIE_ESCURSIONI));
		} else {
			alertConfirm.close();
		}	
	}
}
