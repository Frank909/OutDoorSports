package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.StatoIscrizione;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoIscrizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class StatoIscrizioneDAO extends GenericDAO<StatoIscrizione>{

	/**
	 * Il costruttore inizializza l'entità StatoIscrizione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoIscrizioneDAO() {
		this.setCurrentClass(new StatoIscrizione());
	}
	
	/**
	 * @return lo stato attivo di una iscrizione
	 * @throws DatabaseException
	 */
	public StatoIscrizione getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	/**
	 * @return lo stato disattivo di una iscrizione
	 * @throws DatabaseException
	 */
	public StatoIscrizione getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

	/**
	 * @return lo stato terminato di una iscrizione
	 * @throws DatabaseException
	 */
	public StatoIscrizione getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}
