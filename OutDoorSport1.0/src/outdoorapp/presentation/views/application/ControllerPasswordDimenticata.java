package outdoorapp.presentation.views.application;

import javafx.application.Platform;
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
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Gestisce la finestra per il recupero della password
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerPasswordDimenticata extends GenericController{

	@FXML private Button btnRichiediNuovaPassword;
	@FXML private TextField txtEmail;
	@FXML private Label lblErrore;
	@FXML private Button btnIndietro;
	
	private UtenteTO utente = null;
	private TOFactory TOFact = null;
	
	/**
	 * Costruttore della classe ControllerPasswordDimenticata
	 */
	public ControllerPasswordDimenticata() {
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
		lblErrore.setText("");
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
		utente.setEmail(txtEmail.getText());
		
		Response response = this.sendRequest(new Request(utente, OUTDOORSPORT_REQUEST_NEW_PASSWORD));
		if(response.toString().equals(RESP_OK)){
			Alert alert = new Alert(AlertType.INFORMATION, "La nuova password è stata inviata correttamente!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				this.sendRequest(new Request(VIEW_LOGIN));
			
				
		}else{
			lblErrore.setText("L'email inserita non esiste!");
		}		
	}
	
	@FXML protected void btnIndietro(){
		sendRequest(new Request(VIEW_LOGIN));
	}
}
