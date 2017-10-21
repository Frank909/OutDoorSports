package outdoorapp.business.applicationservice;

//import outdoorapp.business.ServiceBusinessDelegate;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.services.Service;
import outdoorapp.services.ServiceFactory;
import outdoorapp.services.ServiceType;

/**
 * Classe di servizio/supporto al businessLookUp realizzato allo scopo di nascondere dettagli
 * implementativi fungendo da tunneling fra BusinessDelegate e BusinessLookUp
 * @author Andrea Zito
 * @author Francesco Ventura
 */
public class ServiceBusinessLookUp extends AbstractService{

	private static BusinessLookUp businessLookUp = BusinessLookUp.getInstance();
	private static ServiceBusinessLookUp serviceBusinessLookUp = new ServiceBusinessLookUp();

	/**
	 * Costruttore privato
	 */
	private ServiceBusinessLookUp(){}

	/**
	 * @return restituisce l'istanza del ServiceBusinessLookUp
	 */
	public static ServiceBusinessLookUp getInstance(){
		return serviceBusinessLookUp;
	}

	/**
	 * Override del metodo dell'interfaccia Service ereditata dalla classe astratta AbstractService.
	 * Metodo che manda una richiesta al businessLookUp e restituisce una risposta 
	 * a seconda del tipo di richiesta in ingresso controllando che il servizio arrivi dal livello esatto.
	 */
	@Override
	public Response sendRequest(Request req, ServiceType serviceType){
		if(serviceType.equals(ServiceType.BusinessDelegate)){
			if(isTunneling()){
				setTunneled(false);
				return businessLookUp.lookup(req, this);
			}
		}
		return null;
	}

}
