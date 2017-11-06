package outdoorapp.presentation.views.managersistema;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional.TxType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.converter.LocalDateStringConverter;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.ManagerDiEscursioneModel;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.SessionCache;

/**
 * Gestisce la modifica di un manager di escursione da parte del Manager di Sistema selezionato
 * dalla tabella. Il Manager di Sistema può modificare alcuni dati sensibili e inviarli al 
 * database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerModificaManagerEscursione extends ControllerRegistrazione{

	@FXML private StackPane stpModificaManagerEscursione;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtCF;
	@FXML private TextField txtStipendio;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCitta;
	@FXML private RadioButton radioM;
	@FXML private RadioButton radioF;
	@FXML private TextField txtEmail;
	@FXML private DatePicker dataNasc;
	@FXML private Button btnModificaMDE;
	@FXML private Label lblErrore;

	private ManagerDiEscursioneModel mde_model = null;

	public ControllerModificaManagerEscursione() {}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					mde_model = (ManagerDiEscursioneModel) SessionCache.getCurrentData(mde_model.getClass().getSimpleName());
					txtNome.setText(mde_model.getNome());
					txtCognome.setText(mde_model.getCognome());
					txtCF.setText(mde_model.getCodice_fiscale());
					txtUsername.setText(mde_model.getUsername());
					txtPassword.setText(mde_model.getPassword());
					txtStipendio.setText(Double.toString(mde_model.getStipendio()));
					txtEmail.setText(mde_model.getEmail());
					if(mde_model.getSesso().equals("M")){
						radioM.setSelected(true);
						radioF.setSelected(false);
					}else{
						radioM.setSelected(false);
						radioF.setSelected(true);
					}
					txtIndirizzo.setText(mde_model.getIndirizzo());
					txtCitta.setText(mde_model.getCitta());
					dataNasc.setValue(LocalDate.parse(mde_model.getData_nascita()));
				}
			}
		};

		stpModificaManagerEscursione.visibleProperty().addListener(visibilityListener);
		lblErrore.setText("");
	}

	/**
	 * Metodo che invia la richiesta ai livelli più bassi per effettuare la modifica, chiedendo conferma, 
	 * di un Manager di Escursione. Se la risposta è positiva, la modifica viene confermata,
	 * altrimenti un messaggio di errore avvisa il Manager di Sistema che non sono 
	 * state apportate modifiche. 
	 */

	private void execModificaManagerDiEscursione() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("OutDoorSports 1.0");
		alert.setHeaderText("Modifica informazioni Manager di Escursione");
		alert.setContentText("Confermare le modifiche?");
		Optional<ButtonType> res = alert.showAndWait();
		
		if(res.get() == ButtonType.OK){

			TOFactory to_fact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) to_fact.getUtenteTO(UtenteEnum.ManagerDiEscursione);

			lblErrore.setText("");

			mde.setIdManagerDiEscursione(mde_model.getId());
			mde.setIdUtente(mde_model.getId());
			mde.setNome(txtNome.getText());
			mde.setCognome(txtCognome.getText());
			mde.setCodiceFiscale(txtCF.getText());
			mde.setUsername(txtUsername.getText());
			mde.setPassword(txtPassword.getText());
			mde.setIndirizzo(txtIndirizzo.getText());
			mde.setCitta(txtCitta.getText());
			mde.setEmail(txtEmail.getText());
			if(radioM.isSelected())
				mde.setSesso(MALE);
			else if(radioF.isSelected())
				mde.setSesso(FEMALE);
			mde.setStipendio(Double.parseDouble(txtStipendio.getText()));

			String result = checkErrors(mde);
			if(result.equals("")){
				Response response = this.sendRequest(new Request(mde, OUTDOORSPORT_MODIFY_MDE));
				if(response.toString().equals(RESP_OK))
					this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_GESTIONE_MANAGER_ESCURSIONE));
				else
					lblErrore.setText("Errore! Email o Username già presenti!");
			}else
				lblErrore.setText(result);
		}
	}

	@Override
	protected void registra() {
		execModificaManagerDiEscursione();
	}

	@Override
	public void checkDatePicker(UtenteTO utente) {
		if(dataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dataNasc.getValue().toString());
		}
	}

}
