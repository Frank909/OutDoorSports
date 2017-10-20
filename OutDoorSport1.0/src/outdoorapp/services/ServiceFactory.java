package outdoorapp.services;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

public abstract class ServiceFactory implements Service{
	
	private static boolean flag = false;
	
	public abstract Response sendRequest(Request req, Service service);
	
	protected boolean getFlag(){
		return flag;
	}
	
	protected void setFlag(boolean flag){
		ServiceFactory.flag = flag;
	}
	
	
}
