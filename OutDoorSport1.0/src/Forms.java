import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Forms {
	
	static class Form{//rimuovere
		private Scene scene;
		private String id;
		
		public Form(Scene scene, String id){
			this.scene=scene;
			this.id=id;
		}
		
		Scene getScene(){
			return this.scene;
		}
		
		String getId(){
			return this.id;
		}

	}

	
	private static HashMap<String, Scene> Forms = new HashMap<>();
	
//	public static void showForm(String title, String resource){
	public static void showForm(String resource){
		Stage newStage = new Stage();
		newStage.setTitle(resource);
		newStage.setResizable(false);
		AnchorPane newPane=null;
		try {
			newPane = (AnchorPane)FXMLLoader.load(Forms.class.getResource(resource + ".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene myScene = new Scene(newPane);
		newStage.setScene(myScene);
		newStage.show();
		Forms.put(resource, myScene);	
	}

	public static void closeForm(String resource) {
		((Stage)Forms.get(resource).getWindow()).close();
		Forms.remove(resource);
	}
}
