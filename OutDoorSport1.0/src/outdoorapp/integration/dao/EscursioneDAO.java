package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa le operazioni per le Escursioni.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class EscursioneDAO extends GenericDAO<EscursioneTO> implements Escursione_DAO{

	/**
	 * Il costruttore inizializza l'entità Escursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public EscursioneDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
		this.setCurrentClass(tofact.getGenericTO(GenericEnum.Escursione));
	}
	
	@Override
	public EscursioneTO readById(Integer id) throws DatabaseException{
		EscursioneTO response = super.findOne(id);
		return response;
	}
	
	@Override
	public List<EscursioneTO> readEscursioniAttive() throws DatabaseException { 
		return super.executeQuery("BOOOOOHHHH");
	}
	
	@Override
	public List<EscursioneTO> readEscursioniAperte() throws DatabaseException{
		return super.executeQuery("BOOOOOHHHH");
	}
	
	@Override
	public EscursioneTO annullaEscursione(EscursioneTO escursione)
			throws DatabaseException {
		return super.update(escursione);	
	}

	@Override
	public List<EscursioneTO> readEscursioniByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException {
		List<ManagerDiEscursioneTO> param = new ArrayList<ManagerDiEscursioneTO>();
		param.add(mde);
		List<EscursioneTO> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
	
	@Override
	public List<EscursioneTO> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException {
		List<ManagerDiEscursioneTO> param = new ArrayList<ManagerDiEscursioneTO>();
		param.add(mde);
		List<EscursioneTO> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
}
