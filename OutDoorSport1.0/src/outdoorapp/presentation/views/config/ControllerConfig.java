package outdoorapp.presentation.views.config;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

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

	private ManagerDiSistemaTO mds = null;
	private TOFactory TOFact = null;

	/**
	 * Costruttore della classe ControllerConfig 
	 */
	public ControllerConfig() {
		if(mds == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			mds = (ManagerDiSistemaTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiSistema);
		}
	}

	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@Override
	protected void initialize() {
		lblErrore.setText("");
		final ToggleGroup group = new ToggleGroup();
		radioM.setToggleGroup(group);
		radioF.setToggleGroup(group);
		group.selectToggle(radioM);
		
		txtCF.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (txtCF.getText().length() > 16) {
	                String s = txtCF.getText().substring(0, 16);
	                txtCF.setText(s);
	            }
	        }
	    });
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
	protected void checkDatePicker(UtenteTO utente) {
		if(dateDataNasc.getValue() == null)
			utente.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				utente.setDataNascita(dateDataNasc.getValue().toString());
		}
	}
}


