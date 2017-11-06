package outdoorapp.business.applicationservice;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.SessionCache;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un Partecipante.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Partecipante, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServicePartecipante implements Views, Actions{

	private DAOFactory userFactory = null, 
			statoFactory = null, tipoFactory = null;
	
	private Partecipante_DAO partecipante_dao = null;
	private PartecipanteTO partecipante = null;
	

	/**
	 * Costruttore che inizializza il DAO del Partecipante
	 */
	public ApplicationServicePartecipante() {
		userFactory = FactoryProducerDAO.getFactory(DAORequest.Users);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		partecipante_dao =  (Partecipante_DAO) userFactory.getUtenteDAO(UtenteDAOEnum.Partecipante);
	}

	/**
	 * Metodo che permette la ricerca del file da caricare e ne salva
	 * il path.
	 * 
	 * @param request: richiesta in ingresso
	 * @return response: una response in base alla request
	 */
	public Response caricaCertificatoSRC(Request request){
		partecipante = (PartecipanteTO) request.getData();
		Response response = new Response();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Carica Certificato SRC");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("TXT", "*.txt"));
		File fileCertificatoSRC = fileChooser.showOpenDialog(ViewCache.getCurrentView());
		if(fileCertificatoSRC != null){
			partecipante.setCertificatoSrc(fileCertificatoSRC.getPath());
			response.setResponse(RESP_OK);
		}else
			response.setResponse(RESP_KO);
		response.setData(partecipante);

		return response;
	}


	/**
	 * Metodo che permette la creazione e l'inserimento di un nuovo partecipante all'interno del sistema,
	 * controllando se il partecipante stesso non è già stato inserito nel sistema
	 * @param request: richiesta in ingresso
	 * @return response: responso dell'operazione
	 */
	public Response nuovoPartecipante(Request request){
		Response response = new Response();

		try {
			if(!partecipante_dao.esisteEmail((UtenteTO)request.getData()) && !partecipante_dao.esisteUsername((UtenteTO)request.getData())){
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(StatoDAOEnum.User);
				
				partecipante = (PartecipanteTO)request.getData();
				uploadCertificatoSRC(partecipante);
				partecipante.setRuolo(ruoliDao.getRuoloPartecipante());
				partecipante.setStatoUtente(statoUtenteDao.getStatoAttivo());
				partecipante_dao.create(partecipante);
				Alert alert = new Alert(AlertType.INFORMATION, "Il Partecipante è stato inserito correttamente!", ButtonType.OK);
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
	 * Metodo che crea, se non è stata già creata, la directory principale, e crea la directory
	 * avente il nome dell'username, che conterrà il certificato SRC. Quindi salva il 
	 * certificato SRC.
	 * 
	 * @param username
	 */
	private void uploadCertificatoSRC(PartecipanteTO partecipante){
		File rootDir = new File(ROOT_CERTIFICATE);
		if (!rootDir.exists()) {
			try{
				rootDir.mkdir();
			}catch(SecurityException se){
				se.printStackTrace();
			}        
		}

		String path = ROOT_CERTIFICATE + "\\" + partecipante.getUsername();
		File userCertificateDir = new File(path);
		if (!userCertificateDir.exists()) {
			try{
				userCertificateDir.mkdir();
			}catch(SecurityException se){
				se.printStackTrace();
			}        
		}

		try{
			File source = new File(partecipante.getCertificatoSrc());
			File dest = new File(userCertificateDir.getPath() + "\\" + source.getName());
			Files.copy(source.toPath(), dest.toPath());
			
			partecipante.setCertificatoSrc(dest.getPath());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che restituisce tutti i Partecipanti
	 * presenti nel sistema
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllPartecipanti(Request request){
		return null;
	}
}
