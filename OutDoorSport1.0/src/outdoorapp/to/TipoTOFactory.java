package outdoorapp.to;

/**
 * Classe che restituisce l'istanza dell'entità desiderata
 * della categoria Tipo in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.OutDoorSports;

class TipoTOFactory implements TOFactory{

	/**
	 *  Costruttore di TipoTOFactory
	 */
	public TipoTOFactory() {}

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
		switch (choice) {
		case TipoEscursione:
			return new TipoEscursione();
		case TipoOptional:
			return new TipoOptional();
		case Ruoli:
			return new Ruoli();
		default:
			return null;
		}
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
