package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Optional_DAO;
import outdoorapp.to.Optional;
import outdoorapp.to.TipoOptional;
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

class OptionalDAO extends GenericDAO<Optional> implements Optional_DAO{

	/**
	 * Il costruttore inizializza l'entità Optional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public OptionalDAO() {
		this.setCurrentClass(new Optional());
	}
	
	/**
	 * @param optional
	 * @return un Optional modificato in disattivato
	 * @throws DatabaseException
	 */
	@Override
	public Optional disattivaOptional(Optional optional) throws DatabaseException {
		return super.update(optional);
	}
	
	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo attivi
	 * @throws DatabaseException
	 */
	@Override
	public List<Optional> getOptionalAttiviByTipo(TipoOptional tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<Optional> response = super.executeParamQuery("boooooooooooooooooooh", param);
		return response;
	}

	/**
	 * @param tipoOptional
	 * @return la lista degli optional di un determinato tipo
	 * @throws DatabaseException
	 */	
	@Override
	public List<Optional> getOptionalByTipo(TipoOptional tipoOptional) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tipoOptional.getNome());
		List<Optional> response = super.executeParamQuery("gbooooooooooooooooooooh", param);
		return response;
	}
}
