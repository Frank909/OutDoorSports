package outdoorapp.business.applicationservice;

import outdoorapp.business.ServiceBusinessDelegate;
import outdoorapp.presentation.applicationcontroller.ServiceApplicationController;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;

public class ServiceBusinessLookUp extends ServiceFactory{

	private static BusinessLookUp businessLookUp = BusinessLookUp.getInstance();
	private static ServiceBusinessLookUp serviceBusinessLookUp = new ServiceBusinessLookUp();
	
	private ServiceBusinessLookUp(){}
	
	public static ServiceBusinessLookUp getInstance(){
		return serviceBusinessLookUp;
	}

	@Override
	public Response sendRequest(Request req, Service service){
		if(service instanceof ServiceBusinessDelegate){
			if(getFlag()){
				setFlag(false);
				return businessLookUp.lookup(req, this);
			}
			return null;
		}
		else 
			return null;
	}

}
