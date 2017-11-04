package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;

import java.util.List;

/** 
 * Classe che implementa i Data Access Object per tutte le 
 * operazioni CRUD per StatoCompetizione.
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
	
	@Override
	public StatoEscursioneTO getStatoEscursioneAnnullata() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("getStatoEscursioneAnnullata");
		return statoEscursione.get(0);
	}
	
	
	@Override
	public StatoEscursioneTO getStatoEscursioneAperta() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("getStatoEscursioneAperta");
		return statoEscursione.get(0);
	}

	@Override
	public StatoEscursioneTO getStatoEscursioneChiusa() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("getStatoEscursioneChiusa");
		return statoEscursione.get(0);
	}

	@Override
	public StatoEscursioneTO getStatoEscursioneInCorso() throws DatabaseException {
		List<StatoEscursioneTO> statoEscursione = super.executeQuery("getStatoEscursioneInCorso");
		return statoEscursione.get(0);
	}
}
