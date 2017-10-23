package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.RuoliTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per Ruoli.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class RuoliDAO extends GenericDAO<RuoliTO> implements Ruoli_DAO{

	/**
	 * Il costruttore inizializza l'entità Ruoli da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public RuoliDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
		this.setCurrentClass(tofact.getTipoTO(TipoEnum.Ruoli));
	}
	
	/**
	 * @return il ruolo di Manager di Sistema
	 * @throws DatabaseException
	 */
	@Override
	public RuoliTO getRuoloManagerDiSistema() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return il ruolo di Manager di Escursione
	 * @throws DatabaseException
	 */
	@Override
	public RuoliTO getRuoloManagerDiEscursione() throws DatabaseException {
		return this.findOne(1);
	}
	
	/**
	 * @return il ruolo di Partecipante
	 * @throws DatabaseException
	 */
	@Override
	public RuoliTO getRuoloPartecipante() throws DatabaseException {
		return this.findOne(2);
	}


}
