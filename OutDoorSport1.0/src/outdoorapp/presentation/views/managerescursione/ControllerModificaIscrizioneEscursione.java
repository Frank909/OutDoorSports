package outdoorapp.presentation.views.managerescursione;

import java.util.ArrayList;
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
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la modifica di una iscrizione per un partecipante.
 * Il Manager di escursione può modificare gli optional scelti,
 * con il calcolo del costo risultante in base agli optional
 * scelti.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerModificaIscrizioneEscursione extends GenericController{

	@FXML private StackPane stpModificaIscrizioneEscursione;
	@FXML private Label lblNomeEscursione;
	@FXML private Label lblTipoEscursione;
	@FXML private Label lblDataEscursione;
	@FXML private Label lblNMaxEscursione;
	@FXML private Label lblNMinEscursione;
	@FXML private Label lblCostoEscursione;
	@FXML private Button btnSelezionaOptional;
	@FXML private Label lblCostoTotale;
	@FXML private Button btnConfermaModifiche;
	@FXML private Button btnIndietro;
	@FXML private Label lblOptionalScelti;
	private IscrizioneTO iscrizione = null;
	private StatoEscursioneTO stato_escursione = null;
	private List<StatoEscursioneTO> list_stato_escursione = new ArrayList<>();

	public ControllerModificaIscrizioneEscursione() {
		if(iscrizione == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			iscrizione = (IscrizioneTO) toFactory.getGenericTO(GenericEnum.Iscrizione);
		}
		if(stato_escursione == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
			stato_escursione = (StatoEscursioneTO) toFactory.getStatoTO(StatoEnum.StatoEscursione);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					iscrizione = (IscrizioneTO) SessionCache.getCurrentData(iscrizione.getClass().getSimpleName());
					lblNomeEscursione.setText(iscrizione.getEscursione().getNome());
					lblTipoEscursione.setText(iscrizione.getEscursione().getTipoEscursione().getNome());
					lblDataEscursione.setText(iscrizione.getEscursione().getData());
					lblNMinEscursione.setText(Integer.toString(iscrizione.getEscursione().getNumberMin()));
					lblNMaxEscursione.setText(Integer.toString(iscrizione.getEscursione().getNumberMax()));
					lblCostoEscursione.setText(Double.toString(iscrizione.getEscursione().getCosto()) + " €");
					String string = "";
					double costoTotale = iscrizione.getEscursione().getCosto();
					Set<OptionalEscursioneTO> optionals = new HashSet();
					optionals = (Set<OptionalEscursioneTO>) iscrizione.getOptionals();
					if(optionals.isEmpty() || optionals == null)
						string = "Nessun Optional Scelto";
					else{
						for(OptionalEscursioneTO optional : optionals){
							if(optional.getStatoOptional().getIdStatoOptional() == 2){
								string += optional.getOptional().getNome() + " | ";
								costoTotale += optional.getOptional().getTipoOptional().getCosto();
							}
						}
					}
					lblOptionalScelti.setText(string);
					lblCostoTotale.setText(costoTotale + " €");
					if(stato_escursione != null){
						Response response = sendRequest(new Request(stato_escursione, OUTDOORSPORT_GET_ALL_STATO_ESCURSIONE));
						if(response.getData() != null){
							list_stato_escursione = (List<StatoEscursioneTO>) response.getData();
						}
					}
				}
			}
		};

		stpModificaIscrizioneEscursione.visibleProperty().addListener(visibilityListener);
	}

	/**
	 * Evento associato alla gestione degli optional per 
	 * quella escursione, di un determinato partecipante
	 */
	@FXML protected void selezionaOptional(){
		if(iscrizione.getEscursione().getStatoEscursione().getNome().equals(list_stato_escursione.get(1).getNome()))
			this.sendRequest(new Request(iscrizione, ViewCache.getNestedAnchorPane(), VIEW_SELEZIONA_OPTIONAL_ISCRIZIONE));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Gli Optionals si possono modificare solo se le Escursioni sono ancora aperte", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}

	/**
	 * Torna alla schermata della visualizzazione degli iscritti all'escursione
	 */
	@FXML protected void indietro(){
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Attenzione! Perderai tutte le modifiche non confermate");
		Optional<ButtonType> result = alertConfirm.showAndWait();
		if (result.get() == ButtonType.OK){
			this.sendRequest(new Request(iscrizione.getEscursione(), ViewCache.getNestedAnchorPane(), VIEW_ISCRITTI_ESCURSIONE));
		} else {
			alertConfirm.close();
		}	
	}

	/**
	 * Conferma le modifiche degli optional scelti, e invia una mail 
	 * all'iscritto interessato
	 */
	@FXML protected void confermaModifiche(){
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION, "Vuoi confermare le modifiche per questa iscrizione?");
		Optional<ButtonType> result = alertConfirm.showAndWait();
		if (result.get() == ButtonType.OK){
			this.sendRequest(new Request(iscrizione, OUTDOORSPORT_UPDATE_OPTIONAL_FROM_ISCRIZIONE));
		} else {
			alertConfirm.close();
		}
	}
}
