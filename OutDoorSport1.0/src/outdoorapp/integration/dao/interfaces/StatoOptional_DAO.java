package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoOptionalTO;

public interface StatoOptional_DAO extends GEN_DAO<StatoOptionalTO> {
	StatoOptionalTO getStatoDisattivo() throws DatabaseException;
	StatoOptionalTO getStatoAttivo() throws DatabaseException;

}
