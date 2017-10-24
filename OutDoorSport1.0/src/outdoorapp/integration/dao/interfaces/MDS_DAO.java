package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;

/** 
 * Interfaccia che rappresenta Data Access Object per tutte 
 * le operazioni CRUD per Manager di Sistema.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface MDS_DAO extends Utente_DAO<ManagerDiSistemaTO> {
	
	/**
	 * @return vero se esiste il manager di sistema, falso altrimenti
	 * @throws DatabaseException
	 */
	public boolean verificaManagerDiSistema() throws DatabaseException;
}
