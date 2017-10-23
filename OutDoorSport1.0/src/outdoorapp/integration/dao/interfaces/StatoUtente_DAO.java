package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.StatoUtente;

public interface StatoUtente_DAO extends GEN_DAO<StatoUtente> {
	StatoUtente getStatoDisattivo() throws DatabaseException;
	StatoUtente getStatoAttivo() throws DatabaseException;

}
