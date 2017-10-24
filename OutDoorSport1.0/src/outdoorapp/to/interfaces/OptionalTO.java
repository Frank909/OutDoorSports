package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato dell'Optional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface OptionalTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id dell'Optional
	 * 
	 * @param idOptional
	 */
	public void setIdOptional(Integer idOptional);
	
	/**
	 * @return l'id dell'optional
	 */
	public Integer getIdOptional();
	
	/**
	 * @return lo stato dell'optional
	 */
	public StatoOptionalTO getStatoOptional();
	
	/**
	 * Metodo che setta lo stato dell'optional
	 * 
	 * @param tblStatoOptional
	 */
	public void setStatoOptional(StatoOptionalTO statoOptional);
	
	/**
	 * @return il tipo dell'optional
	 */
	public TipoOptionalTO getTipoOptional();
	
	/**
	 * Metodo che setta il tipo dell'optional
	 * 
	 * @param tblTipoOptional
	 */
	public void setTipoOptional(TipoOptionalTO tipoOptional);
	
	/**
	 * @return il nome dell'optional
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dell'optional
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
	
	/**
	 * @return la descrizione dell'optional
	 */
	public String getDescrizione();
	
	/**
	 * Metodo che setta la descrizione dell'optional
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione);
}
