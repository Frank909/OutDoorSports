package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoUtenteTO;

/** 
 * Interfaccia che rappresenta in Data Access Object per 
 * tutte le operazioni CRUD per StatoUtente.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoUtente_DAO extends GEN_DAO<StatoUtenteTO> {
	
	/**
	 * @return stato disattivo dell'utente
	 * @throws DatabaseException
	 */
	StatoUtenteTO getStatoDisattivo() throws DatabaseException;
	
	/**
	 * @return stato attivo dell'utente
	 * @throws DatabaseException
	 */
	StatoUtenteTO getStatoAttivo() throws DatabaseException;

}
