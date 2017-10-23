package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Ruoli_DAO;
import outdoorapp.to.Ruoli;

/** 
 * Data Access Object per tutte le operazioni CRUD per Ruoli.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class RuoliDAO extends GenericDAO<Ruoli> implements Ruoli_DAO{

	/**
	 * Il costruttore inizializza l'entità Ruoli da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public RuoliDAO() {
		this.setCurrentClass(new Ruoli());
	}
	
	/**
	 * @return il ruolo di Manager di Sistema
	 * @throws DatabaseException
	 */
	@Override
	public Ruoli getRuoloManagerDiSistema() throws DatabaseException {
		return this.findOne(0);
	}
	
	/**
	 * @return il ruolo di Manager di Escursione
	 * @throws DatabaseException
	 */
	@Override
	public Ruoli getRuoloManagerDiEscursione() throws DatabaseException {
		return this.findOne(1);
	}
	
	/**
	 * @return il ruolo di Partecipante
	 * @throws DatabaseException
	 */
	@Override
	public Ruoli getRuoloPartecipante() throws DatabaseException {
		return this.findOne(2);
	}


}
