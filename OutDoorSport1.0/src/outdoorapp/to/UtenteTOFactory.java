package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

public class UtenteTOFactory implements TOFactory{

	public UtenteTOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public OutDoorSports getGenericTO(GenericEnum choice) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDoorSports getOptionalTO(OptionalEnum choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDoorSports getStatoTO(StatoEnum choice) {
		// TODO Auto-generated method stub
		return null;
	}


}
