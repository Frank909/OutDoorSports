package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta TipoEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface TipoEscursioneTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id del tipo escursione
	 * 
	 * @param idTipoEscursione
	 */
	public void setIdTipoEscursione(Integer idTipoEscursione);
	
	/**
	 * @return l'id del tipo escursione
	 */
	public Integer getIdTipoEscursione();
	
	/**
	 * @return il nome del tipo escursione
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome del tipo escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
	

	/** 
	 * @return la descrizione del tipoEscursione
	 */
	public String getDescrizione();
	
	/**
	 * Metodo che setta la descrizione del tipo escursione
	 * 
	 * @param decrizione
	 */
	public void setDescrizione(String decrizione);
}
