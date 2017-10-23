package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.StatoEscursioneTO;

public interface StatoEscursione_DAO extends GEN_DAO<StatoEscursioneTO> {
	StatoEscursioneTO getStatoEscursioneAnnullata() throws DatabaseException;
	StatoEscursioneTO getStatoEscursioneAperta() throws DatabaseException;
	StatoEscursioneTO getStatoEscursioneChiusa() throws DatabaseException;
	StatoEscursioneTO getStatoEscursioneInCorso() throws DatabaseException;
	StatoEscursioneTO getStatoEscursioneTerminata() throws DatabaseException;
}
