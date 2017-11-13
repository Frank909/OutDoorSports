package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.PartecipanteTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per Partecipante.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Partecipante_DAO extends Utente_DAO<PartecipanteTO>{
	
	/**
	 * @return la lista di tutti i partecipanti
	 * @throws DatabaseException
	 */
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;
}
