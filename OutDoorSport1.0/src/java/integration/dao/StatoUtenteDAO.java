package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.StatoUtente;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoUtente.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class StatoUtenteDAO extends GenericDAO<StatoUtente> {

	/**
	 * Il costruttore inizializza l'entità StatoUtente da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoUtenteDAO() {
		this.setCurrentClass(new StatoUtente());
	}

	/**
	 * @return stato disattivo dell'utente
	 * @throws DatabaseException
	 */
	public StatoUtente getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return stato attivo dell'utente
	 * @throws DatabaseException
	 */
	public StatoUtente getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
