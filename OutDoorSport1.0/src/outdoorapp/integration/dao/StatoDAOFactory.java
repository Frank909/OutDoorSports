package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.Generic;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;

public class StatoDAOFactory implements AbstractFactoryDAO{

	@Override
	public GenericDAO<?> getStatoDAO(State choice){
		
		switch(choice){
		case Escursione:
			return new StatoEscursioneDAO();
		case Iscrizione:
			return new StatoIscrizioneDAO();
		case Optional:
			return new StatoOptionalDAO();
		default:
			return new StatoUtenteDAO();
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
	public GenericDAO<?> getTipoDAO(Type choice) {
		return null;
	}

}
