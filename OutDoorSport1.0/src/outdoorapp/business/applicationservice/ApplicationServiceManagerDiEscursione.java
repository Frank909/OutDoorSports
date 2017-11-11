package outdoorapp.business.applicationservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.EncryptPasswordTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.EmailConfig;
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
	private TOFactory TOFact = null;
	private EncryptPasswordTO passwordEncryptor = null;
	
	/**
	 * Costruttore che inizializza il DAO del Manager di Escursione
	 */
	public ApplicationServiceManagerDiEscursione() {
		userFactory = FactoryProducerDAO.getFactory(DAORequest.Users);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		tipoFactory = FactoryProducerDAO.getFactory(DAORequest.Type);
		mde_dao =  (MDE_DAO) userFactory.getUtenteDAO(UtenteDAOEnum.ManagerDiEscursione);
		if(passwordEncryptor == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			passwordEncryptor = (EncryptPasswordTO) TOFact.getGenericTO(GenericEnum.EncryptPassword);
		}
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
				
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(StatoDAOEnum.User);

				mde = (ManagerDiEscursioneTO)request.getData();
				mde.setRuolo(ruoliDao.getRuoloManagerDiEscursione());
				mde.setStatoUtente(statoUtenteDao.getStatoAttivo());
				
				String password = mde.getPassword();
				mde.setPassword(passwordEncryptor.encryptPassword(password));
				
				mde_dao.create(mde);
				
				TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Registrazione Manager Di Escursione";
				String mailMessaggio = "Gentile ";
				mailMessaggio += mde.getNome() + " " + mde.getCognome() + ", \n";
				mailMessaggio += "I dati relativi al suo account sono stati inseriti dal Manager di Sistema! \n";
				mailMessaggio += "Username: " + mde.getUsername() + "\n";
				mailMessaggio += "Password: " + password + "\n";

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
				listaDestinatari.add(mde);
				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				
				Alert alert = new Alert(AlertType.INFORMATION, "Il Manager di Escursione è stato inserito correttamente!", ButtonType.OK);
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
	 * Metodo che restituisce la risposta rispetto alla richiesta di modificare un manager di escursione
	 * esistente, notificando il manager, via email, delle avvenute modifiche
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response modificaManagerDiEscursione(Request request){
		Response response = new Response();
		mde = (ManagerDiEscursioneTO)request.getData();
		UtenteTO temp = null;
		int id_temp = -1;
		
		try {
			temp = mde_dao.getByEmail(mde.getEmail());
			if(temp.getIdUtente() != null)
				id_temp = temp.getIdUtente();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		try {
			if((mde_dao.esisteEmail(mde) || temp == null) || (mde_dao.esisteEmail(mde) && mde.getIdUtente() == id_temp)){
				Ruoli_DAO ruoliDao = (Ruoli_DAO) tipoFactory.getTipoDAO(TipoDAOEnum.Ruolo);
				StatoUtente_DAO statoUtenteDao = (StatoUtente_DAO) statoFactory.getStatoDAO(StatoDAOEnum.User);
						
				mde.setRuolo(ruoliDao.getRuoloManagerDiEscursione());
				mde.setStatoUtente(statoUtenteDao.getStatoAttivo());
				
				ManagerDiEscursioneTO mde_into_db = mde_dao.getByID(mde.getIdUtente());
				if(!mde.getPassword().equals(mde_into_db.getPassword())){
					String newPassword = passwordEncryptor.encryptPassword(mde.getPassword());
					mde.setPassword(newPassword);
				}
				
				mde_dao.update(mde);
				
				TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Modifica Dati Manager Di Escursione";
				String mailMessaggio = "Gentile ";
				mailMessaggio += mde.getNome() + " " + mde.getCognome() + ", \n";
				mailMessaggio += "I dati relativi al suo account sono stati modificati dal Manager di Sistema! \n";
				mailMessaggio += "Username: " + mde.getUsername() + "\n";
				mailMessaggio += "Password: " + mde.getPassword() + "\n";

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
				listaDestinatari.add(mde);
				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				
				Alert alert = new Alert(AlertType.INFORMATION, "Il Manager di Escursione è stato modificato correttamente!", ButtonType.OK);
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
	 * Metodo che restituisce tutti i Manager di Escursione 
	 * presenti nel sistema
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllManagerDiEscursione(Request request){
		Response response = new Response();
		
		try {
			List<ManagerDiEscursioneTO> list_mde = mde_dao.getAll();
			response.setData(list_mde);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
}
