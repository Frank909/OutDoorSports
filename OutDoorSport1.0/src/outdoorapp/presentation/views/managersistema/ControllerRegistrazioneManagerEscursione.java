package outdoorapp.presentation.views.managersistema;

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Gestisce la registrazione di un nuovo Manager di Escursione da parte del Manager
 * di Sistema. Il Manager di Sistema inserisce tutti i dati e, se non è già presente
 * all'interno del sistema, il Manager di Escursione viene creato correttamene
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerRegistrazioneManagerEscursione extends ControllerRegistrazione{

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
	@FXML private DatePicker dateDataNasc;
	@FXML private Button btnRegistraManagerDiEscursione;
	@FXML private Label lblErrore;
	
	private ManagerDiEscursioneTO mde = null;
	/**
	 * Costruttore della classe ControllerRegistrazioneManagerEscursione
	 */
	public ControllerRegistrazioneManagerEscursione() {
		if(mde == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			mde = (ManagerDiEscursioneTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiEscursione);
		}
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		lblErrore.setText("");
		
		final ToggleGroup group = new ToggleGroup();
		radioM.setToggleGroup(group);
		radioF.setToggleGroup(group);
		
		txtStipendio.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            txtStipendio.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		txtCF.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (txtCF.getText().length() > 16) {
	                String s = txtCF.getText().substring(0, 16);
	                txtCF.setText(s);
	            }
	        }
	    });
		group.selectToggle(radioM);
    }
	
	/**
	 * Evento associato all'invio dei dati della registrazione di un Partecipante
	 */
	@Override
	protected void registra() {
		execRegistraManagerDiEscursione();
	}
	
	private void execRegistraManagerDiEscursione() {
		
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
			Response response = this.sendRequest(new Request(mde, OUTDOORSPORT_SAVE_MDE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_GESTIONE_MANAGER_ESCURSIONE));
			else
				lblErrore.setText("Errore! Email o Username già presenti!");
		}else
			lblErrore.setText(result);
	}
	
	@Override
	public void checkDatePicker(UtenteTO utente) {
		if(dateDataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dateDataNasc.getValue().toString());
		}
	}
}
