package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.to.Escursione;
import outdoorapp.to.Iscrizione;
import outdoorapp.to.Partecipante;
import outdoorapp.to.StatoIscrizione;
import java.util.ArrayList;
import java.util.List;


/** 
 * Data Access Object per tutte le operazioni CRUD per Iscrizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class IscrizioneDAO extends GenericDAO<Iscrizione> implements Iscrizione_DAO{

	/**
	 * Il costruttore inizializza l'entità Iscrizione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public IscrizioneDAO() {
		this.setCurrentClass(new Iscrizione());
	}
	
	/**
	 * @return la lista di tutte le iscrizioni
	 * @throws DatabaseException
	 */
	@Override
	public List<Iscrizione> getAll() throws DatabaseException{
		List<Iscrizione> response = super.getAll();
		return response;
	}
	
	/**
	 * @param iscrizione
	 * @return l'iscrizione cambiando lo stato in annullata
	 * @throws DatabaseException
	 */
	@Override
	public Iscrizione annullaIscrizione(Iscrizione iscrizione) throws DatabaseException {
		super.update(iscrizione);
		return iscrizione;
	}
	
	/**
	 * @param iscrizione
	 * @return vero se esiste l'iscrizione, falso altrimenti
	 * @throws DatabaseException
	 */
	@Override
	public boolean esisteIscrizione(Iscrizione iscrizione) throws DatabaseException {
		Iscrizione newIscrizione = this.findOne(iscrizione.getIdIscrizione());
		return newIscrizione != null;
	}
	
	/**
	 * 
	 * @param partecipante
	 * @return la lista delle iscrizioni attive di un partecipante
	 * @throws DatabaseException
	 */
	@Override
	public List<Iscrizione> getAllIscrizioniAttive(Partecipante partecipante) throws DatabaseException {
		List<Partecipante> param = new ArrayList<Partecipante>();
		param.add(partecipante);
		List<Iscrizione> response = super.executeParamQuery("BOOOOOOOOh", param);
		return response;
	}
	
	/**
	 * @return lo stato terminato di un iscrizione
	 * @throws DatabaseException
	 */
	private StatoIscrizione getStatoIscrizioneTerminato() throws DatabaseException {
		StatoIscrizione statoIscrizione = (StatoIscrizione) super.executeQuery("BOOOOOOH");
		return statoIscrizione;
	}
	
	/**
	 * Modifica lo stato delle Iscrizioni di una determinata Escursione 
	 * in termininato
	 * 
	 * @param escursione
	 * @throws DatabaseException
	 */
	@Override
	public void terminaIscrizioni(Escursione escursione) throws DatabaseException {
		List<Iscrizione> response = getIscrizioniAttiveEscursione(escursione);
		for(Iscrizione iscrizione: response){
			iscrizione.setStatoIscrizione(getStatoIscrizioneTerminato());
			this.update(iscrizione);
		}
	}
	
	/**
	 * @param escursione
	 * @return la lista delle Iscrizioni attive per una determitata Escursione
	 * @throws DatabaseException
	 */
	@Override
	public List<Iscrizione> getIscrizioniAttiveEscursione(Escursione escursione) throws DatabaseException {
		List<Escursione> param = new ArrayList<Escursione>();
		param.add(escursione);
		List<Iscrizione> response = super.executeParamQuery("boooooooooooooooooh", param);
		return response;
	}

	/**
	 * @param partecipante
	 * @return tutte le iscrizioni di un partecipante
	 * @throws DatabaseException
	 */
	@Override
	public List<Iscrizione> getAllIscrizioniPartecipante(Partecipante partecipante) throws DatabaseException {
		List<Partecipante> param = new ArrayList<Partecipante>();
		param.add(partecipante);
		List<Iscrizione> res = super.executeParamQuery("booooooooh", param);
		return res;
	}
}
