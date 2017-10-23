package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.Generic;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;

public interface AbstractFactoryDAO{
	GenericDAO<?> getGenericDAO(Generic choice);
	GenericDAO<?> getTipoDAO(Type choice);
	GenericDAO<?> getStatoDAO(State choice);
	UtenteDAO<?> getUtenteDAO(Users choice);
}
