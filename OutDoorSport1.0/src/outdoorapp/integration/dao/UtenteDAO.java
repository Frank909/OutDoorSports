package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Utente_DAO;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.StatoUtente;
import outdoorapp.to.Utente;
import java.util.ArrayList;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Utente.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class UtenteDAO<T extends OutDoorSports> extends GenericDAO<T> implements Utente_DAO<T>{
	

	/**
	 * Il costruttore inizializza l'entità Utente da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public UtenteDAO() {
		this.setCurrentClass(new Utente());
	}
	
	/**
	 * @param utente
	 * @return un'istanza T sottoclasse di Utente
	 * @throws DatabaseException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getUtente(Utente utente) throws DatabaseException {
		Utente response = null;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		param.add(utente.getPassword());
		List<T> list = this.executeParamQuery("getUtente", param);
		if(list.size() == 1){
			response = (Utente) list.get(0);
		} else {
			response = new Utente();
		}
		return (T)response;
	}
	
	/**
	 * @param queryName
	 * @param params
	 * @return un'istanza T sottoclasse di Utente tramite query
	 * @throws DatabaseException
	 */
	@SuppressWarnings({ "unchecked" })
	private T getUtenteByQuery(String queryName, List<?> params) throws DatabaseException{
		Utente response = null;
		List<T> list = super.executeParamQuery(queryName, params);
		if(list.size() == 0){
			response = new Utente();
		} else {
			response = (Utente)list.get(0);
		}
		return (T)response;
	}

	/**
	 * @param username
	 * @return un'istanza T sottoclasse di Utente in base all'username
	 * @throws DatabaseException
	 */
	@Override
	public T getByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		return this.getUtenteByQuery("getByUsername", param);
	}
	
	/**
	 * @param email
	 * @return un'istanza T sottoclasse di Utente in base all'email
	 * @throws DatabaseException
	 */
	@Override
	public T getByEmail(String email) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(email);
		return this.getUtenteByQuery("getByEmail", param);
	}
	
	/**
	 * @param id
	 * @return un'istanza T sottoclasse di Utente in base all'id
	 * @throws DatabaseException
	 */
	@Override
	public T getByID(Integer id) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);
		return getUtenteByQuery("boooooooooooooooooh", param);
	}
	
	/**
	 * @param utente
	 * @return vero se è un utente nullo, falso altrimenti
	 */
	private boolean isNullUtente(Utente utente){
		return utente.getIdUtente() == null;
	}
	
	/**
	 * @param utente
	 * @return vero se esiste l'username dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	@Override
	public boolean esisteUsername(Utente utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		Utente newUtente = (Utente) this.getUtenteByQuery("getByUsername", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
	
	/**
	 * @param utente
	 * @return vero se esiste l'email dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	@Override
	public boolean esisteEmail(Utente utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getEmail());
		Utente newUtente = (Utente) this.getUtenteByQuery("getByEmail", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
}
