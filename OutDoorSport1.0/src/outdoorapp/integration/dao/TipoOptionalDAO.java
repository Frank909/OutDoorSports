package outdoorapp.integration.dao;

import outdoorapp.integration.dao.interfaces.TipoOptional_DAO;
import outdoorapp.to.TipoOptional;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoOptional.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoOptionalDAO extends GenericDAO<TipoOptional> implements TipoOptional_DAO{

	/**
	 * Il costruttore inizializza l'entità TipoOptional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public TipoOptionalDAO() {
		this.setCurrentClass(new TipoOptional());
	}

}
