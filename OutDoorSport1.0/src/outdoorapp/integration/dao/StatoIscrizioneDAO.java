package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoIscrizione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.TOFactory;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per StatoIscrizione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoIscrizioneDAO extends GenericDAO<StatoIscrizioneTO> implements StatoIscrizione_DAO{

	/**
	 * Il costruttore inizializza l'entità StatoIscrizione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoIscrizioneDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
		this.setCurrentClass(tofact.getStatoTO(StatoEnum.StatoIscrizione));
	}
	
	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	@Override
	public StatoIscrizioneTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

	@Override
	public StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}
