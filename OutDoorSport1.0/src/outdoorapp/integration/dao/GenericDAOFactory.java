package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria GenericDAO in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class GenericDAOFactory implements DAOFactory{

	@Override
	public GenericDAO<?> getGenericDAO(GenericDAOEnum choice){
		switch(choice){
		case Escursione:
			return new EscursioneDAO();
		case Iscrizione:
			return new IscrizioneDAO();
		case OptionalEscursione:
			return new OptionalEscursioneDAO();
		case OptionalIscrizione:
			return new OptionalIscrizioneDAO();
		default:
			return new OptionalDAO();
		}
	}

	@Override
	public UtenteDAO<?> getUtenteDAO(UtenteDAOEnum choice) {
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
