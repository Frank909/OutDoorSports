package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.UtenteTO;

/** 
 * Interfaccia che rappresenta i Data Access Object 
 * per tutte le operazioni CRUD per Utente.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Utente_DAO<T extends UtenteTO> extends GEN_DAO<T> {
	
	/**
	 * @param utente
	 * @return un'istanza T sottoclasse di Utente
	 * @throws DatabaseException
	 */
	T getUtente(UtenteTO utente) throws DatabaseException;
	
	/**
	 * @param username
	 * @return un'istanza T sottoclasse di Utente in base all'username
	 * @throws DatabaseException
	 */
	T getByUsername(String username) throws DatabaseException;
	
	/**
	 * @param email
	 * @return un'istanza T sottoclasse di Utente in base all'email
	 * @throws DatabaseException
	 */
	T getByEmail(String email) throws DatabaseException;
	
	/**
	 * @param id
	 * @return un'istanza T sottoclasse di Utente in base all'id
	 * @throws DatabaseException
	 */
	T getByID(Integer id) throws DatabaseException;
	
	/**
	 * @param utente
	 * @return vero se esiste l'username dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteUsername(UtenteTO utente) throws DatabaseException;
	
	/**
	 * @param utente
	 * @return vero se esiste l'email dell'utente, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteEmail(UtenteTO utente) throws DatabaseException;
	
}
