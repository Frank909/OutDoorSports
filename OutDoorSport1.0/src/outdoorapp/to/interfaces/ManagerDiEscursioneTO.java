package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta il i dati del Manager di Escursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface ManagerDiEscursioneTO extends UtenteTO{
	
	/**
	 * Metodo che setta l'id del manager di escursione
	 * 
	 * @param idMde
	 */
	public void setIdManagerDiEscursione(int idMde);
	
	/**
	 * @return l'id del manager di escursione
	 */ 
	public int getIdManagerDiEscursione();
	
	/**
	 * @return lo stipendio del manager di escursione
	 */
	public double getStipendio();
	
	/**
	 * Metodo che setta lo stipendio del manager di escursione
	 * 
	 * @param stipendio
	 */
	public void setStipendio(double stipendio);
}
