package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.PartecipanteTO;

public interface Partecipante_DAO extends Utente_DAO<PartecipanteTO>{
	PartecipanteTO readByCodiceFiscale(String codiceFiscale) throws DatabaseException;
	PartecipanteTO readByUsername(String username) throws DatabaseException;
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;

}
