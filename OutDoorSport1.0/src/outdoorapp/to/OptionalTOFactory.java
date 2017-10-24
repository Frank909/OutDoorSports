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
 * della categoria Optional in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class OptionalTOFactory implements TOFactory{

	/**
	 * Costruttore di OriginalTOFactory
	 */
	public OptionalTOFactory() {}

	@Override
	public OutDoorSports getGenericTO(GenericEnum choice) {
		return null;
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
		switch (choice) {
		case Optional:
			return new Optional();
		case OptionalEscursione:
			return new OptionalEscursione();
		case OptionalIscrizione:
			return new OptionalIscrizione();
		default:
			return null;
		}
	}

	@Override
	public OutDoorSports getStatoTO(StatoEnum choice) {
		return null;
	}

}
