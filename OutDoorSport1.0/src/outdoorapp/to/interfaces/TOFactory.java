package outdoorapp.to.interfaces;

import outdoorapp.to.interfaces.strings.GenericEnum;
import outdoorapp.to.interfaces.strings.OptionalEnum;
import outdoorapp.to.interfaces.strings.StatoEnum;
import outdoorapp.to.interfaces.strings.TipoEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

public interface TOFactory {
	public OutDoorSports getGenericTO(GenericEnum choice);
	public OutDoorSports getUtenteTO(UtenteEnum choice);
	public OutDoorSports getTipoTO(TipoEnum choice);
	public OutDoorSports getOptionalTO(OptionalEnum choice);
	public OutDoorSports getStatoTO(StatoEnum choice);
}
