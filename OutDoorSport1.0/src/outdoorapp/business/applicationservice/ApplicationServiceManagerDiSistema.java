package outdoorapp.business.applicationservice;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.ManagerDiSistemaDAO;
import outdoorapp.integration.dao.RuoliDAO;
import outdoorapp.integration.dao.StatoUtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un Manager di Sistema.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Manager di Sistema, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceManagerDiSistema implements Actions, Views{

	private ManagerDiSistemaDAO mds_dao;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Sistema
	 */
	public ApplicationServiceManagerDiSistema() {
		mds_dao = new ManagerDiSistemaDAO();
	}
	
	/**
	 * Metodo che resistitusce la risposta rispetto alla richiesta di verificare il manager di sistema.
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response verificaManagerDiSistema(Request request){
		Response response = new Response();
		
		boolean result = false;
		try {
			result = mds_dao.verificaManagerDiSistema();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		response.setData(null);
		if(result){
			response.setResponse(RESP_OK);
			response.setView(VIEW_LOGIN);
		}else{
			response.setView(VIEW_MANAGER_DI_SISTEMA_CONFIG);
			response.setResponse(RESP_KO);
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce la risposta rispetto alla richiesta di inserire un nuovo manager di sistema
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response nuovoManagerDiSistema(Request request){
		Response response = new Response();
		
		try {
			if(!mds_dao.esisteEmail((Utente)request.getData()) && !mds_dao.esisteUsername((Utente)request.getData())){
				
				RuoliDAO ruoliDao = new RuoliDAO();
				StatoUtenteDAO statoUtenteDao = new StatoUtenteDAO();
				ManagerDiSistema mds = (ManagerDiSistema)request.getData();
				mds.setRuolo(ruoliDao.getRuoloManagerDiSistema());
				mds.setStatoUtente(statoUtenteDao.getStatoAttivo());
				mds_dao.create(mds);
				Alert alert = new Alert(AlertType.INFORMATION, "Il Manager di Sistema è stato inserito correttamente!", ButtonType.OK);
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
