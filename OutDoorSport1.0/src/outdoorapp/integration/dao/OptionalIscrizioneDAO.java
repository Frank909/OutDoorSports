package outdoorapp.integration.dao;

import outdoorapp.integration.dao.interfaces.OptionalIscrizione_DAO;
import outdoorapp.to.interfaces.OptionalIscrizioneTO;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per gli OptionalIscrizione collegati
 * alle escursioni.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class OptionalIscrizioneDAO extends GenericDAO<OptionalIscrizioneTO> implements OptionalIscrizione_DAO{

	OptionalIscrizioneDAO() {}
}
