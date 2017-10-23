package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Optional_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoOptionalTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;

import java.util.ArrayList;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Optional.
 * Sono presenti i metodi di lettura e modifica.
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
	
	/**
	 * @param optional
	 * @return un Optional modificato in disattivato
	 * @throws DatabaseException
	 */
	@Override
	public OptionalTO disattivaOptional(OptionalTO optional) throws DatabaseException {
		return super.update(optional);
	}
	
	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo attivi
	 * @throws DatabaseException
	 */
	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<OptionalTO> response = super.executeParamQuery("boooooooooooooooooooh", param);
		return response;
	}

	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo
	 * @throws DatabaseException
	 */	
	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<OptionalTO> response = super.executeParamQuery("gbooooooooooooooooooooh", param);
		return response;
	}
}
