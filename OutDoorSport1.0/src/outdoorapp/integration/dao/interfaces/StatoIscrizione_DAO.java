package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.StatoIscrizione;

public interface StatoIscrizione_DAO extends GEN_DAO<StatoIscrizione> {
	StatoIscrizione getStatoAttivo() throws DatabaseException;
	StatoIscrizione getStatoDisattivo() throws DatabaseException;
	StatoIscrizione getStatoIscrizioneTerminato() throws DatabaseException;

}
