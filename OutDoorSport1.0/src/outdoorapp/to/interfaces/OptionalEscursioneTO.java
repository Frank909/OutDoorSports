package outdoorapp.to.interfaces;

import java.util.Set;

/**
 * Interfaccia che rappresenta lo stato di OptionalEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface OptionalEscursioneTO extends OutDoorSports{
	

	public Integer getId();

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
