package outdoorapp.integration.dao.interfaces;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.Ruoli;

public interface Ruoli_DAO extends GEN_DAO<Ruoli> {
	Ruoli getRuoloManagerDiSistema() throws DatabaseException;
	Ruoli getRuoloManagerDiEscursione() throws DatabaseException;
	Ruoli getRuoloPartecipante() throws DatabaseException;
}
