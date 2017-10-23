package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.UtenteTO;

public interface Utente_DAO<T extends OutDoorSports> extends GEN_DAO<T> {
	T getUtente(UtenteTO utente) throws DatabaseException;
	T getByUsername(String username) throws DatabaseException;
	T getByEmail(String email) throws DatabaseException;
	T getByID(Integer id) throws DatabaseException;
	boolean esisteUsername(UtenteTO utente) throws DatabaseException;
	boolean esisteEmail(UtenteTO utente) throws DatabaseException;
	
}
