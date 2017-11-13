package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;

import java.util.List;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per Manager di Escursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ManagerDiEscursioneDAO extends UtenteDAO<ManagerDiEscursioneTO> implements MDE_DAO{

	/**
	 * Il costruttore inizializza l'entità Manager di Escursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public ManagerDiEscursioneDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
		this.setCurrentClass(tofact.getUtenteTO(UtenteEnum.ManagerDiEscursione));
	}
	
	@Override
	public List<ManagerDiEscursioneTO> getAll() throws DatabaseException{
		List<ManagerDiEscursioneTO> response = super.getAll();
		return response;
	}
}
