package outdoorapp.presentation.views.application;

import java.text.Normalizer.Form;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Forms;
import outdoorapp.utils.Views;

/**
 * Gestisce la view per la schermata di login
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerLogin implements Actions, Views{

	@FXML private Button btnLogin;
	@FXML private Button btnSignIn;
	@FXML private Button btnForgotPassword;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblErrore;
	
	/**
	 * Costruttore della classe ControllerLogin
	 */
	public ControllerLogin() {}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        lblErrore.setText("");
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	/**
	 * Evento associato al login. Vengono recuperati i dati dell'utente
	 * e viene aperta la dashboard relativa
	 */
	@FXML protected void eseguiLogin(){
		execLogin();
	}
	
	/**
	 * Metodo di supporto a eseguiLogin(). Vengono catturati username e passord e viene
	 * effettuata la richiesta di login nei livelli sottostanti
	 */
	private void execLogin(){
		Utente utente = new Utente();
		utente.setUsername(txtUsername.getText());
		utente.setPassword(txtPassword.getText());
		Request request = new Request(utente, OUTDOORSPORT_EXECUTE_LOGIN);
		FrontController fc = FrontController.getInstance();
		Response response = fc.sendRequest(request);
		if(response.getResponse().equals(RESP_OK)){
			Forms.showForm(response.getView());
			Forms.closeForm(VIEW_LOGIN);
		}
		else
			lblErrore.setText(response.getView());
	}
	
	/**
	 * Metodo che visualizza a video la finestra per la richiesta di una nuova password
	 */
	@FXML protected void richiediNuovaPassword(){
		Forms.showForm(VIEW_PASSWORD_DIMENTICATA);
	}
	
	/**
	 * Metodo che visualizza a video la finestra della registrazione del partecipante
	 */
	@FXML protected void eseguiSignIn(){
		Forms.showForm(VIEW_REGISTRAZIONE_PARTECIPANTE);
	}

}
