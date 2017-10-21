package outdoorapp.presentation.applicationcontroller;

import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

class Dispatcher {

	private static ViewCache vc = null;
	private static Dispatcher dispatcher = new Dispatcher();;
	
	private Dispatcher(){
	}
	
	public static Dispatcher getInstance(){
		if(vc == null){
			try {
				vc = ViewCache.getInstance();
				vc.initialize();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dispatcher;
	}
	
	private void setView(String request){
		vc.setView(request);
	}
	
	private void setNestedView(String request, AnchorPane pane){
		vc.setNestedView(request, pane);
	}

	Response dispatch(Request request){
		if(request.getView() == null){
			setView(request.toString());
		}else if(request.getView() instanceof AnchorPane){
			setNestedView(request.toString(), (AnchorPane)request.getView());
		}
		return null;
	}
}
