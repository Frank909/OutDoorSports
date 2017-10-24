package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria TipoDAO in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoDAOFactory implements DAOFactory{

	@Override
	public GenericDAO<?> getTipoDAO(TipoDAOEnum choice){
		switch(choice){
		case Escursione:
			return new TipoEscursioneDAO();
		case Optional:
			return new TipoOptionalDAO();
		default:
			return new RuoliDAO();
		}
	}

	@Override
	public UtenteDAO<?> getUtenteDAO(UtenteDAOEnum choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getGenericDAO(GenericDAOEnum choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getStatoDAO(StatoDAOEnum choice) {
		return null;
	}

}
