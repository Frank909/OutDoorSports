package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Utente_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

import java.util.ArrayList;
import java.util.List;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per Utente.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class UtenteDAO<T extends UtenteTO> extends GenericDAO<T> implements Utente_DAO<T>{
	

	private UtenteTO utente = null;
	
	/**
	 * Il costruttore inizializza l'entità Utente da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public UtenteDAO() {
		if(utente == null){
			TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			utente = (UtenteTO) tofact.getUtenteTO(UtenteEnum.Utente);
		}
		
		this.setCurrentClass(utente);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getUtente(UtenteTO utente) throws DatabaseException {
		UtenteTO response = null;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		param.add(utente.getPassword());
		
		List<T> list = this.executeParamQuery("getUtente", param);
		if(list.size() == 1){
			response = (UtenteTO) list.get(0);
		} else {
			response = utente;
		}
		return (T)response;
	}
	
	/**
	 * @param queryName
	 * @param params
	 * @return un'istanza T sottoclasse di Utente tramite query
	 * @throws DatabaseException
	 */
	@SuppressWarnings({ "unchecked" })
	private T getUtenteByQuery(String queryName, List<?> params) throws DatabaseException{
		UtenteTO response = null;
		List<T> list = super.executeParamQuery(queryName, params);
		if(list.size() == 0){
			response = utente;
		} else {
			response = (UtenteTO)list.get(0);
		}
		return (T)response;
	}

	@Override
	public T getByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		return this.getUtenteByQuery("getByUsername", param);
	}
	
	@Override
	public T getByEmail(String email) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(email);
		return this.getUtenteByQuery("getByEmail", param);
	}
	
	@Override
	public T getByID(Integer id) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);
		return getUtenteByQuery("getByID", param);
	}
	
	/**
	 * @param utente
	 * @return vero se è un utente nullo, falso altrimenti
	 */
	private boolean isNullUtente(UtenteTO utente){
		return utente.getIdUtente() == null;
	}
	
	@Override
	public boolean esisteUsername(UtenteTO utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getUsername());
		UtenteTO newUtente = (UtenteTO) this.getUtenteByQuery("getByUsername", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
	
	@Override
	public boolean esisteEmail(UtenteTO utente) throws DatabaseException {
		boolean response = false;
		List<String> param = new ArrayList<String>();
		param.add(utente.getEmail());
		UtenteTO newUtente = (UtenteTO) this.getUtenteByQuery("getByEmail", param);
		response = !this.isNullUtente(newUtente);
		return response;
	}
}
