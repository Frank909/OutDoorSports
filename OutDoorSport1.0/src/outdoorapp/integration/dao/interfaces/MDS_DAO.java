package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.ManagerDiSistema;

public interface MDS_DAO extends Utente_DAO<ManagerDiSistema> {
	public boolean verificaManagerDiSistema() throws DatabaseException;
}
