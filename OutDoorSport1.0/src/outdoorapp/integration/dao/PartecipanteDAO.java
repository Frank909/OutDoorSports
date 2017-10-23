package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.to.Partecipante;
import java.util.ArrayList;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Partecipante.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class PartecipanteDAO extends UtenteDAO<Partecipante> implements Partecipante_DAO{

	/**
	 * Il costruttore inizializza l'entit� Partecipante da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public PartecipanteDAO() {
		this.setCurrentClass(new Partecipante());
	}
	
	/**
	 * @param codiceFiscale
	 * @return il Partecipante con un determinato codice fiscale 
	 * @throws DatabaseException
	 */
	@Override
	public Partecipante readByCodiceFiscale(String codiceFiscale) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(codiceFiscale);
		List<Partecipante> list = super.executeParamQuery("booooooooooooooooooh", param);
		Partecipante response = (Partecipante)list.get(0);
		return response;
	}
	
	/**
	 * @param username
	 * @return il Partecipante con un determinato username
	 * @throws DatabaseException
	 */
	@Override
	public Partecipante readByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		List<Partecipante> list = super.executeParamQuery("boooooooooooooh", param);
		Partecipante response = (Partecipante)list.get(0);
		return response;
	}
	
	/**
	 * @return la lista di tutti i partecipanti
	 * @throws DatabaseException
	 */
	@Override
	public List<Partecipante> getAllPartecipante() throws DatabaseException {
		return super.executeQuery("boooooooooooooooooooh");
	}
}
