package outdoorapp.business.applicationservice;
import java.util.ArrayList;


import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.Users;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.integration.dao.interfaces.MDS_DAO;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.integration.dao.interfaces.Utente_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;
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

	private DAOFactory DAOFact = null;
	private TOFactory TOFact = null;

	private Utente_DAO<UtenteTO> utente_dao = null;
	private UtenteTO utente = null;

	/**
	 * Costruttore che inizializza il DAO dell'Utente
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceUtente() {
		if(utente_dao == null){
			DAOFact = FactoryProducerDAO.getFactory(DAORequest.Users);
			utente_dao = (Utente_DAO<UtenteTO>) DAOFact.getUtenteDAO(Users.Utente);
		}
		if(utente == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			utente = (UtenteTO) TOFact.getUtenteTO(UtenteEnum.Utente);
		}

	}

	/**
	 * Autenticazione dell'Utente.
	 * 
	 * @param richiesta che contiene i dati da controllare per l'autenticazione
	 * @return la risposta in base alla richiesta. Se l'autenticazione va a buon fine viene restituito un Utente
	 */
	public Response eseguiLogin(Request request){
		Response response = new Response();
		try {
			utente = (UtenteTO) utente_dao.getUtente((UtenteTO) request.getData());

			if(utente.getIdUtente() != null){
				OutDoorSports newUtente = checkUserTipe(utente);	
				response.setData(newUtente);

				response.setResponse(RESP_OK);
				if(utente instanceof ManagerDiSistemaTO)
					response.setView(VIEW_DASHBOARD_MANAGER_DI_SISTEMA);
				if(utente instanceof ManagerDiEscursioneTO)
					response.setView(VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE);
				if(utente instanceof PartecipanteTO)
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
		utente = (UtenteTO) request.getData();
		Response response = new Response();

		try {
			if(utente_dao.esisteEmail(utente)){
				utente = (UtenteTO) utente_dao.getByEmail(utente.getEmail());

				RandomString randomString = new RandomString(8);
				String newPassword = randomString.nextString();


				utente.setPassword(newPassword);
				utente_dao.create(utente);
				
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Recupero Password";
				String mailMessaggio = "Gentile ";
				mailMessaggio += utente.getNome() + " " + utente.getCognome() + ", \n";
				mailMessaggio += "La sua nuova password per lo username ";
				mailMessaggio += utente.getUsername() + " è: \n";
				mailMessaggio += newPassword;

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
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

	private OutDoorSports checkUserTipe(UtenteTO utente){

		OutDoorSports result = null;

		Partecipante_DAO partecipante_dao = (Partecipante_DAO) DAOFact.getUtenteDAO(Users.Partecipante);
		MDS_DAO mds_dao = (MDS_DAO) DAOFact.getUtenteDAO(Users.ManagerDiSistema);
		MDE_DAO mde_dao = (MDE_DAO) DAOFact.getUtenteDAO(Users.ManagerDiEscursione);

		PartecipanteTO partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		ManagerDiSistemaTO mds = (ManagerDiSistemaTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiSistema);
		ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) TOFact.getUtenteTO(UtenteEnum.ManagerDiEscursione);

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
	private void setData(UtenteTO newData, UtenteTO data){
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
