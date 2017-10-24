package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoOptional_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TOFactory;

/** 
 * Classe che implementa i Data Access Object per tutte 
 * le operazioni CRUD per StatoOptional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoOptionalDAO extends GenericDAO<StatoOptionalTO> implements StatoOptional_DAO{

	/**
	 * Il costruttore inizializza l'entità StatoOptional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoOptionalDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
		this.setCurrentClass(tofact.getStatoTO(StatoEnum.StatoOptional));
	}
	
	@Override
	public StatoOptionalTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	@Override
	public StatoOptionalTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
