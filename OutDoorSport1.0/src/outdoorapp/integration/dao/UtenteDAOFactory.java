package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria UtenteDAO in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class UtenteDAOFactory implements DAOFactory{

	public UtenteDAO<? extends UtenteTO> getUtenteDAO(UtenteDAOEnum choice){
		switch(choice){
		case ManagerDiSistema:
			return new ManagerDiSistemaDAO();
		case ManagerDiEscursione:
			return new ManagerDiEscursioneDAO();
		case Partecipante:
			return new PartecipanteDAO();
		default:
			return new UtenteDAO<>();
		}
	}

	@Override
	public GenericDAO<?> getGenericDAO(GenericDAOEnum choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getTipoDAO(TipoDAOEnum choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getStatoDAO(StatoDAOEnum choice) {
		return null;
	}



}
