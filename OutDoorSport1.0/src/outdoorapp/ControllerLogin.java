package outdoorapp;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.ManagerDiEscursioneDAO;
import outdoorapp.integration.dao.ManagerDiSistemaDAO;
import outdoorapp.integration.dao.PartecipanteDAO;
import outdoorapp.integration.dao.RuoliDAO;
import outdoorapp.integration.dao.StatoUtenteDAO;
import outdoorapp.integration.dao.UtenteDAO;
import outdoorapp.to.Utente;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Ruoli;
import outdoorapp.to.StatoUtente;


public class ControllerLogin
{
	/*@FXML private Button btnLogin;
	@FXML private Button btnRegistrati;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblTitolo;
	@FXML private Label lblPassLost;
	@FXML private Label lblMessage;*/
	
	@FXML private AnchorPane dashboardPartecipante;
	@FXML private StackPane leMieEscursioniPartecipante;
	@FXML private StackPane visualizzaEscursioniApertePartecipante;
	@FXML private StackPane ilMioProfiloPartecipante;
	@FXML private GridPane idContentGridPane;
	@FXML private AnchorPane idContent;
	@FXML private Label lblLeMieEscursioni;
	@FXML private Label lblVisulizzaEscursioniAperte;
	@FXML private Label lblIlMioProfilo;
	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private TableView mTableEscursioni;
	@FXML private TextField username_textview;
	@FXML private PasswordField password_textview;

	private String user;
	private String pass;
	private String checkUser, checkPass;
	
	@FXML protected void txtKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER){
			/*lblMessage.setVisible(true);
			lblMessage.setText("Premere il tasto LOGIN");
			lblMessage.setTextFill(Color.BLUE);
			txtUsername.setText("");
			txtPassword.setText("");*/
		}
	}
	
	@FXML protected void lblLeMieEscursioniOnClick(MouseEvent event) throws IOException{
		loadPanel(leMieEscursioniPartecipante, "leMieEscursioniPartecipante");
	}
	
	@FXML protected void lblVisulizzaEscursioniAperteOnClick(MouseEvent event) throws IOException{
		loadPanel(visualizzaEscursioniApertePartecipante, "leMieEscursioniPartecipante");
	}

	@FXML protected void lblIlMioProfiloOnClick(MouseEvent event)throws IOException{
		loadPanel(ilMioProfiloPartecipante, "ilMioProfiloPartecipante");
	}
	
	@FXML protected void executeLogin() throws DatabaseException{
		
		UtenteDAO<Utente> utente_dao = new UtenteDAO<Utente>();
		Partecipante newPartecipante = new Partecipante();
		
		newPartecipante.setNome("cacca");
		newPartecipante.setCognome("cacca");
		newPartecipante.setNome("cacca");
		newPartecipante.setUsername("cacca");
		newPartecipante.setPassword("cacca");
		newPartecipante.setEmail("cacca");
		newPartecipante.setCodiceFiscale("cacca");
		Date newDate = new Date();
		newPartecipante.setDataNascita(newDate);
		newPartecipante.setSesso("m");
		newPartecipante.setIndirizzo("thvthv");
		newPartecipante.setCitta("thvthv");
		newPartecipante.setCertificatoSrc("gvtht");
		newPartecipante.setDataCertificatoSrc(newDate);
		newPartecipante.setTesseraSanitaria("vthtv");
		
		Ruoli ruolo = new Ruoli();
		ruolo.setIdRuolo(1);
		newPartecipante.setRuolo(ruolo);
		StatoUtente stato = new StatoUtente();
		stato.setIdStatoUtente(1);
		newPartecipante.setStatoUtente(stato);
		
		String utente = "";
		utente = this.username_textview.getText();
		
		Partecipante newUtente = (Partecipante) utente_dao.getByUsername(utente);
		
		try{
			utente_dao.create(newPartecipante);
		}catch (DatabaseException e) {
			// TODO: handle exception
		}
		
		newPartecipante.setCognome("zio");
		newPartecipante.setNome("zio");
		
		utente_dao.update(newPartecipante);
		
		System.out.println(newUtente.getUsername() + newUtente.getRuolo().getNome() + newUtente.getCertificatoSrc());
	}
	
	private void loadPanel(StackPane panel, String panelName) throws IOException{
		panel = (StackPane)FXMLLoader.load(Forms.class.getResource(panelName + ".fxml"));
		idContent.setLeftAnchor(panel, 0.0);
		idContent.setRightAnchor(panel, 0.0);
		idContent.setTopAnchor(panel, 0.0);
		idContent.setBottomAnchor(panel, 0.0);
		idContent.getChildren().clear();
		idContent.getChildren().add(panel);
	}
}