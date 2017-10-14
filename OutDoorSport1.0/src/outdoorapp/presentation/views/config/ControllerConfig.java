package outdoorapp.presentation.views.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.utils.KeyMap;

/**
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerConfig implements KeyMap{

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
	
	/**
	 * Costruttore della classe ControllerConfig che inizializza la label di errore come stringa vuota
	 */
	public ControllerConfig() {
		lblErrore.setText("");
	}
	
	/**
	 * Evento associato all'invio dei dati della configurazione iniziale del manager di sistema
	 */
	@FXML protected void registraManagerDiSistema(){
		execRegistraManagerDiSistema();
	}
	
	/**
	 * Metodo che esegue la registrazione del manager di sistema controllando che tutte le informazioni siano state inserite
	 * correttamente
	 */
	private void execRegistraManagerDiSistema(){

		ManagerDiSistema mds = new ManagerDiSistema();
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
			FrontController fc = new FrontController();
			Response res = fc.sendRequest(new Request(mds, OUTDOORSPORT_SAVE_MDS));
		}else
			lblErrore.setText(result);
	}
	

	/**
	 * Funzione che restituisce la stringa degli errori rispetto alle informazioni inserite in maniera non corretta 
	 * per registrare il manager di sistema nella configurazione iniziale
	 * @param mds: manager di sistema
	 * @return result: stringa errori
	 */
	private String checkErrors(ManagerDiSistema mds){
		String result = "";

		if(dateDataNasc.getValue() == null)
			mds.setDataNascita("");
		else{
			if(!(dateDataNasc.getValue().getYear() >= LocalDate.now().getYear() - 15))
				mds.setDataNascita(dateDataNasc.getValue().toString());
		}

		int i = 0;
		for (Field f : mds.getClass().getSuperclass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if ((f.get(mds) == null || f.get(mds).equals(""))) {
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
		for (Field f : mds.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(mds) == null || f.get(mds).equals("")) {
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
		Matcher matcher = pattern.matcher(mds.getEmail());

		if(!matcher.matches()){
			result += "email";
		}
		
		if(!result.equals(""))
			result += " non corretti!";

		return result;
	}

}


