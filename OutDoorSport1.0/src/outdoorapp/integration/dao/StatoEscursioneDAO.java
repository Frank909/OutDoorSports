package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;

import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoCompetizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoEscursioneDAO extends GenericDAO<StatoEscursioneTO> implements StatoEscursione_DAO{

	/**
	 * Il costruttore inizializza l'entità StatoEscursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoEscursioneDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
		this.setCurrentClass(tofact.getStatoTO(StatoEnum.StatoEscursione));
	}
	
	/**
	 * @return lo stato annullata dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursioneTO getStatoEscursioneAnnullata() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("boooooooooooh");
		return statoEscursione.get(0);
	}
	
	/**
	 * @return lo stato aperto alle iscrizioni dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursioneTO getStatoEscursioneAperta() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("booooooooooh");
		return statoEscursione.get(0);
	}
	
	/**
	 * @return lo stato in corso della escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursioneTO getStatoEscursioneInCorso() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("booooooooooh");
		return statoEscursione.get(0);
	}

	/**
	 * @return lo stato chiuso alle iscrizioni della escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursioneTO getStatoEscursioneChiusa() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("boooooooooooooh");
		return statoEscursione.get(0);
	}

	/**
	 * @return lo stato terminata dell'escursione
	 * @throws DatabaseException
	 */
	@Override
	public StatoEscursioneTO getStatoEscursioneTerminata() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("booooooooooooooh");
		return statoEscursione.get(0);
	}
}
