package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.ManagerDiSistema;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Manager di Sistema.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ManagerDiSistemaDAO extends UtenteDAO<ManagerDiSistema>{

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
	public boolean verificaManagerDiSistema() throws DatabaseException {
		boolean response;
		List<ManagerDiSistema> list = super.executeQuery("BOOOOOOOOOOOOOOOOOh");
		if(list.size() != 0)
			response = true;
		else
			response = false;
		return response;
	}


}
