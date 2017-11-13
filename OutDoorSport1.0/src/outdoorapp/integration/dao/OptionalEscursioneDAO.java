package outdoorapp.integration.dao;

import java.util.ArrayList;
import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.OptionalEscursione_DAO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per gli Optional collegati
 * alle escursioni.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class OptionalEscursioneDAO extends GenericDAO<OptionalEscursioneTO> implements OptionalEscursione_DAO{

	public OptionalEscursioneDAO() {}

	@Override
	public List<OptionalEscursioneTO> getOptionalsFromEscursione(int idEscursione) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(idEscursione);
		List<OptionalEscursioneTO> list = super.executeParamQuery("getOptionalsFromEscursione", param);
		return list;
	}

	@Override
	public List<OptionalEscursioneTO> getAssociationID(EscursioneTO escursione, OptionalTO optional) throws DatabaseException {
		List<Integer> param = new ArrayList<>();
		param.add(escursione.getIdEscursione());
		param.add(optional.getIdOptional());
		List<OptionalEscursioneTO> list = super.executeParamQuery("getAssociationID", param);
		return list;
	}

}
