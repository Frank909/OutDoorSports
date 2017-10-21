package outdoorapp.business;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.ServiceType;
import outdoorapp.services.AbstractService;
/**
 * Classe di servizio/supporto al businessLookUp realizzato allo scopo di nascondere dettagli
 * implementativi fungendo da tunneling fra BusinessDelegate e BusinessLookUp
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceBusinessDelegate extends AbstractService{

	private static BusinessDelegate businessDelegate = BusinessDelegate.getInstance();
	private static ServiceBusinessDelegate serviceBusinessDelegate = new ServiceBusinessDelegate();
	
	/**
	 * Costruttore privato
	 */
	private ServiceBusinessDelegate(){}
	
	/**
	 * 
	 * @return restituisce l'istanza del ServiceBusinessDelegate
	 */
	public static ServiceBusinessDelegate getInstance(){
		return serviceBusinessDelegate;
	}

	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta al businessDelegate e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */
	@Override
	public Response sendRequest(Request req, ServiceType serviceType){
		if(serviceType.equals(ServiceType.ApplicationController)){
			if(isTunneling())
				return businessDelegate.lookup(req, ServiceType.BusinessDelegate);
			return null;
		}
		else 
			return null;
	}

}
