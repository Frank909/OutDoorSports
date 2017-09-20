package java.integration.dao;

import java.exceptions.DatabaseException;
import java.to.Escursione;
import java.to.ManagerDiEscursione;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object per tutte le operazioni CRUD per le Escursioni. Sono presenti tutti
 * i metodi di scrittura e lettura delle Escursioni, e anche i filtri per trovare quelle attive,
 * quelle gestite da un particolare Manager di Escursione, e quelle di un tipo specifico.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class EscursioneDAO extends GenericDAO<Escursione>{

	/**
	 * Il costruttore inizializza l'entità Escursione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public EscursioneDAO() {
		this.setCurrentClass(new Escursione());
	}
	
	/**
	 * @param id
	 * @return l'entità con quel determinato id
	 * @throws DatabaseException
	 */
	public Escursione readById(Integer id) throws DatabaseException{
		Escursione response = super.findOne(id);
		return response;
	}
	
	/**
	 * @return le Escursioni attivate dal Manager di Escursione
	 * @throws DatabaseException
	 */
	public List<Escursione> readEscursioniAttive() throws DatabaseException { 
		return super.executeQuery("BOOOOOHHHH");
	}
	
	/**
	 * @return le Escursioni aperte alle iscrizioni
	 * @throws DatabaseException
	 */
	public List<Escursione> readEscursioniAperte()
			throws DatabaseException {
		return super.executeQuery("BOOOOOHHHH");
	}
	
	/**
	 * 
	 * @param escursione
	 * @return restituisce le Escursione escluso quella annullata
	 * @throws DatabaseException
	 */
	public Escursione annullaEscursione(Escursione escursione)
			throws DatabaseException {
		return super.update(escursione);	
	}

	/**
	 * @param mde
	 * @return le Escursioni di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	public List<Escursione> readEscursioniByManagerDiEscursione(ManagerDiEscursione mde) throws DatabaseException {
		List<ManagerDiEscursione> param = new ArrayList<ManagerDiEscursione>();
		param.add(mde);
		List<Escursione> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
	
	/**
	 * @param mde
	 * @return le Escursioni attive di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	public List<Escursione> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursione mde) throws DatabaseException {
		List<ManagerDiEscursione> param = new ArrayList<ManagerDiEscursione>();
		param.add(mde);
		List<Escursione> response = super.executeParamQuery("BOOOOOOOHHHH", param);
		return response;
	}
}
