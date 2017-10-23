package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.Generic;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;

public class TipoDAOFactory implements AbstractFactoryDAO{

	@Override
	public GenericDAO<?> getTipoDAO(Type choice){
		switch(choice){
		case Escursione:
			return new TipoEscursioneDAO();
		case Optional:
			return new TipoOptionalDAO();
		default:
			return new RuoliDAO();
		}
	}

	@Override
	public UtenteDAO<?> getUtenteDAO(Users choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getGenericDAO(Generic choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getStatoDAO(State choice) {
		return null;
	}

}
