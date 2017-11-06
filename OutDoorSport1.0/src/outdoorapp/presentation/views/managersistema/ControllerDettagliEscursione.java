package outdoorapp.presentation.views.managersistema;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.utils.SessionCache;

/**
 * Visualizza i dettagli di una determinata Escursione. Il Manager
 * di Sistema può visualizzare tutti i Partecipanti iscritti
 * all'escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerDettagliEscursione extends GenericController{

	@FXML private Label lblNomeEscursione;
	@FXML private Label lblStatoEscursione;
	@FXML private Label lblTipoEscursione;
	@FXML private Label lblDataEscursione;
	@FXML private Label lblNumMin;
	@FXML private Label lblNumMax;
	@FXML private Label lblCostoEscursione;
	@FXML private Label lblOptionalEscursione;
	@FXML private Label lblDescrizioneEscursione;
	@FXML private Button btnVisualizzaIscritti;
	@FXML private Label lblMDE;
	@FXML private StackPane stpDettagliEscursione;
	private EscursioneModel escursione = new EscursioneModel();
	
	public ControllerDettagliEscursione() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneModel) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					lblNomeEscursione.setText(escursione.getNome());
					lblStatoEscursione.setText("Stato: " + escursione.getStatoEscursione());
					lblTipoEscursione.setText("Tipo: " + escursione.getTipoEscursione());
					lblDataEscursione.setText("Data: " + escursione.getData());
					lblNumMin.setText("Minimo " + escursione.getNumberMin() + " Partecipanti");
					lblNumMax.setText("Massimo " + escursione.getNumberMax() + " Partecipanti");
					lblCostoEscursione.setText("Costo: " + escursione.getCosto());
					String optionals = "";
					for(OptionalTO op : escursione.getEscursione().getOptionals()){
						optionals += optionals + op.getNome() + "\n";
					}
					lblDescrizioneEscursione.setText("Descrizione: " + escursione.getDescrizione());
					lblMDE.setText("Manager: " + escursione.getUtente().getNome() + " " + escursione.getUtente().getCognome());
				}
			}
		};

		stpDettagliEscursione.visibleProperty().addListener(visibilityListener);
	}

	/**
	 * Evento associato alla view. Torna alla schermata precedente
	 */
	@FXML protected void visualizzaEscursioniSistema(){
		this.sendRequest(new Request(SessionCache.getNestedAnchorPane(), VIEW_VISUALIZZA_ESCURSIONI_SISTEMA));
	}
	
	/**
	 * Evento associato alla view. Visualizza gli iscritti dell'escursione
	 */
	@FXML protected void visualizzaIscritti(){
		
	}
}
