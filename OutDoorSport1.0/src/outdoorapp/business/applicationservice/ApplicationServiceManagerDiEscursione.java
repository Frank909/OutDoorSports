package outdoorapp.business.applicationservice;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;


/**
 * Classe che modella e implementa l'Application Service per la gestione di un Manager di Escursione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Manager di Escursione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class ApplicationServiceManagerDiEscursione implements Actions, Views{
	
	private DAOFactory userFactory = null, 
			statoFactory = null, tipoFactory = null;
	
	private MDE_DAO mde_dao = null;
	private ManagerDiEscursioneTO mde = null;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Escursione
	 */
	public ApplicationServiceManagerDiEscursione() {
		userFactory = FactoryProducerDAO.getFactory(DAORequest.Users);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		mde_dao =  (MDE_DAO) userFactory.getUtenteDAO(Users.ManagerDiEscursione);
	}
	
	/**
	 * Metodo che restituisce la risposta rispetto alla richiesta di inserire un nuovo manager di escursione
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response nuovoManagerDiEscursione(Request request){
		Response response = new Response();
		
		try {
			if(!mde_dao.esisteEmail((UtenteTO)request.getData()) && !mde_dao.esisteUsername((UtenteTO)request.getData())){
				
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(Type.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(State.User);

				mde = (ManagerDiEscursioneTO)request.getData();
				mde.setRuolo(ruoliDao.getRuoloManagerDiSistema());
				mde.setStatoUtente(statoUtenteDao.getStatoAttivo());
				mde_dao.create(mde);
				Alert alert = new Alert(AlertType.INFORMATION, "Il Manager di Escursione è stato inserito correttamente!", ButtonType.OK);
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
	
}
