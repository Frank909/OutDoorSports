package outdoorapp.presentation.views.application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
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

public class ControllerLogin extends GenericController{

	@FXML private Button btnLogin;
	@FXML private Button btnSignIn;
	@FXML private Button btnForgotPassword;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	/**
	 * Costruttore della classe ControllerLogin
	 */
	private UtenteTO utente = null;
	private TOFactory TOFact = null;
	
	public ControllerLogin() {
		if(utente == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			utente = (UtenteTO) TOFact.getUtenteTO(UtenteEnum.Utente); 
		}
		
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {		
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
		
		if(response.toString().equals(RESP_OK))
			this.sendRequest(new Request(response.getData(), response.getView()));
		else{
			Alert alert = new Alert(AlertType.ERROR, "Errore! Credenziali non Riconosciute!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
			
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
