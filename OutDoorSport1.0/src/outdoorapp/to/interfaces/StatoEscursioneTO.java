package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta StatoEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoEscursioneTO extends OutDoorSports{
	
	/**
	 * Metodo che setta lo stato dell'escursione
	 * 
	 * @param idStatoEscursione
	 */
	public void setIdStatoEscursione(Integer idStatoEscursione);
	
	/**
	 * @return l'id dello stato escursione
	 */
	public Integer getIdStatoEscursione();
	
	/**
	 * @return il nome dello stato escursione
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dello stato escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
}
