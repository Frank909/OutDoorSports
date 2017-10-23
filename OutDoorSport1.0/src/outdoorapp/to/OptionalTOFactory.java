package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

public class OptionalTOFactory implements TOFactory{

	public OptionalTOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public OutDoorSports getGenericTO(GenericEnum choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDoorSports getUtenteTO(UtenteEnum choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDoorSports getTipoTO(TipoEnum choice) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
