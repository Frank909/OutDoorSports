package outdoorapp.presentation.views.partecipante;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Gestisce la finestra per la registrazione del partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ControllerRegistrazionePartecipante extends ControllerRegistrazione{

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


	private PartecipanteTO partecipante = null;

	/**
	 * Costruttore della classe ControllerLogin
	 */

	public ControllerRegistrazionePartecipante() {
		if(partecipante == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
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
	 * Evento associato all'invio dei dati della registrazione di un Partecipante
	 */
	@Override
	protected void registra() {
		execRegistraPartecipante();
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
		Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_SRC_CERTIFICATE));

		if(response.toString().equals(RESP_OK))
			lblSrcCertificatoSRC.setText(((PartecipanteTO)response.getData()).getCertificatoSrc());
	}


	/**
	 * Metodo di supporto a registra(). Viene recuperata la richiesta di
	 * registrazione dei partecipanti e viene mandata ai livelli successivi
	 */
	private void execRegistraPartecipante() {

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
		partecipante.setCertificatoSrc(lblSrcCertificatoSRC.getText());
		partecipante.setTesseraSanitaria(txtNTSanitaria.getText());


		String result = checkErrors(partecipante);
		if(result.equals("")){
			Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_PARTECIPANTE));
			if(response.toString().equals(RESP_OK))
				this.sendRequest(new Request(VIEW_LOGIN));
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
	public void checkDatePicker(UtenteTO utente) {
		if(dateDataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dateDataNasc.getValue().toString());
		}

		if(dataCertificatoSRC.getValue() == null)
			((PartecipanteTO)utente).setDataCertificatoSrc("");
		else{
			if((dataCertificatoSRC.getValue().getYear() >= LocalDate.now().getYear() - 1))
				((PartecipanteTO)utente).setDataCertificatoSrc(dataCertificatoSRC.getValue().toString());
		}
	}
}
