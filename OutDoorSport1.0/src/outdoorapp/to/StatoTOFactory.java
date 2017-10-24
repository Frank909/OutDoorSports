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
 * della categoria Stato in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoTOFactory implements TOFactory{

	/**
	 * Costruttore di StatoTOFactory
	 */
	public StatoTOFactory(){}

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
		return null;
	}

	@Override
	public OutDoorSports getStatoTO(StatoEnum choice) {
		switch (choice) {
		case StatoOptional:
			return new StatoOptional();
		case StatoEscursione:
			return new StatoEscursione();
		case StatoIscrizione:
			return new StatoIscrizione();
		case StatoUtente:
			return new StatoUtente();
		default:
			return null;
		}
	}

}
