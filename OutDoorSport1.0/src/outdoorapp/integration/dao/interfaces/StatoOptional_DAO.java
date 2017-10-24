package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoOptionalTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per StatoOptional.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoOptional_DAO extends GEN_DAO<StatoOptionalTO> {
	
	/**
	 * @return stato disattivo dell'optional
	 * @throws DatabaseException
	 */
	StatoOptionalTO getStatoDisattivo() throws DatabaseException;
	
	/**
	 * @return stato attivo dell'optional
	 * @throws DatabaseException
	 */
	StatoOptionalTO getStatoAttivo() throws DatabaseException;
}
