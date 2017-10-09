package outdoorapp.business.applicationservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.UtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Utente;

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

public class ApplicationServiceUtente {

	UtenteDAO<Utente> utente_dao;
	
	/**
	 * Costruttore che inizializza il DAO dell'Utente
	 */
	public ApplicationServiceUtente() {
		utente_dao = new UtenteDAO<Utente>();
	}
	
	/**
	 * Autenticazione dell'Utente.
	 * 
	 * @param richiesta che contiene i dati da controllare per l'autenticazione
	 * @return la risposta in base alla richiesta. Se l'autenticazione va a buon fine viene restituito un Utente
	 */
	public Response autenticazione(Request request){
		Response response = new Response();
		
		Utente utente = (Utente)request.getData();
		Utente newUtente = new Utente();
		try {
			newUtente = utente_dao.getByUsername(utente.getUsername());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ciao sono nel application service utente");
		response.setData(newUtente);
		response.setResponse("utente riconosciuto");
		
		return response;
	}
}
