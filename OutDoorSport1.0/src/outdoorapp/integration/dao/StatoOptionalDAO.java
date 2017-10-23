package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoOptional_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoOptional.
 * Sono presenti i metodi di lettura e modifica.
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
	
	/**
	 * @return stato disattivo dell'optional
	 * @throws DatabaseException
	 */
	@Override
	public StatoOptionalTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return stato attivo dell'optional
	 * @throws DatabaseException
	 */
	@Override
	public StatoOptionalTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
