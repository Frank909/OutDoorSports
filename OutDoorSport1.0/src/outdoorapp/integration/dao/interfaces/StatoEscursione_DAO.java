package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.StatoEscursione;

public interface StatoEscursione_DAO extends GEN_DAO<StatoEscursione> {
	StatoEscursione getStatoEscursioneAnnullata() throws DatabaseException;
	StatoEscursione getStatoEscursioneAperta() throws DatabaseException;
	StatoEscursione getStatoEscursioneChiusa() throws DatabaseException;
	StatoEscursione getStatoEscursioneInCorso() throws DatabaseException;
	StatoEscursione getStatoEscursioneTerminata() throws DatabaseException;
}
