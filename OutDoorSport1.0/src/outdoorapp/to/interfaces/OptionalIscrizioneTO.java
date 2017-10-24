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
	 * @return l'id di optional iscrizione
	 */
	public Integer getId();
	
	/**
	 * @return l'iscrizione relativa all'optional
	 */
	public IscrizioneTO getIscrizione();
	
	/**
	 * Metodo che setta l'iscrizione relativa all'optional
	 * 
	 * @param tblIscrizione
	 */
	public void setIscrizione(IscrizioneTO tblIscrizione);
	
	/**
	 * @return l'optional escursione relativo all'iscrizione
	 */
	public OptionalEscursioneTO getOptionalEscursione();
	

	/**
	 * Metodo che setta una optional escursione relativo all'iscrizione
	 * 
	 * @param tblOptionalEscursione
	 */
	public void setOptionalEscursione(OptionalEscursioneTO tblOptionalEscursione);
	

	/**
	 * Metodo che setta l'id di OptionalIscrizione
	 * 
	 * @param id
	 */
	public void setId(Integer id);
}
