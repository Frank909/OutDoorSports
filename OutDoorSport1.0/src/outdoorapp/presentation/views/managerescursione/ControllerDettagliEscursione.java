package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;
import java.util.Optional;

import org.omg.DynamicAny.DynAnySeqHelper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import java.time.temporal.ChronoUnit;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Visualizza i dettagli di una determinata Escursione. Il Manager
 * di Escursione può visualizzare tutti i Partecipanti iscritti
 * all'escursione, modificare l'escursione o annullarla
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerDettagliEscursione extends GenericController{

	@FXML private StackPane stpDettagliEscursione;
	@FXML private Label lblNomeEscursione;
	@FXML private Label lblStatoEscursione;
	@FXML private Label lblTipoEscursione;
	@FXML private Label lblDataEscursione;
	@FXML private Label lblNumMin;
	@FXML private Label lblNumMax;
	@FXML private Label lblCostoEscursione;
	@FXML private Label lblOptionalEscursione;
	@FXML private Label lblDescrizioneEscursione;
	@FXML private Button btnModificaEscursione;
	@FXML private Button btnAnnullaEscursione;
	@FXML private Button btnVisualizzaIscritti;
	@FXML private Button btnIndietro;
	private EscursioneModel escursione = new EscursioneModel();
	
	public ControllerDettagliEscursione() {
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneModel) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					lblNomeEscursione.setText(escursione.getNome());
					lblStatoEscursione.setText("Stato: " + escursione.getStatoEscursione().getNome());
					lblTipoEscursione.setText("Tipo: " + escursione.getTipoEscursione().getNome());
					lblDataEscursione.setText("Data: " + escursione.getData());
					lblNumMin.setText("Minimo " + escursione.getNumberMin() + " Partecipanti");
					lblNumMax.setText("Massimo " + escursione.getNumberMax() + " Partecipanti");
					lblCostoEscursione.setText("Costo: " + escursione.getCosto());
					String optionals = "";
					for(OptionalTO op : escursione.getEscursione().getOptionals()){
						optionals += optionals + op.getNome() + "\n";
					}
					lblOptionalEscursione.setText("Optional: " + optionals);
					lblDescrizioneEscursione.setText("Descrizione: " + escursione.getDescrizione());
				}
			}
		};
		
		stpDettagliEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Evento associato alla view. Torna alla schermata precedente
	 */
	@FXML protected void visualizzaEscursioniSistema(){
		this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
	}
	
	/**
	 * Evento associato alla view. Visualizza gli iscritti dell'escursione
	 */
	@FXML protected void visualizzaIscritti(){
		this.sendRequest(new Request(escursione.getEscursione(), SessionCache.getNestedAnchorPane(), VIEW_ISCRITTI_ESCURSIONE));
	}
	
	/**
	 * Evento associato alla modifica di una escursione
	 */
	@FXML protected void modificaEscursione(){
		if(escursione.getStatoEscursione().getNome().equals("APERTA"))
			this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_MODIFICA_ESCURSIONE));
		else if(escursione.getStatoEscursione().getNome().equals("IN CORSO")){
			Alert alert = new Alert(AlertType.ERROR, "Non è possibile modificare una escursione in corso!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}else if(escursione.getStatoEscursione().getNome().equals("CHIUSA")){
			Alert alert = new Alert(AlertType.ERROR, "Non è possibile modificare una escursione chiusa!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}else if(escursione.getStatoEscursione().getNome().equals("ANNULLATA")){
			Alert alert = new Alert(AlertType.ERROR, "Non è possibile modificare una escursione annullata!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}		
	}
	
	/**
	 * Evento associato all'annullamento di una escursione
	 */
	@FXML protected void annullaEscursione(){
		LocalDate escursione_date = LocalDate.parse(escursione.getData());
		LocalDate date_now = LocalDate.now();
		if(escursione.getStatoEscursione().getNome().equals("APERTA") && (ChronoUnit.DAYS.between(date_now, escursione_date) > 2)){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Sei Sicuro di voler annullare questa escursione?");
			alert.setTitle("OutDoorSport1.0");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Response response = sendRequest(new Request(escursione.getEscursione(), OUTDOORSPORTS_ANNULLA_ESCURSIONE));
				Alert alert1 = new Alert(AlertType.INFORMATION, "Escursione annullata con successo!", ButtonType.OK);
				alert1.setTitle("OutDoorSport1.0");
				alert1.showAndWait();
				this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_GESTIONE_ESCURSIONI));
			} else
				alert.close();
		}else if(!escursione.getStatoEscursione().getNome().equals("APERTA")){
			Alert alert = new Alert(AlertType.ERROR, "Non è possibile annullare l'escursione", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(AlertType.ERROR, "Non è possibile annullare una escursione a meno di due giorni prima del suo inizio!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
	}	
}
