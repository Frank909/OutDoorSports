package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoIscrizione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoIscrizione.
 * Sono presenti i metodi di lettura e modifica.
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
	
	/**
	 * @return lo stato attivo di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	/**
	 * @return lo stato disattivo di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizioneTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

	/**
	 * @return lo stato terminato di una iscrizione
	 * @throws DatabaseException
	 */
	@Override
	public StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}
