package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato di OptionalEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface OptionalEscursioneTO extends OutDoorSports{
	
	/**
	 * @return l'id di OptionalEscursione
	 */
	public Integer getId();

	/**
	 * Metodo che setta l'id di OptionalEscursione
	 * 
	 * @param id
	 */
	public void setId(Integer id);
	
	/**
	 * @return l'escursione collegata all'optional
	 */
	public EscursioneTO getEscursione();
	
	/**
	 * Metodo che setta l'escursione collegata all'optional
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(EscursioneTO tblEscursione);
	
	/**
	 * @return l'optional collegato all'escursione
	 */
	public OptionalTO getOptional();
	
	/**
	 * Metodo che setta l'optional collegato all'escursione
	 * 
	 * @param tblOptional
	 */
	public void setOptional(OptionalTO tblOptional);
	
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
	
}
