package outdoorapp.business.applicationservice;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.ManagerDiSistemaDAO;
import outdoorapp.integration.dao.RuoliDAO;
import outdoorapp.integration.dao.StatoUtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.Utente;
import outdoorapp.utils.KeyMap;

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

public class ApplicationServiceManagerDiSistema implements KeyMap{

	ManagerDiSistemaDAO mds_dao;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Sistema
	 */
	public ApplicationServiceManagerDiSistema() {
		mds_dao = new ManagerDiSistemaDAO();
	}
	

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
			response.setView("../../resources/fxml/application/login");
		}
		else{
			response.setView("../../resources/fxml/manager_sistema/managerDiSistemaConfig");
			response.setResponse(RESP_KO);
		}
		
		return response;
	}
	
	public Response nuovoManagerDiSistema(Request request){
		Response response = new Response();
		
		ManagerDiSistemaDAO mds_dao = new ManagerDiSistemaDAO();
		
		try {
			if(!mds_dao.esisteEmail((Utente)request.getData()) && !mds_dao.esisteUsername((Utente)request.getData())){
				
				RuoliDAO ruoliDao = new RuoliDAO();
				StatoUtenteDAO statoUtenteDao = new StatoUtenteDAO();
				ManagerDiSistema mds = (ManagerDiSistema)request.getData();
				mds.setRuolo(ruoliDao.getRuoloManagerDiSistema());
				mds.setStatoUtente(statoUtenteDao.getStatoAttivo());
				mds_dao.create(mds);
			}
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
}
