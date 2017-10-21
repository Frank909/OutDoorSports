package outdoorapp.business.applicationservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.ManagerDiEscursioneDAO;
import outdoorapp.integration.dao.ManagerDiSistemaDAO;
import outdoorapp.integration.dao.PartecipanteDAO;
import outdoorapp.integration.dao.UtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.to.Email;
import outdoorapp.to.ManagerDiEscursione;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.EmailConfig;
import outdoorapp.utils.RandomString;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un Utente.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Utente, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceUtente implements Views, Actions{

	private UtenteDAO<Utente> utente_dao;
	
	/**
	 * Costruttore che inizializza il DAO dell'Utente
	 */
	public ApplicationServiceUtente() {
		utente_dao = new UtenteDAO<>();
	}
	
	/**
	 * Autenticazione dell'Utente.
	 * 
	 * @param richiesta che contiene i dati da controllare per l'autenticazione
	 * @return la risposta in base alla richiesta. Se l'autenticazione va a buon fine viene restituito un Utente
	 */
	public Response eseguiLogin(Request request){
		Utente utente = new Utente();
		Response response = new Response();
		try {
			utente = utente_dao.getUtente((Utente) request.getData());
			
			if(utente.getIdUtente() != null){
				OutDoorSports newUtente = checkUserTipe(utente);	
				response.setData(newUtente);
				
				response.setResponse(RESP_OK);
				if(utente instanceof ManagerDiSistema)
					response.setView(VIEW_DASHBOARD_MANAGER_DI_SISTEMA);
				if(utente instanceof ManagerDiEscursione)
					response.setView(VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE);
				if(utente instanceof Partecipante)
					response.setView(VIEW_DASHBOARD_PARTECIPANTE);
			}else{
				response.setResponse(RESP_KO);
				response.setView("Errore! Utente o Password non riconosciuti!");
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Metodo che invia una mail al destinatario (se la mail esiste) con la nuova password
	 * 
	 * @param richiesta da eseguire
	 * @return risultato della richiesta
	 */
	public Response richiediNuovaPassword(Request request){
		Utente utente = (Utente) request.getData();
		Response response = new Response();
		
		try {
			if(utente_dao.esisteEmail(utente)){
				utente = utente_dao.getByEmail(utente.getEmail());
				
				RandomString randomString = new RandomString(8);
				String newPassword = randomString.nextString();
				
				
				utente.setPassword(newPassword);
				utente_dao.update(utente);
				
				Email email = new Email();
				
				String mailOggetto = "OutDoorSports | Recupero Password";
				String mailMessaggio = "Gentile ";
				mailMessaggio += utente.getNome() + " " + utente.getCognome() + ", \n";
				mailMessaggio += "La sua nuova password per lo username ";
				mailMessaggio += utente.getUsername() + " è: \n";
				mailMessaggio += newPassword;
				
				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);
				
				ArrayList<Utente> listaDestinatari = new ArrayList<>();
				listaDestinatari.add(utente);
				email.setListaDestinatari(listaDestinatari);
				
				EmailConfig emailConfig = new EmailConfig();
				emailConfig.sendEmail(email);
				
				response.setResponse(RESP_OK);
			}
			else{
				response.setResponse(RESP_KO);
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Metodo privato che identifica il tipo di utente che si è autenticato
	 * 
	 * @param utente
	 * @return una nuova istanza del tipo di utente che si è autenticato
	 */
	
	private OutDoorSports checkUserTipe(Utente utente){
		
		OutDoorSports result = null;
		PartecipanteDAO partecipante_dao = new PartecipanteDAO();
		ManagerDiSistemaDAO mds_dao = new ManagerDiSistemaDAO();
		ManagerDiEscursioneDAO mde_dao = new ManagerDiEscursioneDAO();
		Partecipante partecipante = new Partecipante();
		ManagerDiSistema mds = new ManagerDiSistema();
		ManagerDiEscursione mde = new ManagerDiEscursione();
		
		try {
			partecipante = partecipante_dao.findOne(utente.getIdUtente());
			if(partecipante != null){
				setData(partecipante, utente);
				result = partecipante;
			}
			mds = mds_dao.findOne(utente.getIdUtente());
			if(mds != null){
				setData(mds, utente);
				result = mds;
			}
			mde = mde_dao.findOne(utente.getIdUtente());
			if(mde != null){
				setData(mde, utente);
				result = mde;
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Metodo di supporto a checkUserTipe, inserisce i dati raccolti in precedenza
	 * nella nuova istanza dopo aver identificato il tipo 
	 * 
	 * @param newData
	 * @param data
	 */
	private void setData(Utente newData, Utente data){
		newData.setIdUtente(data.getIdUtente());
		newData.setUsername(data.getUsername());
		newData.setPassword(data.getPassword());
		newData.setNome(data.getNome());
		newData.setCognome(data.getCognome());
		newData.setCodiceFiscale(data.getCodiceFiscale());
		newData.setIndirizzo(data.getIndirizzo());
		newData.setCitta(data.getCitta());
		newData.setEmail(data.getEmail());
		newData.setSesso(data.getSesso());
		newData.setDataNascita(data.getDataNascita());
	}
}
