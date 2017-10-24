package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TipoOptionalTO;

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
	
	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo attivi
	 * @throws DatabaseException
	 */
	List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO tipoOptional) throws DatabaseException;
	
	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo
	 * @throws DatabaseException
	 */	
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO tipoOptional) throws DatabaseException ;
	
}
