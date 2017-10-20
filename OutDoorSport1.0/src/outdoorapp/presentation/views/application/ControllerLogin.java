package outdoorapp.presentation.views.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.business.applicationservice.ServiceBusinessLookUp;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.ViewCache;
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
	private FrontController fc;
	private ViewCache vc;
	/**
	 * Costruttore della classe ControllerLogin
	 */
	public ControllerLogin() {
		fc = FrontController.getInstance();
		vc = ViewCache.getInstance();
	}
	
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
	 * Metodo di supporto a eseguiLogin(). Vengono catturati username e password e viene
	 * effettuata la richiesta di login nei livelli sottostanti
	 */
	private void execLogin(){
		Utente utente = new Utente();
		utente.setUsername(txtUsername.getText());
		utente.setPassword(txtPassword.getText());
		Request request = new Request(utente, OUTDOORSPORT_EXECUTE_LOGIN);
		ServiceBusinessLookUp s = ServiceBusinessLookUp.getInstance();
		
		Response r = s.sendRequest(request, ServiceBusinessDelegate.getInstance());
		
		Response response = fc.sendRequest(request);
		if(response.getResponse().equals(RESP_OK))
			vc.setView(response.getView());
		else
			lblErrore.setText(response.getView());
		
		
	}
	
	/**
	 * Metodo che visualizza a video la finestra per la richiesta di una nuova password
	 */
	@FXML protected void richiediNuovaPassword(){
		//da fare con il frontcontroller
		vc.setView(VIEW_PASSWORD_DIMENTICATA);
	}
	
	/**
	 * Metodo che visualizza a video la finestra della registrazione del partecipante
	 */
	@FXML protected void eseguiSignIn(){
		//da fare con il frontcontroller
		vc.setView(VIEW_REGISTRAZIONE_PARTECIPANTE);
	}

}
