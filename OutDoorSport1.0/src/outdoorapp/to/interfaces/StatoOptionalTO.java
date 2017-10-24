package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato di StatoOptional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoOptionalTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id dello stato dell'optional
	 * 
	 * @param idStatoOptional
	 */
	public void setIdStatoOptional(Integer idStatoOptional);
	
	/**
	 * @return l'id dello stato dell'optional
	 */
	public Integer getIdStatoOptional();
	
	/**
	 * @return il nome dello stato dell'optional
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dello stato dell'optional
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
}
