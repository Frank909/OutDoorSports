package outdoorapp.presentation.views.partecipante;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Forms;
import outdoorapp.utils.Views;

/**
 * Gestisce la finestra per la registrazione del partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerRegistrazionePartecipante implements Actions, Views{

	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtCF;
	@FXML private TextField txtNTSanitaria;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCitta;
	@FXML private RadioButton radioM;
	@FXML private RadioButton radioF;
	@FXML private TextField txtEmail;
	@FXML private DatePicker dateDataNasc;
	@FXML private Label lblSrcCertificatoSRC;
	@FXML private Button btnCaricaCertificatoSRC;
	@FXML private DatePicker dataCertificatoSRC;
	@FXML private Button btnRegistrati;
	@FXML private Label lblErrore;
	
	/**
	 * Costruttore della classe ControllerLogin
	 */
	public ControllerRegistrazionePartecipante() {}
	
	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML public void initialize() {
        lblErrore.setText("");
        ///DA COMPLETARE CON TUTTI I CAMPI///
    }
	
	/**
	 * Evento associato al caricamento del certificato src nel sistema. Viene caricato
	 * il file in un percorso specifico.
	 */
	@FXML protected void caricaCertificatoSRC(){
		execCaricaCertificatoSRC();
	}
	
	/**
	 * Metodo di supporto a caricaCertificatoSRC(). Viene recuperata la richiesta di
	 * caricamento del certificato e viene mandata ai livelli successivi
	 */
	private void execCaricaCertificatoSRC() {
		Partecipante partecipante = new Partecipante();
		Request request = new Request(partecipante, OUTDOORSPORT_SAVE_SRC_CERTIFICATE);
		FrontController fc = new FrontController();
		Response response = fc.sendRequest(request);
		
		if(response.getResponse().equals(RESP_OK))
			lblSrcCertificatoSRC.setText(((Partecipante)response.getData()).getCertificatoSrc());
	}
	
	/**
	 * Evento associato all'invio dei dati della resistrazione di un Partecipante
	 */
	@FXML protected void registraPartecipante(){
		execRegistraPartecipante();
	}

	/**
	 * Metodo di supporto a registraPartecipante(). Viene recuperata la richiesta di
	 * registrazione dei partecipanti e viene mandata ai livelli successivi
	 */
	private void execRegistraPartecipante() {
		///////////////////////////////////////////
		Partecipante partecipante = new Partecipante();
		partecipante.setNome(txtNome.getText());
		partecipante.setCognome(txtCognome.getText());
		partecipante.setCodiceFiscale(txtCF.getText());
		partecipante.setUsername(txtUsername.getText());
		partecipante.setPassword(txtPassword.getText());
		partecipante.setIndirizzo(txtIndirizzo.getText());
		partecipante.setCitta(txtCitta.getText());
		partecipante.setEmail(txtEmail.getText());
		if(radioM.isSelected())
			partecipante.setSesso(MALE);
		else if(radioF.isSelected())
			partecipante.setSesso(FEMALE);
		////////////////////////////////////////////
		partecipante.setCertificatoSrc(lblSrcCertificatoSRC.getText());
		partecipante.setTesseraSanitaria(txtNTSanitaria.getText());
		
		
		String result = checkErrors(partecipante);
		if(result.equals("")){
			FrontController fc = new FrontController();
			Response response = fc.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_PARTECIPANTE));
			if(response.getResponse().equals(RESP_OK))
				System.out.println("Partecipante inserito correttamente");
			else
				lblErrore.setText("Errore! Email o Username già presenti!");
		}else
			lblErrore.setText(result);
	}
	
	/**
	 * Funzione che restituisce la stringa degli errori rispetto alle informazioni inserite in maniera non corretta 
	 * per registrare il manager di sistema nella configurazione iniziale
	 * 
	 * @param utente: manager di sistema
	 * @return result: stringa errori
	 */
	private String checkErrors(Partecipante utente){
		String result = "";

		if(dateDataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dateDataNasc.getValue().toString());
		}
		
		if(dataCertificatoSRC.getValue() == null)
			utente.setDataCertificatoSrc("");
		else{
			if(!(dataCertificatoSRC.getValue().getYear() > LocalDate.now().getYear() - 0))
				utente.setDataCertificatoSrc(dataCertificatoSRC.getValue().toString());
		}

		int i = 0;
		for (Field f : utente.getClass().getSuperclass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if ((f.get(utente) == null || f.get(utente).equals(""))) {
					if(!(f.getName().equals("ruolo") || f.getName().equals("statoUtente"))){
						if(!f.getName().equals("email")){
							result += f.getName() + ", ";
							i++;
							if(i == 2){
								result += "\n";
								i = 0;
							}
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		i = 0;
		for (Field f : utente.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(utente) == null || f.get(utente).equals("")) {
					result += f.getName() + ", ";
					i++;
					if(i == 2){
						result += "\n";
						i = 0;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(utente.getEmail());

		if(!matcher.matches()){
			result += "email";
		}
		
		if(!result.equals(""))
			result += " non corretti!";

		return result;
	}
}
