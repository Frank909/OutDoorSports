package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoIscrizioneTO;

public interface StatoIscrizione_DAO extends GEN_DAO<StatoIscrizioneTO> {
	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
	StatoIscrizioneTO getStatoDisattivo() throws DatabaseException;
	StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException;

}
