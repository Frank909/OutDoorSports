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
	
	/**
	 * Metodo che setta l'id di OptionalEscursione
	 * 
	 * @param id
	 */
	public void setId(Integer id);
	
	/**
	 * @return l'id di OptionalEscursione
	 */
	public Integer getId();
	
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
	 * @return gli optional dell'iscrizione
	 */
	public Set<OptionalIscrizioneTO> getOptionalIscrizione();
	
	/**
	 * Metodo che setta gli optional relativi all'iscrizione
	 * 
	 * @param tblOptionalIscriziones
	 */
	public void setOptionalIscrizione(Set<OptionalIscrizioneTO> tblOptionalIscriziones);
}
