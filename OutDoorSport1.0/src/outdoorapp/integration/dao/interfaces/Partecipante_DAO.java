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
	 * @param codiceFiscale
	 * @return il Partecipante con un determinato codice fiscale 
	 * @throws DatabaseException
	 */
	PartecipanteTO readByCodiceFiscale(String codiceFiscale) throws DatabaseException;
	
	/**
	 * @param username
	 * @return il Partecipante con un determinato username
	 * @throws DatabaseException
	 */
	PartecipanteTO readByUsername(String username) throws DatabaseException;
	
	/**
	 * @return la lista di tutti i partecipanti
	 * @throws DatabaseException
	 */
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;
}
