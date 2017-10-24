package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta il i dati del Manager di Sistema. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface ManagerDiSistemaTO extends UtenteTO {
	
	/**
	 * Metodo che setta il manager di sistema
	 * 
	 * @param idMds
	 */
	public void setIdMds(int idMds);
	
	/**
	 * @return l'id del manager di sistema
	 */
	public int getIdMds();
	
	/**
	 * @return il numero di telefono del manager di sistema
	 */
	public String getTelefono();
	
	/**
	 * Metodo che setta il numero di telefono del manager di sistema
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono);
}
