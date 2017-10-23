package outdoorapp.business.applicationservice;



//import outdoorapp.business.ServiceBusinessDelegate;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio/supporto al businessLookUp realizzato allo scopo di nascondere dettagli
 * implementativi fungendo da tunneling fra BusinessDelegate e BusinessLookUp
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceBusinessLookUp extends Service{
	
	private BusinessLookUp businessLookUp = BusinessLookUp.getInstance();
	
	public ServiceBusinessLookUp(){
	}

	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta al businessLookUp e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */

	@Override
	public ServiceType getType() {
		return ServiceType.BusinessLookUp;
	}

	@Override
	protected Response sendRequest(Request request) {
		return businessLookUp.lookup(request);
	}

}
