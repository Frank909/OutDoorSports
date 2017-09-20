package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.ManagerDiEscursione;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Manager di Escursione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ManagerDiEscursioneDAO extends UtenteDAO<ManagerDiEscursione>{

	/**
	 * Il costruttore inizializza l'entità Manager di Escursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public ManagerDiEscursioneDAO() {
		this.setCurrentClass(new ManagerDiEscursione());
	}
	
	public List<ManagerDiEscursione> getAll() throws DatabaseException{
		List<ManagerDiEscursione> response = super.getAll();
		return response;
	}
	
	/**
	 * @return la lista di tutti i Manager di Escursione
	 * @throws DatabaseException
	 */
	public List<ManagerDiEscursione> getAllManagerDiEscursione() throws DatabaseException {
		return super.executeQuery("booooooooooooohhhh");
	}
}
