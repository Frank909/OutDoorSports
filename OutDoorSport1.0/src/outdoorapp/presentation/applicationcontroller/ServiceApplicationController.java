package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.frontcontroller.FrontController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;

public class ServiceApplicationController extends ServiceFactory{

	private static ApplicationController applicationController = ApplicationController.getInstance();
	private static ServiceApplicationController serviceApplicationController = new ServiceApplicationController();
	
	private ServiceApplicationController(){
	}
	
	@Override
	public Response sendRequest(Request req, Service service) {
		if(service instanceof FrontController){
			setFlag(true);
			return applicationController.getAction(req, this);
		}
		else 	
			return null;
	}
	
	public static ServiceApplicationController getInstance(){
		return serviceApplicationController;
	}

}
