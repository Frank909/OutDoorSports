package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Optional_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoOptionalTO;

import java.util.ArrayList;
import java.util.List;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per Optional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class OptionalDAO extends GenericDAO<OptionalTO> implements Optional_DAO{

	/**
	 * Il costruttore inizializza l'entità Optional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public OptionalDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
		this.setCurrentClass(tofact.getOptionalTO(OptionalEnum.Optional));
	}
	
	@Override
	public OptionalTO disattivaOptional(OptionalTO optional) throws DatabaseException {
		return super.update(optional);
	}
	
	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<OptionalTO> response = super.executeParamQuery("boooooooooooooooooooh", param);
		return response;
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<OptionalTO> response = super.executeParamQuery("gbooooooooooooooooooooh", param);
		return response;
	}
}
