package outdoorapp.integration.dao;

import outdoorapp.integration.dao.interfaces.TipoOptional_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoOptionalTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;

/** 
 * Data Access Object per tutte le operazioni CRUD per TipoOptional.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoOptionalDAO extends GenericDAO<TipoOptionalTO> implements TipoOptional_DAO{

	/**
	 * Il costruttore inizializza l'entità TipoOptional da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public TipoOptionalDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.TipoTOFactory);
		this.setCurrentClass(tofact.getTipoTO(TipoEnum.TipoOptional));
	}

}
