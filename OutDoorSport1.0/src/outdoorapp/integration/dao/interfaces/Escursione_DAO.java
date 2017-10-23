package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.Escursione;
import outdoorapp.to.ManagerDiEscursione;

public interface Escursione_DAO extends GEN_DAO<Escursione>{
	Escursione readById(Integer id) throws DatabaseException;
	List<Escursione> readEscursioniAttive() throws DatabaseException;
	List<Escursione> readEscursioniAperte() throws DatabaseException;
	Escursione annullaEscursione(Escursione escursione) throws DatabaseException;
	List<Escursione> readEscursioniByManagerDiEscursione(ManagerDiEscursione mde) throws DatabaseException;
	List<Escursione> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursione mde) throws DatabaseException;
}
