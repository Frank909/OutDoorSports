package outdoorapp.business.applicationservice;

import java.util.ArrayList;
import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di uno StatoEscursione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * uno StatoEscursione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceStatoEscursione implements Actions, Views{

	DAOFactory daoFactory = null;
	StatoEscursione_DAO stato_escursione_dao = null;
	
	public ApplicationServiceStatoEscursione() {
		if(stato_escursione_dao == null){
			daoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
			stato_escursione_dao = (StatoEscursione_DAO) daoFactory.getStatoDAO(StatoDAOEnum.Escursione);
		}
	}
	
	/**
	 * Metodo che restituisce tutti gli stati delle escursioni
	 * 
	 * @param request
	 * @return
	 */
	public Response getAllStatoEscursione (Request request){
		Response response = new Response();
		List<StatoEscursioneTO> stati = new ArrayList<>();
		
		try {
			stati = stato_escursione_dao.getAll();
			response.setData(stati);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
			e.printStackTrace();
		}
		
		return response;
	}

}
