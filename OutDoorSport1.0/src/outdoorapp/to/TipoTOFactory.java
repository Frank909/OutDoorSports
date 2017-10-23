package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

public class TipoTOFactory implements TOFactory{

	public TipoTOFactory() {
		// TODO Auto-generated constructor stub
	}

	public OutDoorSports getTipoTO(OutDoorSports choice){
		if(choice instanceof TipoOptional)
			return new TipoOptional();
		if(choice instanceof Ruoli)
			return new Ruoli();
		if(choice instanceof TipoEscursione)
			return new TipoEscursione();
		return null;
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
		switch (choice) {
		case TipoEscursione:
			return new TipoEscursione();
		case TipoOptional:
			return new TipoOptional();
		default:
			return null;
		}
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
