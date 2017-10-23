package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Utente;

public interface GEN_DAO<T extends OutDoorSports>{
	T create(T entity) throws DatabaseException;
	T findOne(final int id) throws DatabaseException;
	List<T> getAll() throws DatabaseException;
	T update(final T entity) throws DatabaseException;
	void delete(final T entity) throws DatabaseException;
}
