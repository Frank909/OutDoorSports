package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.MDS_DAO;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.utils.Actions;

import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Manager di Sistema.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ManagerDiSistemaDAO extends UtenteDAO<ManagerDiSistema> implements Actions, MDS_DAO{

	/**
	 * Il costruttore inizializza l'entit� Manager di Sistema da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public ManagerDiSistemaDAO() {
		this.setCurrentClass(new ManagerDiSistema());
	}
	
	/**
	 * @return vero se esiste il manager di sistema, falso altrimenti
	 * @throws DatabaseException
	 */
	@Override
	public boolean verificaManagerDiSistema() throws DatabaseException {
		boolean response;
		List<ManagerDiSistema> list = super.executeQuery("getAllMDS");
		if(list.size() != 0)
			response = true;
		else
			response = false;
		return response;
	}

}
