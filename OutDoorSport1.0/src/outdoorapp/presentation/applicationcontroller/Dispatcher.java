package outdoorapp.presentation.applicationcontroller;

import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.utils.ViewCache;

class Dispatcher {

	private static ViewCache vc = ViewCache.getInstance();
	
	Dispatcher(){
		try {
			vc.initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setView(String request){
		vc.setView(request);
	}
	
	private void setNestedView(String request, AnchorPane pane){
		vc.setNestedView(request, pane);
	}

	Response dispatch(Request request){
		if(request.getData() == null){
			setView(request.toString());
		}else if(request.getData() instanceof AnchorPane){
			setNestedView(request.toString(), (AnchorPane)request.getData());
		}
		return null;
	}
}
