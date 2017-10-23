package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.GenericEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object per tutte le operazioni CRUD per le Escursioni. Sono presenti tutti
 * i metodi di scrittura e lettura delle Escursioni, e anche i filtri per trovare quelle attive,
 * quelle gestite da un particolare Manager di Escursione, e quelle di un tipo specifico.
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
	
	/**
	 * @param id
	 * @return l'entità con quel determinato id
	 * @throws DatabaseException
	 */
	@Override
	public EscursioneTO readById(Integer id) throws DatabaseException{
		EscursioneTO response = super.findOne(id);
		return response;
	}
	
	/**
	 * @return le Escursioni attivate dal Manager di Escursione
	 * @throws DatabaseException
	 */
	@Override
	public List<EscursioneTO> readEscursioniAttive() throws DatabaseException { 
		return super.executeQuery("BOOOOOHHHH");
	}
	
	/**
	 * @return le Escursioni aperte alle iscrizioni
	 * @throws DatabaseException
	 */
	@Override
	public List<EscursioneTO> readEscursioniAperte() throws DatabaseException{
		return super.executeQuery("BOOOOOHHHH");
	}
	
	/**
	 * 
	 * @param escursione
	 * @return restituisce le Escursione escluso quella annullata
	 * @throws DatabaseException
	 */
	@Override
	public EscursioneTO annullaEscursione(EscursioneTO escursione)
			throws DatabaseException {
		return super.update(escursione);	
	}

	/**
	 * @param mde
	 * @return le Escursioni di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	@Override
	public List<EscursioneTO> readEscursioniByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException {
		List<ManagerDiEscursioneTO> param = new ArrayList<ManagerDiEscursioneTO>();
		param.add(mde);
		List<EscursioneTO> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
	
	/**
	 * @param mde
	 * @return le Escursioni attive di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	@Override
	public List<EscursioneTO> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException {
		List<ManagerDiEscursioneTO> param = new ArrayList<ManagerDiEscursioneTO>();
		param.add(mde);
		List<EscursioneTO> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
}
