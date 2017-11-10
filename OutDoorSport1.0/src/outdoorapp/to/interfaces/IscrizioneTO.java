package outdoorapp.to.interfaces;

import java.util.Set;

/**
 * Interfaccia che rappresenta lo stato dell'Iscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface IscrizioneTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id dell'iscrizione
	 * 
	 * @param idIscrizione
	 */
	public void setIdIscrizione(Integer idIscrizione);
	
	/**
	 * @return id dell'iscrizione
	 */
	public Integer getIdIscrizione();
	
	/**
	 * @return l'escursione associata a quell'escursione
	 */
	public EscursioneTO getEscursione();
	
	/**
	 * Metodo che setta l'escursione della relativa iscrizione
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(EscursioneTO escursione);
	
	/**
	 * @return lo stato dell'iscrizione
	 */
	public StatoIscrizioneTO getStatoIscrizione();
	
	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param tblStatoIscrizione
	 */
	public void setStatoIscrizione(StatoIscrizioneTO statoIscrizione);
	
	/**
	 * @return l'utente relativo a quella iscrizione
	 */
	public UtenteTO getUtente();
	

	/**
	 * Metodo che setta l'utente relativo a quella iscrizione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(UtenteTO utente);
	

	/**
	 * @return la data di una iscrizione
	 */
	public String getData();
	
	/**
	 * Metodo che setta la data di una iscrizione
	 * 
	 * @param data
	 */
	public void setData(String data);
	
	/**
	 * @return il momento esatto dell'iscrizione
	 */
	public String getOra();
	
	/**
	 * Metodo che setta il momento esatto dell'iscrizione
	 * 
	 * @param ora
	 */
	public void setOra(String ora);
	
	/**
	 * @return un set di optional
	 */
	public Set<OptionalEscursioneTO> getOptionals();
	
	/**
	 * Metodo che setta gli optional per una determinata iscrizione
	 * 
	 * @param optionals
	 */
	public void setOptionals(Set<OptionalEscursioneTO> optionals);
}
