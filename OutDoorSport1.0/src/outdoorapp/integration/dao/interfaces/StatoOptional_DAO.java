package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.StatoOptional;

public interface StatoOptional_DAO extends GEN_DAO<StatoOptional> {
	StatoOptional getStatoDisattivo() throws DatabaseException;
	StatoOptional getStatoAttivo() throws DatabaseException;

}
