package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoUtenteTO;

public interface StatoUtente_DAO extends GEN_DAO<StatoUtenteTO> {
	StatoUtenteTO getStatoDisattivo() throws DatabaseException;
	StatoUtenteTO getStatoAttivo() throws DatabaseException;

}
