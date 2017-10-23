package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.Escursione;
import outdoorapp.to.Iscrizione;
import outdoorapp.to.Partecipante;

public interface Iscrizione_DAO extends GEN_DAO<Iscrizione> {
	Iscrizione annullaIscrizione(Iscrizione iscrizione) throws DatabaseException;
	boolean esisteIscrizione(Iscrizione iscrizione) throws DatabaseException;
	List<Iscrizione> getAllIscrizioniAttive(Partecipante partecipante) throws DatabaseException;
	void terminaIscrizioni(Escursione escursione) throws DatabaseException;
	List<Iscrizione> getIscrizioniAttiveEscursione(Escursione escursione) throws DatabaseException;
	List<Iscrizione> getAllIscrizioniPartecipante(Partecipante partecipante) throws DatabaseException;
}
