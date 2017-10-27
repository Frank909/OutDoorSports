package outdoorapp.business.applicationservice;

import java.util.List;

import javax.swing.Action;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.Actions;
import outdoorapp.utils.SessionCache;

/**
 * Classe che modella e implementa l'Application Service per la gestione di una Escursione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * una Escurisione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceEscursione implements Actions {

	private DAOFactory genericFactory = null, 
			statoFactory = null, tipoFactory = null;
	
	private Escursione_DAO escursione_dao = null;
	private EscursioneTO escursione = null;
	private TOFactory TOFact = null;
	
	public ApplicationServiceEscursione() {
		genericFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		escursione_dao =  (Escursione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Escursione);
	}
	
	/**
	 * Metodo che restituisce tutte le Escursioni 
	 * presenti nel sistema
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllEscursioni(Request request){
		Response response = new Response();
		
		try {
			List<EscursioneTO> list_escursioni = escursione_dao.getAll();
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce tutte le escursioni di un determinato 
	 * Manager di Escursione
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllEscursioniFromMDE(Request request){
		Response response = new Response();
		
		SessionCache.getCurrentData("ManagerDiEscursione");
		
		return null;
	}
}
