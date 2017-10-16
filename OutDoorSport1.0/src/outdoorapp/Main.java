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
import outdoorapp.utils.Actions;

public class Main extends Application implements Actions{
	
	@Override
	public void start(Stage primaryStage) {
		ManagerDiSistema mds = new ManagerDiSistema();
		FrontController fc = new FrontController();
		Request request = new Request(mds, OUTDOORSPORT_MDS_EXISTS_AT_LEAST_ONE);
		Response response = fc.sendRequest(request);
		Forms.showForm(response.getView());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
