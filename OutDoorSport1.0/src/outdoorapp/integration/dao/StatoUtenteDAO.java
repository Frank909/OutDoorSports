package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.interfaces.StatoUtenteTO;
import outdoorapp.to.interfaces.TOFactory;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per StatoUtente.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoUtenteDAO extends GenericDAO<StatoUtenteTO> implements StatoUtente_DAO{

	/**
	 * Il costruttore inizializza l'entità StatoUtente da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public StatoUtenteDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.StatoTOFactory);
		this.setCurrentClass(tofact.getStatoTO(StatoEnum.StatoUtente));
	}


	@Override
	public StatoUtenteTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	@Override
	public StatoUtenteTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
