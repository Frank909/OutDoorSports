package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.Generic;
import outdoorapp.integration.dao.enums.State;
import outdoorapp.integration.dao.enums.Type;
import outdoorapp.integration.dao.enums.Users;
import outdoorapp.to.Utente;

public class UtenteDAOFactory implements AbstractFactoryDAO{

	public UtenteDAO<? extends Utente> getUtenteDAO(Users choice){
		switch(choice){
		case ManagerDiSistema:
			return new ManagerDiSistemaDAO();
		case ManagerDiEscursione:
			return new ManagerDiEscursioneDAO();
		case Partecipante:
			return new PartecipanteDAO();
		default:
			return new UtenteDAO<>();
		}
	}

	@Override
	public GenericDAO<?> getGenericDAO(Generic choice) {
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
