package outdoorapp;

import javafx.application.Application;
import javafx.stage.Stage;
import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.ViewCache;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.utils.Actions;

public class Main extends Application implements Actions{
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		ManagerDiSistema mds = new ManagerDiSistema();
		FrontController fc = FrontController.getInstance();
		Request request = new Request(mds, OUTDOORSPORT_MDS_EXISTS_AT_LEAST_ONE);
		Response response = fc.sendRequest(request);
		ViewCache views = ViewCache.getInstance();
		views.initialize();
		views.setView(response.getView());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
