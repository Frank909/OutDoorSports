package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;

public interface MDS_DAO extends Utente_DAO<ManagerDiSistemaTO> {
	public boolean verificaManagerDiSistema() throws DatabaseException;
}
