package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TipoEscursioneTO;

/** 
 * Interfaccia che implementa i Data Access Object per 
 * tutte le operazioni CRUD per TipoEscursione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface TipoEscursione_DAO extends GEN_DAO<TipoEscursioneTO> {

	/**
	 * @return i tipi di escursione
	 * @throws DatabaseException
	 */
	public List<TipoEscursioneTO> getAllTipiEscursione() throws DatabaseException;
}
