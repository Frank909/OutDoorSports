package outdoorapp.presentation.views.application;

import java.text.Normalizer.Form;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Forms;
import outdoorapp.utils.Views;

/**
 * Gestisce la finestra per il recupero della password
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerPasswordDimenticata implements Actions, Views{

	@FXML private Button btnRichiediNuovaPassword;
	@FXML private TextField txtEmail;
	@FXML private Label lblErrore;
	
	
	/**
	 * Costruttore della classe ControllerPasswordDimenticata
	 */
	public ControllerPasswordDimenticata() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
		lblErrore.setText("");
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	/**
	 * Evento associato alla richiesta di una nuova password. Viene inviata all'utente
	 * una nuova password tramite email.
	 */
	@FXML protected void execRichiediNuovaPassword(){
		execRequestNewPassword();
	}

	/**
	 * Metodo di supporto a execRichiediNuovaPassword(). Viene catturata l'email e viene
	 * effettuata la richiesta di invio di una nuova password.
	 */
	private void execRequestNewPassword() {
		Utente utente = new Utente();
		utente.setEmail(txtEmail.getText());
		FrontController fc = new FrontController();
		Request request = new Request(utente, OUTDOORSPORT_REQUEST_NEW_PASSWORD);
		Response response = fc.sendRequest(request);
		if(response.getResponse().equals(RESP_OK)){
			Alert alert = new Alert(AlertType.INFORMATION, "La nuova password è stata inviata correttamente!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK){
				Forms.closeForm(VIEW_PASSWORD_DIMENTICATA);
			}
				
		}else{
			lblErrore.setText("L'email inserita non esiste!");
		}
			
	}

}
