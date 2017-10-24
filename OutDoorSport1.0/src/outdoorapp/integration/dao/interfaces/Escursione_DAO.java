package outdoorapp.integration.dao.interfaces;

/**
 * Interfaccia che rappresenta i Data Access Object per tutte le operazioni CRUD per le Escursioni. 
 * Sono presenti tutti i metodi delle Escursioni, e anche i filtri per 
 * trovare quelle attive, quelle gestite da un particolare Manager di Escursione, 
 * e quelle di un tipo specifico.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

public interface Escursione_DAO extends GEN_DAO<EscursioneTO>{
	
	/**
	 * @param id
	 * @return l'entit� con quel determinato id
	 * @throws DatabaseException
	 */
	EscursioneTO readById(Integer id) throws DatabaseException;
	
	/**
	 * @return le Escursioni attivate dal Manager di Escursione
	 * @throws DatabaseException
	 */
	List<EscursioneTO> readEscursioniAttive() throws DatabaseException;
	
	/**
	 * @return le Escursioni aperte alle iscrizioni
	 * @throws DatabaseException
	 */
	List<EscursioneTO> readEscursioniAperte() throws DatabaseException;
	
	/**
	 * 
	 * @param escursione
	 * @return restituisce le Escursione escluso quella annullata
	 * @throws DatabaseException
	 */
	EscursioneTO annullaEscursione(EscursioneTO escursione) throws DatabaseException;
	
	/**
	 * @param mde
	 * @return le Escursioni di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	List<EscursioneTO> readEscursioniByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException;
	
	/**
	 * @param mde
	 * @return le Escursioni attive di un determinato Manager di Escursione
	 * @throws DatabaseException
	 */
	List<EscursioneTO> readEscursioniAttiveByManagerDiEscursione(ManagerDiEscursioneTO mde) throws DatabaseException;
}
