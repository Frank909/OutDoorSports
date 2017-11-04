package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.interfaces.OptionalEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.utils.Actions;

/**
 * Classe che modella e implementa l'Application Service per la gestione degli Optional
 * di una determinata Escursione. L'obiettivo è quello di raccogliere tutte le azioni 
 * che è possibile effettuare per un Optional di una Escursione, andando a ridurre 
 * l'accoppiamento con le altre componenti del sistema. L'application service utilizza 
 * i Transfer Object e i Data Access Object per occuparsi della persistenza di tali 
 * oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceOptionalEscursione implements Actions {

	private DAOFactory genericDAO = null;
	
	private OptionalEscursione_DAO optional_escursione_dao = null;
	
	public ApplicationServiceOptionalEscursione() {
		genericDAO = FactoryProducerDAO.getFactory(DAORequest.Generic);
		optional_escursione_dao = (OptionalEscursione_DAO) genericDAO.getGenericDAO(GenericDAOEnum.OptionalEscursione);
	}

	/**
	 * Metodo che restituisce tutti gli Optional di una determinata Escursione
	 * 
	 * @param richiesta 
	 * @return risposta in base alla richiesta
	 */
	public Response getAllOptionalsFromEscursione(Request request){
		Response response = new Response();
		OptionalEscursioneTO optional_escursione = (OptionalEscursioneTO) request.getData();
		
		try {
			List<OptionalEscursioneTO> optional_escursione_list = optional_escursione_dao.getOptionalsFromEscursione(optional_escursione.getEscursione().getIdEscursione());
			response.setData(optional_escursione_list);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
			e.printStackTrace();
		}
		
		return response;
	}
	
	
}
