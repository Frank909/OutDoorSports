package outdoorapp.business.applicationservice;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
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
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
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
		
		;
		try {
			List<EscursioneTO> list_escursioni = escursione_dao.readEscursioniByManagerDiEscursione((ManagerDiEscursioneTO)SessionCache.getCurrentData("ManagerDiEscursione"));
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce una risposta in base a una richiesta, e inserisce 
	 * una nuova Escursione nel sistema
	 * 
	 * @param request
	 * @return una risposta in base alla richiesta
	 */
	public Response nuovaEscursione(Request request){
		Response response = new Response();
		
		try {
			if(!escursione_dao.esisteEscursione((EscursioneTO)request.getData())){
				
				StatoEscursione_DAO statoEscursioneDao = (StatoEscursione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Escursione);

				escursione = (EscursioneTO)request.getData();
				escursione.setStatoEscursione(statoEscursioneDao.getStatoEscursioneAperta());
				ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) SessionCache.getCurrentData("ManagerDiEscursione");
				escursione.setIdMde(mde.getIdManagerDiEscursione());
				escursione.setUtente(mde);
				escursione_dao.create(escursione);
				Alert alert = new Alert(AlertType.INFORMATION, "L'Escursione è stata inserita correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
				response.setResponse(RESP_OK);
			}else{
				response.setResponse(RESP_KO);
			}
			response.setData(null);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce una risposta in base a una richiesta, e modifica
	 * una escursione presente nel sistema
	 * 
	 * @param request
	 * @return una risposta in base alla richiesta
	 */
	public Response modificaEscursione(Request request){
		Response response = new Response();
		escursione = (EscursioneTO) request.getData();
		EscursioneTO temp = null;
		int id_temp = -1;
		
		try {
			temp = escursione_dao.readById(escursione.getIdEscursione().intValue());
			if(temp.getIdEscursione() != null)
				id_temp = temp.getIdEscursione().intValue();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		try {
			if(!(escursione_dao.esisteEscursione(escursione) || temp == null) || (escursione_dao.esisteEscursione(escursione) && escursione.getIdEscursione() == id_temp)){
				
				StatoEscursione_DAO statoEscursioneDao = (StatoEscursione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Escursione);

				escursione = (EscursioneTO)request.getData();
				escursione.setStatoEscursione(statoEscursioneDao.getStatoEscursioneAperta());
				ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) SessionCache.getCurrentData("ManagerDiEscursione");
				escursione.setIdMde(mde.getIdManagerDiEscursione());
				escursione.setUtente(mde);
				escursione_dao.update(escursione);
				Alert alert = new Alert(AlertType.INFORMATION, "L'Escursione è stata modificata correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
				response.setResponse(RESP_OK);
				
				//IMPLEMENTARE L'INVIO DELLA MAIL//
			}else{
				response.setResponse(RESP_KO);
			}
			response.setData(null);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
}
