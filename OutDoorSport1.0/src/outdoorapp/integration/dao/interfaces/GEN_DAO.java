package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Interfaccia che rappresenta una generalizzazione del pattern DAO per 
 * tutti i DAO presenti all'interno del package. In particolare rappresenta
 * i nomi dei metodi del CRUD di una tabella del database, che sono generici per tutti.
 * Inoltre sono stati inseriti i nomi dei metodi per generalizzare le esecuzioni di 
 * una query con o senza parametri.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 * @param <T>
 */

public interface GEN_DAO<T extends OutDoorSports>{
	
	/**
	 * Inserisce l'entità di tipo T nel database
	 * 
	 * @param entity
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	T create(T entity) throws DatabaseException;
	
	/**
	 * Inserisce l'entità di tipo T nel database o,
	 * se esiste, la modifica
	 * 
	 * @param entity
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	T createOrUpdate(final T entity) throws DatabaseException;
	
	/**
	 * Restituisce una specifica entità di tipo T presente nel database
	 * in base al suo id
	 * 
	 * @param id
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	T findOne(final int id) throws DatabaseException;
	
	/**
	 * Restituisce la lista di tutte le entità di tipo T presenti in una
	 * tabella del database.
	 * 
	 * @return lista di entità di tipo T
	 * @throws DatabaseException
	 */
	List<T> getAll() throws DatabaseException;
	
	/**
	 * Aggiorna una specifica entità già presente nel database
	 * 
	 * @param entity
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	T update(final T entity) throws DatabaseException;
	
	/**
	 * Elimina una entità già presente nel database
	 * 
	 * @param entity
	 * @throws DatabaseException 
	 */
	void delete(final T entity) throws DatabaseException;
}
