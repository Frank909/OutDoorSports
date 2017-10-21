package outdoorapp.business.applicationservice;

//import outdoorapp.business.ServiceBusinessDelegate;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;
import outdoorapp.services.ServiceType;

public class ServiceBusinessLookUp extends AbstractService{

	private static BusinessLookUp businessLookUp = BusinessLookUp.getInstance();
	private static ServiceBusinessLookUp serviceBusinessLookUp = new ServiceBusinessLookUp();

	private ServiceBusinessLookUp(){}

	public static ServiceBusinessLookUp getInstance(){
		return serviceBusinessLookUp;
	}

	@Override
	public Response sendRequest(Request req, ServiceType serviceType){
		if(serviceType.equals(ServiceType.BusinessDelegate)){
			if(getFlag()){
				setFlag(false);
				return businessLookUp.lookup(req, this);
			}
		}
		return null;
	}

}
