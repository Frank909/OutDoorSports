package outdoorapp.business.applicationservice;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.SessionCache;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di Iscrizioni.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * una Iscrizione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceIscrizione implements Views, Actions{

	private DAOFactory genericFactory = null;
	
	private Iscrizione_DAO iscrizione_dao = null;
	private IscrizioneTO iscrizione = null;
	
	public ApplicationServiceIscrizione() {
		genericFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		iscrizione_dao = (Iscrizione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Iscrizione);
	}
	
	/**
	 * Metodo che restituisce tutte le iscrizioni
	 * ad una determinata escursione.
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllIscrittiFromEscursione(Request request){
		Response response = new Response();
		EscursioneTO escursione = (EscursioneTO)SessionCache.getCurrentData("Escursione");
		
		try {
			List<IscrizioneTO> list_iscrizioni = iscrizione_dao.getAllIscrittiFromEscursione((EscursioneTO)SessionCache.getCurrentData("Escursione"));
			response.setData(list_iscrizioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}

}
