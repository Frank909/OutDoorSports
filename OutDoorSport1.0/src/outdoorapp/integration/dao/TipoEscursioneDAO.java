package outdoorapp.integration.dao;

import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoEscursione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoEscursioneDAO extends GenericDAO<TipoEscursioneTO> implements TipoEscursione_DAO{

	/**
	 * Il costruttore inizializza l'entità TipoEscursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	private TOFactory TOFactory = null;
	
	public TipoEscursioneDAO() {
		if(TOFactory == null)
			TOFactory = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
		
		this.setCurrentClass(TOFactory.getTipoTO(TipoEnum.TipoEscursione));
	}

}
