package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.Utente;
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

public class UtenteDAO<T extends Utente> extends GenericDAO<T>{
	
	/**
	 * @param utente
	 * @return un'istanza T sottoclasse di Utente
	 * @throws DatabaseException
	 */
	@SuppressWarnings("unchecked")
	public T getUtente(Utente utente) throws DatabaseException {
		Utente response = null;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		param.add(utente.getPassword());
		List<T> list = this.executeParamQuery("boooooooooooooooh", param);
		if(list.size() == 1){
			response = list.get(0);
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
	public T getByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		return this.getUtenteByQuery("boooooooooooooooooh", param);
	}
	
	/**
	 * @param email
	 * @return un'istanza T sottoclasse di Utente in base all'email
	 * @throws DatabaseException
	 */
	public T getByEmail(String email) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(email);
		return this.getUtenteByQuery("boooooooooooooh", param);
	}
	
	/**
	 * @param id
	 * @return un'istanza T sottoclasse di Utente in base all'id
	 * @throws DatabaseException
	 */
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
		return utente.getIdUtente() == -1;
	}
	
	/**
	 * @param utente
	 * @return vero se esiste l'username dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	public boolean esisteUsername(Utente utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		Utente newUtente = this.getUtenteByQuery("boooooooooooooooooh", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
	
	/**
	 * @param utente
	 * @return vero se esiste l'email dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	public boolean esisteEmail(Utente utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getEmail());
		Utente newUtente = this.getUtenteByQuery("booooooooooooooooooh", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
}
