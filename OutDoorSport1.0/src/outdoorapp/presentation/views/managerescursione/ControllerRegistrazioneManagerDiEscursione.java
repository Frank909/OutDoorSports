package outdoorapp.presentation.views.managerescursione;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.ControllerRegistrazione;
import outdoorapp.to.ManagerDiEscursione;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * 
 * Classe controller che gestisce la view per la registrazione
 * del manager di escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerRegistrazioneManagerDiEscursione extends ControllerRegistrazione implements Actions, Views{

	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtCF;
	@FXML private TextField txtStipendio;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCitta;
	@FXML private TextField txtEmail;
	@FXML private RadioButton radioM;
	@FXML private RadioButton radioF;
	@FXML private Button btnRegistrati;
	@FXML private DatePicker dateDataNasc;
	@FXML private Label lblErrore;
	private FrontController fc;
	
	
	public ControllerRegistrazioneManagerDiEscursione(){
		fc = FrontController.getInstance();
	}
	
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		lblErrore.setText("");
	}
	
	/**
	 * Evento associato all'invio dei dati della registrazione di un manager di escursione
	 */
	@Override
	protected void registra() {
		execRegistraManagerDiEscursione();
	}
	
	/**
	 * Metodo che esegue la registrazione del manager di escursione controllando che tutte le informazioni siano state inserite
	 * correttamente
	 */
	private void execRegistraManagerDiEscursione(){

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
		
		String result = checkErrors(mde);
		if(result.equals("")){
			Response res = fc.sendRequest(new Request(mde, OUTDOORSPORT_SAVE_MDE));
			if(res.getResponse().equals(RESP_OK)){
				System.out.println("Manager di escursione inserito correttamente"); //da rivedere
			}
			else
				lblErrore.setText("Errore! Email o Username già presenti!");
		}else
			lblErrore.setText(result);
	}
	
	/**
	 * Riscrittura del metodo checkDatePicker dell'interfaccia DateFieldCheck che permette di controllare i datepicker delle schermate
	 * di inserimento dati.
	 * @param utente
	 */
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
