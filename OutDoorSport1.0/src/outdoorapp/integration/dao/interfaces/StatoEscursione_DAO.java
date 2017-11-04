package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoEscursioneTO;

/** 
 * Interfaccia che rappresenta i Data Access Object per 
 * tutte le operazioni CRUD per StatoCompetizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoEscursione_DAO extends GEN_DAO<StatoEscursioneTO> {
	
	/**
	 * @return lo stato annullata dell'escursione
	 * @throws DatabaseException
	 */
	StatoEscursioneTO getStatoEscursioneAnnullata() throws DatabaseException;
	
	/**
	 * @return lo stato aperto alle iscrizioni dell'escursione
	 * @throws DatabaseException
	 */
	StatoEscursioneTO getStatoEscursioneAperta() throws DatabaseException;
	
	/**
	 * @return lo stato chiuso alle iscrizioni della escursione
	 * @throws DatabaseException
	 */
	StatoEscursioneTO getStatoEscursioneChiusa() throws DatabaseException;
	
	/**
	 * @return lo stato in corso alle iscrizioni della escursione
	 * @throws DatabaseException
	 */
	StatoEscursioneTO getStatoEscursioneInCorso() throws DatabaseException;
}
