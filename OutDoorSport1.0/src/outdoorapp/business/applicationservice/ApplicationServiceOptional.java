package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.interfaces.Optional_DAO;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.utils.Actions;

/**
 * Classe che modella e implementa l'Application Service per la gestione degli Optional.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Optional, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceOptional implements Actions{

	private DAOFactory optionalFactory = null;
	
	private Optional_DAO optional_dao = null;
	
	public ApplicationServiceOptional() {
		optionalFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		optional_dao =  (Optional_DAO) optionalFactory.getGenericDAO(GenericDAOEnum.Optional);
	}

	/**
	 * Metodo che restituisce tutti gli optional
	 * 
	 * @param request: richiesta da inviare
	 * @return response: la risposta in base alla richiesta
	 */
	public Response getAllOptionals(Request request){
		Response response = new Response();
		List<OptionalTO> list_optional = null;
		try {
			list_optional = optional_dao.getAll();
			response.setData(list_optional);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
			e.printStackTrace();
		}
		return response;
	}
}
