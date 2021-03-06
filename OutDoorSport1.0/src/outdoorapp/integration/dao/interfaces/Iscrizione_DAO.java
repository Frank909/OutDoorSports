package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;

/** 
 * Interfaccia che rappresenta  i Data Access Object per tutte 
 * le operazioni CRUD per Iscrizione. Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Iscrizione_DAO extends GEN_DAO<IscrizioneTO> {
	
	/**
	 * @param iscrizione
	 * @return l'iscrizione cambiando lo stato in annullata
	 * @throws DatabaseException
	 */
	IscrizioneTO annullaIscrizione(IscrizioneTO iscrizione) throws DatabaseException;
	
	/**
	 * @param iscrizione
	 * @return vero se esiste l'iscrizione, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteIscrizione(IscrizioneTO iscrizione) throws DatabaseException;
	
	/**
	 * @return i partecipanti iscritti a una escursione
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getAllIscrittiFromEscursione(EscursioneTO escursione) throws DatabaseException;
	
	/**
	 * @return l'iscrizione del partecipante a una determinata escursione
	 * @throws DatabaseException
	 */
	IscrizioneTO getIscrizioneFromEscursione(EscursioneTO escursione, PartecipanteTO partecipante) throws DatabaseException;	
}
