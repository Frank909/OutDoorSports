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


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Forms.showForm("../resources/fxml/application/login");
		//Forms.showForm("login");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}