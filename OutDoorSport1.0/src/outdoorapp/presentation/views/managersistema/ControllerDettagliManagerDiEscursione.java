package outdoorapp.presentation.views.managersistema;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

/**
 * Gestisce i dettagli di un manager di escursione da parte del Manager di Sistema selezionato
 * dalla tabella. Il Manager di Sistema può visualizzare i dettagli anagrafici del
 * Manager di Escursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerDettagliManagerDiEscursione extends GenericViewController{

	@FXML private Label mUsernameProfilo;
	@FXML private Label mNomeCognomeProfilo;
	@FXML private Label mIndirizzoProfilo;
	@FXML private Label mStipendioProfilo;
	@FXML private Label mEmailProfilo;
	@FXML private Label mCFProfilo;
	@FXML private Label mCittaProfilo;
	@FXML private Label mDataNascitaProfilo;
	@FXML private Button mBtnIndietro;
	@FXML private StackPane stpDettagliManagerEscursione;
	private ManagerDiEscursioneModel mde = null;
	
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					mde = (ManagerDiEscursioneModel) ViewCache.getCurrentData();
					mUsernameProfilo.setText(mde.getUsername());
					mNomeCognomeProfilo.setText(mde.getNome() + " " + mde.getCognome());
					mIndirizzoProfilo.setText("in: " + mde.getIndirizzo());
					mStipendioProfilo.setText(Double.toString(mde.getStipendio()));
					mEmailProfilo.setText(mde.getEmail());
					mCFProfilo.setText(mde.getCodice_fiscale());
					mDataNascitaProfilo.setText(mde.getData_nascita());
					mCittaProfilo.setText("Residente a: " + mde.getCitta());
				}
			}
		};

		stpDettagliManagerEscursione.visibleProperty().addListener(visibilityListener);
	}
	
	public ControllerDettagliManagerDiEscursione() {}
	
	@FXML protected void gestioneManagerDiEscursione(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_GESTIONE_MANAGER_ESCURSIONE));
	}
}
