package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.StatoOptional;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoOptional.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class StatoOptionalDAO extends GenericDAO<StatoOptional> {

	/**
	 * Il costruttore inizializza l'entità StatoOptional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoOptionalDAO() {
		this.setCurrentClass(new StatoOptional());
	}
	
	/**
	 * @return stato disattivo dell'optional
	 * @throws DatabaseException
	 */
	public StatoOptional getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return stato attivo dell'optional
	 * @throws DatabaseException
	 */
	public StatoOptional getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
