package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.utils.Actions;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un TipoEscursione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un TipoEscursione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceTipoEscursione implements Actions{

	private DAOFactory tipoFactory = null;
	
	private TipoEscursione_DAO tipo_escursione_dao = null;
	
	public ApplicationServiceTipoEscursione() {
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		tipo_escursione_dao =  (TipoEscursione_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Escursione);
	}
	
	/**
	 * Metodo che restituisce tutti i tipi di una escursione
	 * 
	 * @param request: richiesta da inviare
	 * @return response: la risposta in base alla richiesta
	 */
	public Response getAllTipiEscursione(Request request){
		Response response = new Response();
		List<TipoEscursioneTO> list_tipi_escursione = null;
		try {
			list_tipi_escursione = tipo_escursione_dao.getAll();
			response.setData(list_tipi_escursione);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
			e.printStackTrace();
		}
		return response;
	}
}
