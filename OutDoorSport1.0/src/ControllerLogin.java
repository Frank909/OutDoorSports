
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ControllerLogin{

	@FXML private Button btnLogin;
	@FXML private Button btnRegistrati;
	@FXML private AnchorPane AgroludosPane;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblTitolo;
	@FXML private Label lblPassLost;
	@FXML private Label lblMessage;

	private String user;
	private String pass;
	private String checkUser, checkPass;
	
	@FXML protected void txtKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER){
			lblMessage.setVisible(true);
			lblMessage.setText("Premere il tasto LOGIN");
			lblMessage.setTextFill(Color.BLUE);
			txtUsername.setText("");
			txtPassword.setText("");
		}
		
	}

	@FXML protected void login_button(MouseEvent event) {	
		onProva();
	}
	
	private void onProva(){
		System.out.println("ciao");
	}
	
	
	
	









}