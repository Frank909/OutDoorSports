package outdoorapp.integration.dao;

import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.enums.TipoDAOEnum;
import outdoorapp.integration.dao.enums.UtenteDAOEnum;

/**
 * Interfaccia che rappresenta l'abstract factory per il Data Access Object. I metodi richiamano le 
 * categorie desiderate.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface DAOFactory{
	
	/**
	 * Il metodo restituisce una nuova entità della categoria GenericDAO. 
	 * Può essere istanziato un oggetto di tipo EscursioneDAO, IscrizioneDAO, OptionalDAO.
	 * Il tutto è nascosto all'utilizzatore che  non sa come il metodo opererà.
	 *
	 * @param un valore enumerativo di GenericDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getGenericDAO(GenericDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entità della categoria TipoDAO. 
	 * Può essere istanziato un oggetto di tipo TipoEscursioneDAO, TipoOptionalDAO, RuoliDAO.
	 * Il tutto è nascosto all'utilizzatore che  non sa come il metodo opererà.
	 *
	 * @param un valore enumerativo di TipoDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getTipoDAO(TipoDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entità della categoria StatoDAO. 
	 * Può essere istanziato un oggetto di tipo StatoEscursioneDAO, StatoIscrizioneDAO, 
	 * StatoOptionalDAO, StatoUtenteDAO.
	 * Il tutto è nascosto all'utilizzatore che non sa come il metodo opererà.
	 *
	 * @param un valore enumerativo di StaoDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getStatoDAO(StatoDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entità della categoria UtenteDAO. 
	 * Può essere istanziato un oggetto di tipo ManagerDiSistemaDAO, 
	 * ManagerDiEscursioneDAO, UtenteDAO, PartecipanteDAO.
	 * Il tutto è nascosto all'utilizzatore che non sa come il metodo opererà.
	 *
	 * @param un valore enumerativo di UtenteEnum
	 * @return una istanza di UtenteDAO
	 */
	UtenteDAO<?> getUtenteDAO(UtenteDAOEnum choice);
}
