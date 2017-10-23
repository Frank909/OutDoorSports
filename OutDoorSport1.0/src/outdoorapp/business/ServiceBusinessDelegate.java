package outdoorapp.business;


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
public class ServiceBusinessDelegate extends Service{

	private static BusinessDelegate businessDelegate = BusinessDelegate.getInstance();

	public ServiceBusinessDelegate(){
	}
	
	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta al businessDelegate e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */

	@Override
	public ServiceType getType() {
		return ServiceType.BusinessDelegate;
	}

	@Override
	protected Response sendRequest(Request request) {
		return businessDelegate.lookup(request);
	}

}
