package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che rappresenta lo stato dell'Iscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Iscrizione implements IscrizioneTO{

	private static final long serialVersionUID = 5440407744318413216L;
	private Integer idIscrizione;
	private EscursioneTO escursione;
	private StatoIscrizioneTO statoIscrizione;
	private UtenteTO utente;
	private String data;
	private Set<OptionalTO> optionals;

	public Iscrizione() {
	}

	/**
	 * @return id dell'iscrizione
	 */
	public Integer getIdIscrizione() {
		return this.idIscrizione;
	}

	/**
	 * Metodo che setta l'id dell'iscrizione
	 * 
	 * @param idIscrizione
	 */
	public void setIdIscrizione(Integer idIscrizione) {
		this.idIscrizione = idIscrizione;
	}

	/**
	 * @return l'escursione associata a quell'escursione
	 */
	public EscursioneTO getEscursione() {
		return this.escursione;
	}

	/**
	 * Metodo che setta l'escursione della relativa iscrizione
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(EscursioneTO escursione) {
		this.escursione = escursione;
	}

	/**
	 * @return lo stato dell'iscrizione
	 */
	public StatoIscrizioneTO getStatoIscrizione() {
		return this.statoIscrizione;
	}

	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param tblStatoIscrizione
	 */
	public void setStatoIscrizione(StatoIscrizioneTO statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
	}

	/**
	 * @return l'utente relativo a quella iscrizione
	 */
	public UtenteTO getUtente() {
		return this.utente;
	}

	/**
	 * Metodo che setta l'utente relativo a quella iscrizione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(UtenteTO utente) {
		this.utente = utente;
	}

	/**
	 * @return la data di una iscrizione
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Metodo che setta la data di una iscrizione
	 * 
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return un set di optional
	 */
	public Set<OptionalTO> getOptionals() {
		return optionals;
	}

	/**
	 * Metodo che setta gli optional per una determinata iscrizione
	 * 
	 * @param optionals
	 */
	@Override
	public void setOptionals(Set<OptionalTO> optionals) {
		this.optionals = optionals;
	}
}
