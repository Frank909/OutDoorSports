package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.DAORequest;

public class FactoryProducer {
	public static AbstractFactoryDAO getFactory(DAORequest choice){
		switch(choice){
		case Users:
			return new UtenteDAOFactory();
		case State:
			return new StatoDAOFactory();
		case Type: 
			return new TipoDAOFactory();
		default:
			return new GenericDAOFactory();
		}
	}
}
