package outdoorapp.presentation.views.partecipante;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.ControllerRegistrazione;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.EncryptPasswordTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe controller che gestisce la modifica delle anagrafiche del partecipante.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerModificaPartecipante extends ControllerRegistrazione{

	@FXML private Label lblErrore;
	@FXML private Label lblUsername;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtPassword;
	@FXML private TextField txtNTSanitaria;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCitta;
	@FXML private TextField txtEmail;
	@FXML private Button btnModifica;
	@FXML private StackPane stpModificaDati;

	private PartecipanteTO partecipante = null;
	private EncryptPasswordTO encryptPassword = null;

	/**
	 * Costruttore del controller che avvalora il transfer object del partecipante
	 */
	public ControllerModificaPartecipante() {
		if(partecipante == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		}
		if(encryptPassword == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			encryptPassword = (EncryptPasswordTO) TOFact.getGenericTO(GenericEnum.EncryptPassword);
		}
	}

	/**
	 * Metodo di inizializzazione dell'interfaccia
	 */
	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
					lblUsername.setText(partecipante.getUsername());
					txtNome.setText(partecipante.getNome());
					txtCognome.setText(partecipante.getCognome());
					txtPassword.setText(partecipante.getPassword());
					txtNTSanitaria.setText(partecipante.getTesseraSanitaria());
					txtIndirizzo.setText(partecipante.getIndirizzo());
					txtCitta.setText(partecipante.getCitta());
					txtEmail.setText(partecipante.getEmail());
					lblErrore.setText("");
				}
			}
		};

		stpModificaDati.visibleProperty().addListener(visibilityListener);
	}

	/**
	 * Metodo associato all'evento dell'invio delle modifiche del partecipante.
	 */
	@Override
	protected void registra() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Confermare le modifiche?");
		alert.setTitle("OutDoorSport1.0");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK)
			execModificaPartecipante();
	}

	/**
	 * Metodo privato di supporto al controller per effettuare le modifiche del partecipante
	 */
	private void execModificaPartecipante(){
		partecipante.setNome(txtNome.getText());
		partecipante.setCognome(txtCognome.getText());
		partecipante.setPassword(txtPassword.getText());
		partecipante.setTesseraSanitaria(txtNTSanitaria.getText());
		partecipante.setIndirizzo(txtIndirizzo.getText());
		partecipante.setCitta(txtCitta.getText());
		partecipante.setEmail(txtEmail.getText());

		String result = checkErrors(partecipante);
		if(result.equals("")){
			Response resp = this.sendRequest(new Request(partecipante, OUTDOORSPORT_MODIFY_PARTECIPANTE));

			if(resp.toString().equals(RESP_OK))
				this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_IL_MIO_PROFILO));
			else
				lblErrore.setText("Errore nella modifica del partecipante.");
		}else
			lblErrore.setText(result);
	}

	/**
	 * Metodo associato all'evento del click del bottone Indietro
	 */
	@FXML
	protected void btnBackClicked(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_IL_MIO_PROFILO));
	}

}
