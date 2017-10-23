package outdoorapp.business.applicationservice;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.AbstractFactoryDAO;
import outdoorapp.integration.dao.FactoryProducer;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiEscursione;
import outdoorapp.to.Utente;
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
	
	private AbstractFactoryDAO userFactory = null, 
			statoFactory = null, tipoFactory = null;
	
	private MDE_DAO mde_dao = null;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Escursione
	 */
	public ApplicationServiceManagerDiEscursione() {
		userFactory = FactoryProducer.getFactory(DAORequest.Users);
		statoFactory = FactoryProducer.getFactory(DAORequest.State);
		tipoFactory = FactoryProducer.getFactory(DAORequest.Type);
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
			if(!mde_dao.esisteEmail((Utente)request.getData()) && !mde_dao.esisteUsername((Utente)request.getData())){
				
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(Type.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(State.User);

				ManagerDiEscursione mde = (ManagerDiEscursione)request.getData();
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
