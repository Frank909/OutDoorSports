package java.integration.dao;

import java.to.TipoEscursione;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoEscursione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class TipoEscursioneDAO extends GenericDAO<TipoEscursione>{

	/**
	 * Il costruttore inizializza l'entità TipoEscursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public TipoEscursioneDAO() {
		this.setCurrentClass(new TipoEscursione());
	}

}
