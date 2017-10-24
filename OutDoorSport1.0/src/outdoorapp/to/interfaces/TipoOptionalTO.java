package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato del TipoOptional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface TipoOptionalTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id del tipo optional
	 * 
	 * @param idTipoOptional
	 */
	public void setIdTipoOptional(Integer idTipoOptional);
	
	/**
	 * @return l'id del tipo optional
	 */
	public Integer getIdTipoOptional();
	
	/**
	 * @return il nome del tipo optional
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome del tipo optional
	 * 
	 * @param nome
	 */

	public void setNome(String nome);
	
	/**
	 * @return il costo di un optional
	 */
	public double getCosto();
	
	/**
	 * Metodo che setta il costo di un optional
	 * 
	 * @param costo
	 */
	public void setCosto(double costo);
}
