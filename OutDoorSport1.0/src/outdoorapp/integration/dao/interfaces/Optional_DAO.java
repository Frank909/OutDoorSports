package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OptionalTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per Optional. Sono presenti i metodi 
 * di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Optional_DAO extends GEN_DAO<OptionalTO> {
	
	/**
	 * @param optional
	 * @return un Optional modificato in disattivato
	 * @throws DatabaseException
	 */
	OptionalTO disattivaOptional(OptionalTO optional) throws DatabaseException;
}
