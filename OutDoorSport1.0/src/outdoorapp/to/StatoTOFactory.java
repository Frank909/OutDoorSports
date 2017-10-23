package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

public class StatoTOFactory implements TOFactory{

	public StatoTOFactory(){
		// TODO Auto-generated constructor stub
	}
	
	public OutDoorSports getStatoTO(OutDoorSports choice){
		if(choice instanceof StatoUtente)
			return new StatoUtente();
		if(choice instanceof StatoOptional)
			return new StatoOptional();
		if(choice instanceof StatoIscrizione)
			return new StatoIscrizione();
		if(choice instanceof StatoEscursione)
			return new StatoEscursione();
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
