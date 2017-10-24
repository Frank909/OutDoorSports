package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria StatoDAO in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoDAOFactory implements DAOFactory{

	@Override
	public GenericDAO<?> getStatoDAO(StatoDAOEnum choice){
		
		switch(choice){
		case Escursione:
			return new StatoEscursioneDAO();
		case Iscrizione:
			return new StatoIscrizioneDAO();
		case Optional:
			return new StatoOptionalDAO();
		default:
			return new StatoUtenteDAO();
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
	public GenericDAO<?> getTipoDAO(TipoDAOEnum choice) {
		return null;
	}

}
