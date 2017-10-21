package outdoorapp.business;



import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;
import outdoorapp.services.AbstractService;

public class ServiceBusinessDelegate extends AbstractService{

	private static BusinessDelegate businessDelegate = BusinessDelegate.getInstance();
	private static ServiceBusinessDelegate serviceBusinessDelegate = new ServiceBusinessDelegate();
	
	private ServiceBusinessDelegate(){}
	
	public static ServiceBusinessDelegate getInstance(){
		return serviceBusinessDelegate;
	}

	@Override
	public Response sendRequest(Request req, ServiceType serviceType){
		if(serviceType.equals(ServiceType.ApplicationController)){
			if(getFlag())
				return businessDelegate.lookup(req, ServiceType.BusinessDelegate);
			return null;
		}
		else 
			return null;
	}

}
