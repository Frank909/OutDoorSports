package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.RuoliTO;

public interface Ruoli_DAO extends GEN_DAO<RuoliTO> {
	RuoliTO getRuoloManagerDiSistema() throws DatabaseException;
	RuoliTO getRuoloManagerDiEscursione() throws DatabaseException;
	RuoliTO getRuoloPartecipante() throws DatabaseException;
}
