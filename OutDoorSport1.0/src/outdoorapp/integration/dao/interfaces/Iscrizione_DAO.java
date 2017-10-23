package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;

public interface Iscrizione_DAO extends GEN_DAO<IscrizioneTO> {
	IscrizioneTO annullaIscrizione(IscrizioneTO iscrizione) throws DatabaseException;
	boolean esisteIscrizione(IscrizioneTO iscrizione) throws DatabaseException;
	List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO partecipante) throws DatabaseException;
	void terminaIscrizioni(EscursioneTO escursione) throws DatabaseException;
	List<IscrizioneTO> getIscrizioniAttiveEscursione(EscursioneTO escursione) throws DatabaseException;
	List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO partecipante) throws DatabaseException;
}
