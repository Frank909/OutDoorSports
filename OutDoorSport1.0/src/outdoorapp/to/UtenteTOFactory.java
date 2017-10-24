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
 * della categoria Utente in base alla richiesta. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class UtenteTOFactory implements TOFactory{

	/**
	 * Costruttore della classe UtenteTOFactory
	 */
	public UtenteTOFactory() {}

	@Override
	public OutDoorSports getGenericTO(GenericEnum choice) {
		return null;
	}

	@Override
	public OutDoorSports getUtenteTO(UtenteEnum choice) {
		switch (choice) {
		case Utente:
			return new Utente();
		case ManagerDiSistema:
			return new ManagerDiSistema();
		case ManagerDiEscursione:
			return new ManagerDiEscursione();
		case Partecipante:
			return new Partecipante();
		default:
			return null;
		}
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
