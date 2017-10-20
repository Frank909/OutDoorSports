package outdoorapp.business;

import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;

public class ServiceBusinessDelegate extends ServiceFactory{

	private static BusinessDelegate businessDelegate = BusinessDelegate.getInstance();
	private static ServiceBusinessDelegate serviceBusinessDelegate = new ServiceBusinessDelegate();
	
	private ServiceBusinessDelegate(){}
	
	public static ServiceBusinessDelegate getInstance(){
		return serviceBusinessDelegate;
	}

	@Override
	public Response sendRequest(Request req, Service service){
		if(service instanceof ServiceApplicationController){
			if(getFlag())
				return businessDelegate.lookup(req, this);
			return null;
		}
		else 
			return null;
	}

}
