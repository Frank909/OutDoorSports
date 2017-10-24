package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.RuoliTO;

/** 
 * Interfaccia che rappresente i Data Access Object per 
 * tutte le operazioni CRUD per Ruoli.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Ruoli_DAO extends GEN_DAO<RuoliTO> {
	
	/**
	 * @return il ruolo di Manager di Sistema
	 * @throws DatabaseException
	 */
	RuoliTO getRuoloManagerDiSistema() throws DatabaseException;
	
	/**
	 * @return il ruolo di Manager di Escursione
	 * @throws DatabaseException
	 */
	RuoliTO getRuoloManagerDiEscursione() throws DatabaseException;
	
	/**
	 * @return il ruolo di Partecipante
	 * @throws DatabaseException
	 */
	RuoliTO getRuoloPartecipante() throws DatabaseException;
}
