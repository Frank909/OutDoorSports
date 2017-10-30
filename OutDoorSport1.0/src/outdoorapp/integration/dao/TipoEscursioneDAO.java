package outdoorapp.integration.dao;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.TipoEscursione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per TipoEscursione.
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
	
	/**
	 * Costruttore che inizializza il Transfer Object relativo al TipoEscursione
	 */
	public TipoEscursioneDAO() {
		if(TOFactory == null)
			TOFactory = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
		
		this.setCurrentClass(TOFactory.getTipoTO(TipoEnum.TipoEscursione));
	}

	@Override
	public List<TipoEscursioneTO> getAllTipiEscursione() throws DatabaseException {
		List<TipoEscursioneTO> list_tipi_escursione = super.executeQuery("getAllTipiEscursione");
		return list_tipi_escursione;
	}

}
