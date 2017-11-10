package outdoorapp.presentation.views.managersistema;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce i dettagli di un partecipante selezionato dalla tabella. 
 * Il Manager di Sistema può visualizzare i dettagli anagrafici del
 * Partecipante.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerDettagliPartecipante extends GenericController{

	@FXML private StackPane stpDettagliPartecipante;
	@FXML private Label mUsernameProfilo;
	@FXML private Label mNomeCognomeProfilo;
	@FXML private Label mCittaProfilo;
	@FXML private Label mIndirizzoProfilo;
	@FXML private Label mCFProfilo;
	@FXML private Label mDataNascitaProfilo;
	@FXML private Label mEmailProfilo;
	@FXML private Label mDataSRCProfilo;
	@FXML private Label mTessSanProfilo;
	@FXML private Button btnIndietro;
	PartecipanteTO partecipante = null;
	
	public ControllerDettagliPartecipante() {
		if(partecipante == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
					mUsernameProfilo.setText(partecipante.getUsername());
					mNomeCognomeProfilo.setText(partecipante.getNome() + " " + partecipante.getCognome());
					mCittaProfilo.setText("Città: " + partecipante.getCitta());
					mIndirizzoProfilo.setText("Indirizzo: " + partecipante.getIndirizzo());
					mCFProfilo.setText("Codice Fiscale: " + partecipante.getCodiceFiscale());
					mDataNascitaProfilo.setText("Data di Nascita: " + partecipante.getDataNascita());
					mEmailProfilo.setText("Email: " + partecipante.getEmail());
					mDataSRCProfilo.setText("Data Certificato: " + partecipante.getDataCertificatoSrc());
					mTessSanProfilo.setText("N° Tess. San.: " + partecipante.getTesseraSanitaria());
				}
			}
		};

		stpDettagliPartecipante.visibleProperty().addListener(visibilityListener);
	}
	
	/**
	 * Metodo associato all'evento che ritorna alla view precedente
	 */
	@FXML protected void indietro(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_ISCRITTI_ESCURSIONE_SISTEMA));
	}

}
