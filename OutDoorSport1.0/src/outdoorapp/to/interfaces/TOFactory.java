package outdoorapp.to.interfaces;

import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.enums.StatoEnum;
import outdoorapp.to.enums.TipoEnum;
import outdoorapp.to.enums.UtenteEnum;

/**
 * Interfaccia che rappresenta l'abstract factory per il Transfer Object. 
 * I metodi richiamano le categorie desiderate.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface TOFactory {
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria Generic. 
	 * Pu� essere istanziato un oggetto di tipo Escursione, Iscrizione, Email.
	 * Il tutto � nascosto all'utilizzatore che  non sa come il metodo operer�.
	 *
	 * @param un valore enumerativo di GenericEnum
	 * @return una istanza che implementa l'interfaccia OutDoorSports
	 */
	public OutDoorSports getGenericTO(GenericEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria Utente.
	 * Pu� essere istanziato un oggetto di tipo Utente, 
	 * Partecipante, ManagerDiEscursione o ManagerDiSistema. Il tutto � nascosto a
	 * ll'utilizzatore che non sa come il metodo operer�
	 * 
	 * @param un valore enumerativo di Utentenum
	 * @return una istanza che implementa l'interfaccia OutDoorSports
	 */
	public OutDoorSports getUtenteTO(UtenteEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria Tipo.
	 * Pu� essere istanziato un oggetto di tipo Ruoli, 
	 * TipoOptional o TipoEscursione. Il tutto � nascosto a
	 * ll'utilizzatore che non sa come il metodo operer�
	 * 
	 * @param un valore enumerativo di TipoEnum
	 * @return una istanza che implementa l'interfaccia OutDoorSports
	 */
	public OutDoorSports getTipoTO(TipoEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria Optional.
	 * Pu� essere istanziato un oggetto di tipo Optional, 
	 * OptionalEscursione o OptionalIscrizione. Il tutto � nascosto a
	 * ll'utilizzatore che non sa come il metodo operer�
	 * 
	 * @param un valore enumerativo di OptionalEnum
	 * @return una istanza che implementa l'interfaccia OutDoorSports
	 */
	public OutDoorSports getOptionalTO(OptionalEnum choice);
	
	/**
	 * Il metodo restituisce una nuova entit� della categoria Optional.
	 * Pu� essere istanziato un oggetto di tipo StatoUtente, 
	 * StatoOptional, StatoEscursione o StatoIscrizione. Il tutto � nascosto a
	 * ll'utilizzatore che non sa come il metodo operer�
	 * 
	 * @param un valore enumerativo di StatolEnum
	 * @return una istanza che implementa l'interfaccia OutDoorSports
	 */
	public OutDoorSports getStatoTO(StatoEnum choice);
}
