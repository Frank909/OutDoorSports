package outdoorapp.presentation.views.managersistema;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
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
	private EscursioneTO escursione = null;
	
	public ControllerDettagliEscursione() {
		if(escursione == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			escursione = (EscursioneTO) TOFact.getGenericTO(GenericEnum.Escursione);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					escursione = (EscursioneTO) SessionCache.getCurrentData(escursione.getClass().getSimpleName());
					lblNomeEscursione.setText(escursione.getNome());
					lblStatoEscursione.setText("Stato: " + escursione.getStatoEscursione().getNome());
					lblTipoEscursione.setText("Tipo: " + escursione.getTipoEscursione().getNome());
					lblDataEscursione.setText("Data: " + escursione.getData());
					lblNumMin.setText("Minimo " + escursione.getNumberMin() + " Partecipanti");
					lblNumMax.setText("Massimo " + escursione.getNumberMax() + " Partecipanti");
					lblCostoEscursione.setText("Costo: " + escursione.getCosto());
					String optionals = "";
					for(OptionalTO op : escursione.getOptionals()){
						optionals += op.getNome() + "\n";
					}
					lblOptionalEscursione.setText("Optional: " + optionals);
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
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_VISUALIZZA_ESCURSIONI_SISTEMA));
	}
	
	/**
	 * Evento associato alla view. Visualizza gli iscritti dell'escursione
	 */
	@FXML protected void visualizzaIscritti(){
		this.sendRequest(new Request(escursione, ViewCache.getNestedAnchorPane(), VIEW_ISCRITTI_ESCURSIONE_SISTEMA));
	}
}
