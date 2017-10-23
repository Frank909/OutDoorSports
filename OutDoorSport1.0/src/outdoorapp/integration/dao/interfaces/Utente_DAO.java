package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Utente;

public interface Utente_DAO<T extends OutDoorSports> extends GEN_DAO<T> {
	T getUtente(Utente utente) throws DatabaseException;
	T getByUsername(String username) throws DatabaseException;
	T getByEmail(String email) throws DatabaseException;
	T getByID(Integer id) throws DatabaseException;
	boolean esisteUsername(Utente utente) throws DatabaseException;
	boolean esisteEmail(Utente utente) throws DatabaseException;
	
}
