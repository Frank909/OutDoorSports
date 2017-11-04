package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per gli Optional collegati
 * alle Escursioni. Sono presenti i metodi 
 * di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface OptionalEscursione_DAO extends GEN_DAO<OptionalEscursioneTO>{

	/**
	 * @return gli Optional di una determinata Escursione
	 * @throws DatabaseException
	 */
	public List<OptionalEscursioneTO> getOptionalsFromEscursione(int idEscursione) throws DatabaseException;
	
	/**
	 * Metodo che restituisce l'ID dell'associazione Optional Escursione
	 * 
	 * @param escursione
	 * @param optional
	 * @return l'ID dell'associazione
	 * @throws DatabaseException
	 */
	public List<OptionalEscursioneTO> getAssociationID(EscursioneTO escursione, OptionalTO optional) throws DatabaseException;
}
