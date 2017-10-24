package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta il ruolo dell'Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface RuoliTO extends OutDoorSports{
	
	/**
	 * Metodo che setta il ruolo dell'utente
	 * 
	 * @param idRuolo
	 */
	public void setIdRuolo(Integer idRuolo);
	
	/**
	 * @return l'id del ruolo dell'utente
	 */
	public Integer getIdRuolo();
	
	/**
	 * @return il nome del ruolo 
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome del ruolo
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
}
