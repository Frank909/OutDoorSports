package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.Partecipante;

public interface Partecipante_DAO extends Utente_DAO<Partecipante>{
	Partecipante readByCodiceFiscale(String codiceFiscale) throws DatabaseException;
	Partecipante readByUsername(String username) throws DatabaseException;
	List<Partecipante> getAllPartecipante() throws DatabaseException;

}
