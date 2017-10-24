package outdoorapp.presentation.views.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericViewController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Gestisce la view per la schermata di login
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerLogin extends GenericViewController{

	@FXML private Button btnLogin;
	@FXML private Button btnSignIn;
	@FXML private Button btnForgotPassword;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblErrore;
	/**
	 * Costruttore della classe ControllerLogin
	 */
	private UtenteTO utente = null;
	
	public ControllerLogin() {
		if(utente == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			utente = (UtenteTO) TOFact.getUtenteTO(UtenteEnum.Utente);
		}
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		lblErrore.setText("");
		
	}
	
	/**
	 * Evento associato al login. Vengono recuperati i dati dell'utente
	 * e viene aperta la dashboard relativa
	 */
	@FXML protected void eseguiLogin(){
		execLogin();
	}
	
	/**
	 * Metodo di supporto a eseguiLogin(). Vengono catturati username e password e viene
	 * effettuata la richiesta di login nei livelli sottostanti
	 */
	private void execLogin(){
		utente.setUsername(txtUsername.getText());
		utente.setPassword(txtPassword.getText());
		Request req = new Request(utente, OUTDOORSPORT_EXECUTE_LOGIN);
		Response response = this.sendRequest(req);
		
		if(response.toString().equals(RESP_OK)){
			this.sendRequest(new Request(response.getView()));
		}
		else
			lblErrore.setText("Dati non riconosciuti");
	}
	
	/**
	 * Metodo che visualizza a video la finestra per la richiesta di una nuova password
	 */
	@FXML protected void richiediNuovaPassword(){
		this.sendRequest(new Request(VIEW_PASSWORD_DIMENTICATA));
	}
	
	/**
	 * Metodo che visualizza a video la finestra della registrazione del partecipante
	 */
	@FXML protected void eseguiSignIn(){
		this.sendRequest(new Request(VIEW_REGISTRAZIONE_PARTECIPANTE));
	}
}
