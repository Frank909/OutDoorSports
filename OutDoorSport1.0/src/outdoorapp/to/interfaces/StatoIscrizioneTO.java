package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato di StatoIscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoIscrizioneTO extends OutDoorSports{
	
	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param idStatoIscrizione
	 */
	public void setIdStatoIscrizione(Integer idStatoIscrizione);
	
	/**
	 * @return l'id dello statoIscrizione
	 */
	public Integer getIdStatoIscrizione();
	
	/**
	 * @return il nome dello stato iscrizione
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dello stato iscrizione
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
}
