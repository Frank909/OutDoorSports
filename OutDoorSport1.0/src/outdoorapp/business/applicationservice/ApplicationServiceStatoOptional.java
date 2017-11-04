package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.interfaces.StatoOptional_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di uno StatoOptional.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * uno StatoOptional, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceStatoOptional implements Views, Actions {

	private DAOFactory statoFactory = null;
	private StatoOptional_DAO stato_optional_dao = null;
	
	public ApplicationServiceStatoOptional() {
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		stato_optional_dao = (StatoOptional_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Optional);
	}
	
	/**
	 * Restisuisce tutti gli stati possibili per gli Optional
	 * 
	 * @param richiesta
	 * @return risposta in base alla richiesta
	 */
	public Response getAllStatoOptional(Request request){
		Response response = new Response();
		List<StatoOptionalTO> list_stati_optional = null;
		
		try {
			list_stati_optional = stato_optional_dao.getAll();
			response.setData(list_stati_optional);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
			e.printStackTrace();
		}
		
		return response;
	}

}
