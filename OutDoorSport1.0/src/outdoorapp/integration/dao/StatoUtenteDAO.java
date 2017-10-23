package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.StatoUtente_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.StatoUtenteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per StatoUtente.
 * Sono presenti i metodi di lettura e modifica.
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

	/**
	 * @return stato disattivo dell'utente
	 * @throws DatabaseException
	 */
	@Override
	public StatoUtenteTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return stato attivo dell'utente
	 * @throws DatabaseException
	 */
	@Override
	public StatoUtenteTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}
}
