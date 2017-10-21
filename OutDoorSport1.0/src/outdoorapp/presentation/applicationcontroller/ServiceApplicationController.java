package outdoorapp.presentation.applicationcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;
import outdoorapp.services.AbstractService;

public class ServiceApplicationController extends AbstractService{

	private static ApplicationController applicationController = ApplicationController.getInstance();
	private static ServiceApplicationController serviceApplicationController = new ServiceApplicationController();
	
	private ServiceApplicationController(){
	}
	
	public static ServiceApplicationController getInstance(){
		return serviceApplicationController;
	}
	
	@Override
	public Response sendRequest(Request req, ServiceType serviceType) {
		if(serviceType.equals(ServiceType.FrontController)){
			setFlag(true);
			return applicationController.getAction(req, ServiceType.ApplicationController);
		}
		else 	
			return null;
	}

}
