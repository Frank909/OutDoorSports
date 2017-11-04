package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

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

	private EscursioneTO escursione = null;
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
		List<Integer> param = new ArrayList<Integer>();
		param.add(mde.getIdUtente());
		List<EscursioneTO> response = super.executeParamQuery("readEscursioniByManagerDiEscursione", param);
		return response;
	}
	
	@Override
	public List<EscursioneTO> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException {
		List<ManagerDiEscursioneTO> param = new ArrayList<ManagerDiEscursioneTO>();
		param.add(mde);
		List<EscursioneTO> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
	
	@Override
	public boolean esisteEscursione(EscursioneTO escursione) throws DatabaseException{
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(escursione.getNome());
		EscursioneTO newEscursione = (EscursioneTO) this.getEscursioneByQuery("getEscursioneByName", param);
		response = !this.isNullEscursione(newEscursione);
		return response;
	}
	
	/**
	 * @param escursione
	 * @return vero se è una escursione è nulla, falso altrimenti
	 */
	private boolean isNullEscursione(EscursioneTO escursione){
		return escursione == null;
	}

	/**
	 * @param queryName
	 * @param params
	 * @return un'istanza di Escursione tramite query
	 * @throws DatabaseException
	 */
	@SuppressWarnings({ "unchecked" })
	private EscursioneTO getEscursioneByQuery(String queryName, List<?> params) throws DatabaseException{
		EscursioneTO response = null;
		List<EscursioneTO> list = super.executeParamQuery(queryName, params);
		if(list.size() == 0){
			response = escursione;
		} else {
			response = (EscursioneTO)list.get(0);
		}
		return response;
	}
}
