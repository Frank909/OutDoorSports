package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.Generic;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;

public class GenericDAOFactory implements DAOFactory{

	@Override
	public GenericDAO<?> getGenericDAO(Generic choice){
		switch(choice){
		case Escursione:
			return new EscursioneDAO();
		case Iscrizione:
			return new IscrizioneDAO();
		default:
			return new OptionalDAO();
		}
	}

	@Override
	public UtenteDAO<?> getUtenteDAO(Users choice) {
		return null;
	}


	@Override
	public GenericDAO<?> getTipoDAO(Type choice) {
		return null;
	}

	@Override
	public GenericDAO<?> getStatoDAO(State choice) {
		return null;
	}
	
}
