package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

public interface Escursione_DAO extends GEN_DAO<EscursioneTO>{
	EscursioneTO readById(Integer id) throws DatabaseException;
	List<EscursioneTO> readEscursioniAttive() throws DatabaseException;
	List<EscursioneTO> readEscursioniAperte() throws DatabaseException;
	EscursioneTO annullaEscursione(EscursioneTO escursione) throws DatabaseException;
	List<EscursioneTO> readEscursioniByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException;
	List<EscursioneTO> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException;
}
