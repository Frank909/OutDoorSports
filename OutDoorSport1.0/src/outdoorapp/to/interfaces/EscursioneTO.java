package outdoorapp.to.interfaces;

import java.util.Date;
import java.util.Set;

/**
 * Interfaccia che rappresenta lo stato dell'Escursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface EscursioneTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id dell'escursione
	 * 
	 * @param idEscursione
	 */
	public void setIdEscursione(Integer idEscursione);
	
	/**
	 * @return l'id dell'Escursione
	 */
	public Integer getIdEscursione();
	
	/**
	 * @return lo stato dell'Escursione
	 */
	public StatoEscursioneTO getStatoEscursione();
	
	/**
	 * Metodo che setta lo stato dell'Escursione
	 * 
	 * @param tblStatoEscursione
	 */
	public void setStatoEscursione(StatoEscursioneTO statoEscursione);
	
	/**
	 * @return il tipo dell'escursione
	 */
	public TipoEscursioneTO getTipoEscursione();
	
	/**
	 * Metodo che setta il Tipo dell'Escursione
	 * 
	 * @param tblTipoEscursione
	 */
	public void setTipoEscursione(TipoEscursioneTO tipoEscursione);
	
	/**
	 * @return l'utente associato a una determinata Escursione
	 */
	public UtenteTO getUtente();
	
	/**
	 * Metodo che setta l'utente associato all'escursione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(UtenteTO utente);
	
	/**
	 * @return il nome dell'escursione
	 */
	public String getNome();
	

	/**
	 * Metodo che setta il nome dell'escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
	
	/**
	 * @return la data dell'escursione
	 */
	public Date getData();
	
	/**
	 * Metodo che setta la data dell'escursione
	 * 
	 * @param data
	 */
	public void setData(Date data);
	
	/**
	 * @return numero minimo di partecipanti della escursione
	 */
	public int getNumberMin();
	
	/**
	 * Metodo che setta il numero minimo dei partecipanti della escursione
	 * 
	 * @param numberMin
	 */
	public void setNumberMin(int numberMin);
	
	/**
	 * @return numero massimo di partecipanti della escursione
	 */
	public int getNumberMax();
	
	/**
	 * Metodo che setta il numero massimo dei partecipanti alla escursione
	 * 
	 * @param numberMax
	 */
	public void setNumberMax(int numberMax);
	
	/**
	 * @return il costo della escursione
	 */
	public double getCosto();
	
	/**
	 * Metodo che setta il costo della escursione
	 * 
	 * @param costo
	 */
	public void setCosto(double costo);
	
	/**
	 * @return la descrizione della escursione
	 */
	public String getDescrizione();
	
	/**
	 * Metodo che setta la descrizione della escursione
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione);
	
	/**
	 * @return un set di optional
	 */
	public Set<OptionalTO> getOptionals();
	
	/**
	 * Metodo che setta gli optional per una determinata escursione
	 * 
	 * @param optionals
	 */
	public void setOptionals(Set<OptionalTO> optionals);
}
