package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.MDE_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Manager di Escursione.
 * Sono presenti i metodi di lettura e modifica.
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
	
	/**
	 * @return la lista di tutti i Manager di Escursione
	 * @throws DatabaseException
	 *//*
	public List<ManagerDiEscursione> getAllManagerDiEscursione() throws DatabaseException {
		return super.executeQuery("booooooooooooohhhh");
	}*/
}
