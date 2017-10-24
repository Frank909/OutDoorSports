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
	 * Il metodo restituisce una nuova entit� della categoria GenericDAO. 
	 * Pu� essere istanziato un oggetto di tipo EscursioneDAO, IscrizioneDAO, OptionalDAO.
	 * Il tutto � nascosto all'utilizzatore che  non sa come il metodo operer�.
	 *
	 * @param un valore enumerativo di GenericDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getGenericDAO(GenericDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria TipoDAO. 
	 * Pu� essere istanziato un oggetto di tipo TipoEscursioneDAO, TipoOptionalDAO, RuoliDAO.
	 * Il tutto � nascosto all'utilizzatore che  non sa come il metodo operer�.
	 *
	 * @param un valore enumerativo di TipoDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getTipoDAO(TipoDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria StatoDAO. 
	 * Pu� essere istanziato un oggetto di tipo StatoEscursioneDAO, StatoIscrizioneDAO, 
	 * StatoOptionalDAO, StatoUtenteDAO.
	 * Il tutto � nascosto all'utilizzatore che non sa come il metodo operer�.
	 *
	 * @param un valore enumerativo di StaoDAOEnum
	 * @return una istanza di GenericDAO
	 */
	GenericDAO<?> getStatoDAO(StatoDAOEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria UtenteDAO. 
	 * Pu� essere istanziato un oggetto di tipo ManagerDiSistemaDAO, 
	 * ManagerDiEscursioneDAO, UtenteDAO, PartecipanteDAO.
	 * Il tutto � nascosto all'utilizzatore che non sa come il metodo operer�.
	 *
	 * @param un valore enumerativo di UtenteEnum
	 * @return una istanza di UtenteDAO
	 */
	UtenteDAO<?> getUtenteDAO(UtenteDAOEnum choice);
}
