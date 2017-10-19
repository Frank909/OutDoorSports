package outdoorapp.business.applicationservice;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.ManagerDiEscursioneDAO;
import outdoorapp.integration.dao.ManagerDiSistemaDAO;
import outdoorapp.integration.dao.RuoliDAO;
import outdoorapp.integration.dao.StatoUtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiEscursione;
import outdoorapp.to.ManagerDiSistema;
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
public class ApplicationServiceManagerDiEscursione implements Actions, Views{
	
	private ManagerDiEscursioneDAO mde_dao;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Escursione
	 */
	public ApplicationServiceManagerDiEscursione() {
		mde_dao = new ManagerDiEscursioneDAO();
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
				
				RuoliDAO ruoliDao = new RuoliDAO();
				StatoUtenteDAO statoUtenteDao = new StatoUtenteDAO();
				ManagerDiEscursione mde = (ManagerDiEscursione)request.getData();
				mde.setRuolo(ruoliDao.getRuoloManagerDiEscursione());
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
