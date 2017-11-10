package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria Generic in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class GenericTOFactory implements TOFactory{

	/**
	 * Costruttore di GenericTOFactory
	 */
	public GenericTOFactory() {}

	@Override
	public OutDoorSports getGenericTO(GenericEnum choice) {
		switch (choice) {
		case Escursione:
			return new Escursione();
		case Iscrizione:
			return new Iscrizione();
		case Email:
			return new Email();
		case EncryptPassword:
			return new EncryptPassword();
		default:
			return null;
		}
	}

	@Override
	public OutDoorSports getUtenteTO(UtenteEnum choice) {
		return null;
	}

	@Override
	public OutDoorSports getTipoTO(TipoEnum choice) {
		return null;
	}

	@Override
	public OutDoorSports getOptionalTO(OptionalEnum choice) {
		return null;
	}

	@Override
	public OutDoorSports getStatoTO(StatoEnum choice) {
		return null;
	}
}
