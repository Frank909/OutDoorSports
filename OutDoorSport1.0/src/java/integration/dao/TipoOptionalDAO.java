package java.integration.dao;

import java.to.TipoOptional;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoOptional.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class TipoOptionalDAO extends GenericDAO<TipoOptional>{

	/**
	 * Il costruttore inizializza l'entit� TipoOptional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public TipoOptionalDAO() {
		this.setCurrentClass(new TipoOptional());
	}

}
