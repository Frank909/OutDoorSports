package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoIscrizioneTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per StatoIscrizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoIscrizione_DAO extends GEN_DAO<StatoIscrizioneTO> {
	
	/**
	 * @return lo stato attivo di una iscrizione
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
	
	/**
	 * @return lo stato disattivo di una iscrizione
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoDisattivo() throws DatabaseException;
	
	/**
	 * @return lo stato terminato di una iscrizione
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException;

}
