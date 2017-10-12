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
 * Classe che rappresenta lo stato dell'Escursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class Escursione implements OutDoorSports{

	private static final long serialVersionUID = -8060673121786005549L;
	private Integer idEscursione;
	private StatoEscursione statoEscursione;
	private TipoEscursione tipoEscursione;
	private Utente utente;
	private String nome;
	private Date data;
	private int numberMin;
	private int numberMax;
	private double costo;
	private String descrizione;
	private Set<Optional> optionals;

	public Escursione() {
	}

	/**
	 * @return l'id dell'Escursione
	 */
	public Integer getIdEscursione() {
		return this.idEscursione;
	}

	/**
	 * Metodo che setta l'id dell'escursione
	 * 
	 * @param idEscursione
	 */
	public void setIdEscursione(Integer idEscursione) {
		this.idEscursione = idEscursione;
	}

	/**
	 * @return lo stato dell'Escursione
	 */
	public StatoEscursione getStatoEscursione() {
		return this.statoEscursione;
	}

	/**
	 * Metodo che setta lo stato dell'Escursione
	 * 
	 * @param tblStatoEscursione
	 */
	public void setStatoEscursione(StatoEscursione statoEscursione) {
		this.statoEscursione = statoEscursione;
	}

	/**
	 * @return il tipo dell'escursione
	 */
	public TipoEscursione getTipoEscursione() {
		return this.tipoEscursione;
	}

	/**
	 * Metodo che setta il Tipo dell'Escursione
	 * 
	 * @param tblTipoEscursione
	 */
	public void setTipoEscursione(TipoEscursione tipoEscursione) {
		this.tipoEscursione = tipoEscursione;
	}

	/**
	 * @return l'utente associato a una determinata Escursione
	 */
	public Utente getUtente() {
		return this.utente;
	}

	/**
	 * Metodo che setta l'utente associato all'escursione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	/**
	 * @return il nome dell'escursione
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dell'escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return la data dell'escursione
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Metodo che setta la data dell'escursione
	 * 
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return numero minimo di partecipanti della escursione
	 */
	public int getNumberMin() {
		return this.numberMin;
	}

	/**
	 * Metodo che setta il numero minimo dei partecipanti della escursione
	 * 
	 * @param numberMin
	 */
	public void setNumberMin(int numberMin) {
		this.numberMin = numberMin;
	}

	/**
	 * @return numero massimo di partecipanti della escursione
	 */
	public int getNumberMax() {
		return this.numberMax;
	}

	/**
	 * Metodo che setta il numero massimo dei partecipanti alla escursione
	 * 
	 * @param numberMax
	 */
	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	/**
	 * @return il costo della escursione
	 */
	public double getCosto() {
		return this.costo;
	}

	/**
	 * Metodo che setta il costo della escursione
	 * 
	 * @param costo
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return la descrizione della escursione
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Metodo che setta la descrizione della escursione
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return un set di optional
	 */
	public Set<Optional> getOptionals() {
		return optionals;
	}

	/**
	 * Metodo che setta gli optional per una determinata escursione
	 * 
	 * @param optionals
	 */
	public void setOptionals(Set<Optional> optionals) {
		this.optionals = optionals;
	}

}
