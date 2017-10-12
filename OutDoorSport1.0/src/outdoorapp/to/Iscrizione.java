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

/**
 * Classe che rappresenta lo stato dell'Iscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class Iscrizione implements OutDoorSports{

	private static final long serialVersionUID = 5440407744318413216L;
	private Integer idIscrizione;
	private Escursione escursione;
	private StatoIscrizione statoIscrizione;
	private Utente utente;
	private Date data;
	private Set<Optional> optionals;

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
	public Escursione getEscursione() {
		return this.escursione;
	}

	/**
	 * Metodo che setta l'escursione della relativa iscrizione
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(Escursione escursione) {
		this.escursione = escursione;
	}

	/**
	 * @return lo stato dell'iscrizione
	 */
	public StatoIscrizione getStatoIscrizione() {
		return this.statoIscrizione;
	}

	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param tblStatoIscrizione
	 */
	public void setStatoIscrizione(StatoIscrizione statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
	}

	/**
	 * @return l'utente relativo a quella iscrizione
	 */
	public Utente getUtente() {
		return this.utente;
	}

	/**
	 * Metodo che setta l'utente relativo a quella iscrizione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	/**
	 * @return la data di una iscrizione
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Metodo che setta la data di una iscrizione
	 * 
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
	/**
	 * @return un set di optional
	 */
	public Set<Optional> getOptionals() {
		return optionals;
	}

	/**
	 * Metodo che setta gli optional per una determinata iscrizione
	 * 
	 * @param optionals
	 */
	public void setOptionals(Set<Optional> optionals) {
		this.optionals = optionals;
	}
}
