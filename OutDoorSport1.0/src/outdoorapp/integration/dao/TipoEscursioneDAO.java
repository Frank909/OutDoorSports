package outdoorapp.integration.dao;

import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.to.TipoEscursione;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoEscursione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoEscursioneDAO extends GenericDAO<TipoEscursione> implements TipoEscursione_DAO{

	/**
	 * Il costruttore inizializza l'entità TipoEscursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public TipoEscursioneDAO() {
		this.setCurrentClass(new TipoEscursione());
	}

}
