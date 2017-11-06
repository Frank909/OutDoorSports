package outdoorapp.business.applicationservice;

import java.util.List;

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
import outdoorapp.integration.dao.interfaces.MDS_DAO;
import outdoorapp.integration.dao.interfaces.Optional_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.SessionCache;
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

	private DAOFactory userFactory = null, 
			statoFactory = null, tipoFactory = null, optionalFactory = null;

	private MDS_DAO mds_dao = null;
	private TipoEscursione_DAO tipo_escursione_dao = null;
	private Optional_DAO optional_dao = null;
	
	private ManagerDiSistemaTO mds = null;
	private List<TipoEscursioneTO> list_tipi_escursione = null;
	private List<OptionalTO> list_optional = null;
	
	
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Sistema
	 */
	public ApplicationServiceManagerDiSistema() {
		userFactory = FactoryProducerDAO.getFactory(DAORequest.Users);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		optionalFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		mds_dao =  (MDS_DAO) userFactory.getUtenteDAO(UtenteDAOEnum.ManagerDiSistema);
		tipo_escursione_dao = (TipoEscursione_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Escursione);
		optional_dao = (Optional_DAO) optionalFactory.getGenericDAO(GenericDAOEnum.Optional);
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
		/*
		try {
			
			list_tipi_escursione = tipo_escursione_dao.getAll();
			currentData.put("TipiEscursione", list_tipi_escursione);
			list_optional = optional_dao.getAll();
			currentData.put("Optionals", list_optional);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		response.setData(null);
		if(result){
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
			if(!mds_dao.esisteEmail((UtenteTO)request.getData()) && !mds_dao.esisteUsername((UtenteTO)request.getData())){
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(StatoDAOEnum.User);

				mds = (ManagerDiSistemaTO)request.getData();
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
