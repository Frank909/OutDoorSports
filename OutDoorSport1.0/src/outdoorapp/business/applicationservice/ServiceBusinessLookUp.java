package outdoorapp.business.applicationservice;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio, invocata dal Service Locator, per collegare il 
 * Business Delegate al BusinessLookup. In questo modo si nascondono 
 * i vari passaggi della richiesta e non possono essere richiamati da altre
 * parti.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceBusinessLookUp extends Service{
	
	private BusinessLookUp businessLookUp = BusinessLookUp.getInstance();
	
	public ServiceBusinessLookUp(){
	}

	@Override
	public ServiceType getType() {
		return ServiceType.BusinessLookUp;
	}

	@Override
	protected Response sendRequest(Request request) {
		return businessLookUp.lookup(request);
	}
}
