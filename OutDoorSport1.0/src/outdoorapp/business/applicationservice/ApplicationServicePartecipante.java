package outdoorapp.business.applicationservice;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.EncryptPasswordTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.EmailConfig;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un Partecipante.
 * L'obiettivo � quello di raccogliere tutte le azioni che � possibile effettuare per 
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
	private EncryptPasswordTO encryptedPassword = null;
	

	/**
	 * Costruttore che inizializza il DAO del Partecipante
	 */
	public ApplicationServicePartecipante() {
		userFactory = FactoryProducerDAO.getFactory(DAORequest.Users);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		partecipante_dao =  (Partecipante_DAO) userFactory.getUtenteDAO(UtenteDAOEnum.Partecipante);
		if(encryptedPassword == null){
			TOFactory toFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			encryptedPassword = (EncryptPasswordTO) toFactory.getGenericTO(GenericEnum.EncryptPassword);
		}
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
	 * Metodo che restituisce tutte le escursione a cui il partecipante � iscritto
	 * @param request
	 * @return response: risposta in base alla richiesta
	 */
	public Response getAllEscursioniIscritte(Request request){
		Response response = new Response();
		try {
			PartecipanteTO partecipante = (PartecipanteTO) request.getData();
			DAOFactory daofact = (DAOFactory) FactoryProducerDAO.getFactory(DAORequest.Generic);
			Escursione_DAO escursione_dao = (Escursione_DAO) daofact.getGenericDAO(GenericDAOEnum.Escursione);
			List<EscursioneTO> list_escursioni = escursione_dao.readEscursioniIscritte(partecipante);
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce la risposta rispetto alla richiesta di modificare un partecipante
	 * esistente.
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response modificaPartecipante(Request request){
		Response response = new Response();
		
		partecipante = (PartecipanteTO)request.getData();
		UtenteTO temp = null;
		int id_temp = -1;
		
		try {
			temp = partecipante_dao.getByEmail(partecipante.getEmail());
			if(temp.getIdUtente() != null)
				id_temp = temp.getIdUtente();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		
		try {
			if(!(partecipante_dao.esisteEmail(partecipante) || temp == null) || (partecipante_dao.esisteEmail(partecipante) 
					&& partecipante.getIdUtente() == id_temp)){
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(StatoDAOEnum.User);
				partecipante.setRuolo(ruoliDao.getRuoloManagerDiEscursione());
				partecipante.setStatoUtente(statoUtenteDao.getStatoAttivo());
				
				PartecipanteTO partecipante_into_db = partecipante_dao.getByID(partecipante.getIdUtente());
				if(!partecipante.getPassword().equals(partecipante_into_db.getPassword())){
					String newPassword = encryptedPassword.encryptPassword(partecipante.getPassword());
					partecipante.setPassword(newPassword);
				}
				
				uploadCertificatoSRC(partecipante);
				partecipante_dao.update(partecipante);
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
	 * Metodo che permette la creazione e l'inserimento di un nuovo partecipante all'interno del sistema,
	 * controllando se il partecipante stesso non � gi� stato inserito nel sistema
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
				
				String password = partecipante.getPassword();
				partecipante.setPassword(encryptedPassword.encryptPassword(password));
				
				partecipante_dao.create(partecipante);
				
				TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Registrazione Partecipante";
				String mailMessaggio = "Gentile ";
				mailMessaggio += partecipante.getNome() + " " + partecipante.getCognome() + ", \n";
				mailMessaggio += "I dati relativi al suo account sono stati inseriti dal Manager di Sistema! \n";
				mailMessaggio += "Username: " + partecipante.getUsername() + "\n";
				mailMessaggio += "Password: " + password + "\n";

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
				listaDestinatari.add(partecipante);
				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				
				Alert alert = new Alert(AlertType.INFORMATION, "Il Partecipante � stato inserito correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				
				Optional<ButtonType> res = alert.showAndWait();
				
				if(res.get() == ButtonType.OK)
					emailConfig.sendEmail(email);
					
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
	 * Metodo che crea, se non � stata gi� creata, la directory principale, e crea la directory
	 * avente il nome dell'username, che conterr� il certificato SRC. Quindi salva il 
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
		}else
			for(File file: userCertificateDir.listFiles()) 
				if(!file.isDirectory()) 
					if(!file.getPath().equals(partecipante.getCertificatoSrc())) 
						file.delete();
				

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
		Response response = new Response();
		try {
			List<PartecipanteTO> list_partecipante = partecipante_dao.getAll();
			response.setData(list_partecipante);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
}
