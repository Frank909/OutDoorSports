package outdoorapp.presentation.views.config;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.Utente;


/**
 * Gestisce la view per la configurazione iniziale alla prima
 * apertura dell'applicazione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerConfig extends ControllerRegistrazione{

	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtCF;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCitta;
	@FXML private TextField txtEmail;
	@FXML private RadioButton radioM;
	@FXML private RadioButton radioF;
	@FXML private TextField txtTelefono;
	@FXML private Button btnRegistrati;
	@FXML private DatePicker dateDataNasc;
	@FXML private Label lblErrore;
	
	/**
	 * Costruttore della classe ControllerConfig 
	 */
	public ControllerConfig() {
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		lblErrore.setText("");
	}
	
	/**
	 * Evento associato all'invio dei dati della configurazione iniziale del manager di sistema
	 */
	@Override
	protected void registra() {
		execRegistraManagerDiSistema();
	}
	
	/**
	 * Metodo che esegue la registrazione del manager di sistema controllando che tutte le informazioni siano state inserite
	 * correttamente
	 */
	private void execRegistraManagerDiSistema(){

		ManagerDiSistema mds = new ManagerDiSistema();
		mds.setNome(txtNome.getText());
		mds.setCognome(txtCognome.getText());
		mds.setCodiceFiscale(txtCF.getText());
		mds.setUsername(txtUsername.getText());
		mds.setPassword(txtPassword.getText());
		mds.setIndirizzo(txtIndirizzo.getText());
		mds.setCitta(txtCitta.getText());
		mds.setEmail(txtEmail.getText());
		if(radioM.isSelected())
			mds.setSesso(MALE);
		else if(radioF.isSelected())
			mds.setSesso(FEMALE);
		mds.setTelefono(txtTelefono.getText());

		
		String result = checkErrors(mds);
		if(result.equals("")){
			Response res = this.sendRequest(new Request(mds, OUTDOORSPORT_SAVE_MDS));
			if(res.toString().equals(RESP_OK))
				this.sendRequest(new Request(VIEW_MANAGER_DI_SISTEMA_CONFIG));
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


