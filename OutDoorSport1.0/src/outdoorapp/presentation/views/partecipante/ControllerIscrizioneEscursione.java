package outdoorapp.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;

/**
 * Classe Controller che gestisce l'iscrizione del partecipante
 * all'escursione precedentemente scelta.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerIscrizioneEscursione extends GenericController{

	@FXML private Label lblNomeEscursione,
		lblCosto, lblData, lblNMax, lblNMin,
		lblCostoTotale, lblTipoEscursione;
	
	@FXML private Button btnSelezionaOptional,
		btnConfermaIscrizione, btnBack;
	
	/**
	 * Costruttore
	 */
	public ControllerIscrizioneEscursione() {
	}
	
	/**
	 * Metodo che inizializza la schermata dell'iscrizione all'escursione
	 */
	@Override
	protected void initialize() {
		
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Seleziona Optional
	 */
	@FXML
	protected void btnSelezOptClicked(){
		
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Conferma Iscrizione
	 */
	@FXML
	protected void btnIscrivitiClicked(){
		
	}
	
	/**
	 * Metodo associato all'evento del click del bottone Indietro
	 */
	@FXML
	protected void btnBackClicked(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_VISUALIZZA_ESCURSIONI_APERTE));
	}

}
