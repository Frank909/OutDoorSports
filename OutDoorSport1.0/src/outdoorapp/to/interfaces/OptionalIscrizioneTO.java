package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato di OptionalIscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface OptionalIscrizioneTO extends OutDoorSports{
	
	/**
	 * @return l'iscrizione relativa all'optional
	 */
	public Integer getIdIscrizione();
	
	/**
	 * Metodo che setta l'iscrizione relativa all'optional
	 * 
	 * @param tblIscrizione
	 */
	public void setIdIscrizione(Integer idIscrizione);
	
	/**
	 * @return l'optional escursione relativo all'iscrizione
	 */
	public Integer getIdOptionalEscursione();
	

	/**
	 * Metodo che setta una optional escursione relativo all'iscrizione
	 * 
	 * @param tblOptionalEscursione
	 */
	public void setIdOptionalEscursione(Integer idOptionalEscursione);
}
