package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.to.StatoEscursione;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoCompetizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoEscursioneDAO extends GenericDAO<StatoEscursione> implements StatoEscursione_DAO{

	/**
	 * Il costruttore inizializza l'entità StatoEscursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoEscursioneDAO() {
		this.setCurrentClass(new StatoEscursione());
	}
	
	/**
	 * @return lo stato annullata dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursione getStatoEscursioneAnnullata() throws DatabaseException {
		List<StatoEscursione> statoEscursione = super.executeQuery("boooooooooooh");
		return statoEscursione.get(0);
	}
	
	/**
	 * @return lo stato aperto alle iscrizioni dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursione getStatoEscursioneAperta() throws DatabaseException {
		List<StatoEscursione> statoEscursione = super.executeQuery("booooooooooh");
		return statoEscursione.get(0);
	}
	
	/**
	 * @return lo stato in corso della escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursione getStatoEscursioneInCorso() throws DatabaseException {
		List<StatoEscursione> statoEscursione = super.executeQuery("booooooooooh");
		return statoEscursione.get(0);
	}

	/**
	 * @return lo stato chiuso alle iscrizioni della escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursione getStatoEscursioneChiusa() throws DatabaseException {
		List<StatoEscursione> statoEscursione = super.executeQuery("boooooooooooooh");
		return statoEscursione.get(0);
	}

	/**
	 * @return lo stato terminata dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursione getStatoEscursioneTerminata() throws DatabaseException {
		List<StatoEscursione> statoEscursione = super.executeQuery("booooooooooooooh");
		return statoEscursione.get(0);
	}
}
