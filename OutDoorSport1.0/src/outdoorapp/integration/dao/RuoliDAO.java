package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.interfaces.RuoliTO;
import outdoorapp.to.interfaces.TOFactory;

/** 
 * Classe che implmenta i Data Access Object per 
 * tutte le operazioni CRUD per Ruoli.
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
	
	@Override
	public RuoliTO getRuoloManagerDiSistema() throws DatabaseException {
		return this.findOne(1);
	}
	
	@Override
	public RuoliTO getRuoloManagerDiEscursione() throws DatabaseException {
		return this.findOne(2);
	}
	
	@Override
	public RuoliTO getRuoloPartecipante() throws DatabaseException {
		return this.findOne(3);
	}


}
