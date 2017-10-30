package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.utils.Actions;

class ApplicationServiceTipoEscursione implements Actions{

	private DAOFactory tipoFactory = null;
	
	private TipoEscursione_DAO tipo_escursione_dao = null;
	private TipoEscursioneTO tipo_escursione = null;
	private TOFactory TOFact = null;
	
	public ApplicationServiceTipoEscursione() {
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		tipo_escursione_dao =  (TipoEscursione_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Escursione);
	}
	
	/**
	 * Metodo che restituisce tutti i tipi
	 * di escursione presenti nel sistema
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllTipiEscursione(Request request){
		Response response = new Response();
		
		try {
			List<TipoEscursioneTO> list_tipo_escursioni = tipo_escursione_dao.getAllTipiEscursione();
			response.setData(list_tipo_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}

}
