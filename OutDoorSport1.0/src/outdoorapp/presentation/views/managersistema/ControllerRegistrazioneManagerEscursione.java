package outdoorapp.presentation.views.managersistema;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.ControllerRegistrazione;
import outdoorapp.to.ManagerDiEscursione;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.ViewCache;
import outdoorapp.utils.Views;

/**
 * Gestisce la registrazione di un nuovo Manager di Escursione da parte del Manager
 * di Sistema. Il Manager di Sistema inserisce tutti i dati e, se non è già presente
 * all'interno del sistema, il Manager di Escursione viene creato correttamene
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerRegistrazioneManagerEscursione extends ControllerRegistrazione implements Actions, Views{

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
	private FrontController fc;
	private ViewCache vc;
	
	/**
	 * Costruttore della classe ControllerRegistrazioneManagerEscursione
	 */
	public ControllerRegistrazioneManagerEscursione() {
		fc = FrontController.getInstance();
		vc = ViewCache.getInstance();
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
		lblErrore.setText("");
    }
	
	/**
	 * Evento associato all'invio dei dati della registrazione di un Partecipante
	 */
	@Override
	protected void registra() {
		execRegistraManagerDiEscursione();
	}
	
	private void execRegistraManagerDiEscursione() {
		
		ManagerDiEscursione mde = new ManagerDiEscursione();
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
			Response response = fc.sendRequest(new Request(mde, OUTDOORSPORT_SAVE_MDE));
			if(response.getResponse().equals(RESP_OK))
				vc.setNestedView(VIEW_GESTIONE_MANAGER_ESCURSIONE, ViewCache.getNestedAnchorPane());
			else
				lblErrore.setText("Errore! Email o Username già presenti!");
		}else
			lblErrore.setText(result);
	}
	
	@Override
	public void checkDatePicker(Utente utente) {
		if(dateDataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dateDataNasc.getValue().toString());
		}
	}
}
