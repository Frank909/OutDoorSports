package outdoorapp;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.utils.Forms;
import outdoorapp.utils.KeyMap;

public class Main extends Application implements KeyMap{
	
	@Override
	public void start(Stage primaryStage) {
		ManagerDiSistema mds = new ManagerDiSistema();
		FrontController fc = new FrontController();
		Forms.showForm(fc.sendRequest(new Request(mds, OUTDOORSPORT_MDS_EXISTS_AT_LEAST_ONE)).getView());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
