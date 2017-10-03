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
	private Escursione tblEscursione;
	private StatoIscrizione tblStatoIscrizione;
	private Utente tblUtente;
	private Date data;
	private Set<OptionalIscrizione> tblOptionalIscriziones = new HashSet<OptionalIscrizione>(0);

	public Iscrizione() {
	}

	public Iscrizione(Escursione tblEscursione, StatoIscrizione tblStatoIscrizione, Utente tblUtente,
			Date data) {
		this.tblEscursione = tblEscursione;
		this.tblStatoIscrizione = tblStatoIscrizione;
		this.tblUtente = tblUtente;
		this.data = data;
	}

	public Iscrizione(Escursione tblEscursione, StatoIscrizione tblStatoIscrizione, Utente tblUtente,
			Date data, Set<OptionalIscrizione> tblOptionalIscriziones) {
		this.tblEscursione = tblEscursione;
		this.tblStatoIscrizione = tblStatoIscrizione;
		this.tblUtente = tblUtente;
		this.data = data;
		this.tblOptionalIscriziones = tblOptionalIscriziones;
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
		return this.tblEscursione;
	}

	/**
	 * Metodo che setta l'escursione della relativa iscrizione
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(Escursione tblEscursione) {
		this.tblEscursione = tblEscursione;
	}

	/**
	 * @return lo stato dell'iscrizione
	 */
	public StatoIscrizione getStatoIscrizione() {
		return this.tblStatoIscrizione;
	}

	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param tblStatoIscrizione
	 */
	public void setStatoIscrizione(StatoIscrizione tblStatoIscrizione) {
		this.tblStatoIscrizione = tblStatoIscrizione;
	}

	/**
	 * @return l'utente relativo a quella iscrizione
	 */
	public Utente getUtente() {
		return this.tblUtente;
	}

	/**
	 * Metodo che setta l'utente relativo a quella iscrizione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente tblUtente) {
		this.tblUtente = tblUtente;
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
	 * @return gli optional relativi a una iscrizione ad una escursione
	 */
	public Set<OptionalIscrizione> getOptionalIscrizione() {
		return this.tblOptionalIscriziones;
	}

	/**
	 * Metodo che setta gli optional relativi a una iscrizione ad una escursione
	 * 
	 * @param tblOptionalIscriziones
	 */
	public void setOptionalIscrizione(Set<OptionalIscrizione> tblOptionalIscriziones) {
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

}
