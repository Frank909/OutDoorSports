package outdoorapp.presentation.views.login;

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
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Utente;
import outdoorapp.utils.Forms;
import outdoorapp.utils.KeyMap;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Ruoli;
import outdoorapp.to.StatoUtente;


public class ControllerLogin implements KeyMap
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
	Request request;
	
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
		Utente utente = new Utente();
		utente.setUsername(this.username_textview.getText());
		utente.setPassword(this.password_textview.getText());
		
		request = new Request(utente, OUTDOORSPORT_AUTENTICATION);
		FrontController fc = new FrontController();
		Response response = fc.sendRequest(request);
		
		Object newUtente = response.getData();
		if(newUtente instanceof Utente){
			Utente utente1 = new Utente();
			utente1 = (Utente) newUtente;
			System.out.println(utente1.getCitta() + utente1.getCodiceFiscale());
			System.out.println("risposta ok");}
		else
			System.out.println("crack");
		/*String utente = "";
		utente = this.username_textview.getText();
		Partecipante newUtente = (Partecipante) utente_dao.getByUsername(utente);	
		String request = newUtente.getRuolo().getNome();
		System.out.println(newUtente.getUsername() + newUtente.getRuolo().getNome() + newUtente.getCertificatoSrc());*/
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