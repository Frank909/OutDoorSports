package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoIscrizione_DAO;
import outdoorapp.to.StatoIscrizione;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoIscrizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoIscrizioneDAO extends GenericDAO<StatoIscrizione> implements StatoIscrizione_DAO{

	/**
	 * Il costruttore inizializza l'entit� StatoIscrizione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoIscrizioneDAO() {
		this.setCurrentClass(new StatoIscrizione());
	}
	
	/**
	 * @return lo stato attivo di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizione getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	/**
	 * @return lo stato disattivo di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizione getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

	/**
	 * @return lo stato terminato di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizione getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}
