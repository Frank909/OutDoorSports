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

@Entity
@Table(name = "tbl_escursione", catalog = "outdoorsports")
public class Escursione implements OutDoorSports{

	private static final long serialVersionUID = -8060673121786005549L;
	private Integer idEscursione;
	private StatoEscursione tblStatoEscursione;
	private TipoEscursione tblTipoEscursione;
	private Utente tblUtente;
	private String nome;
	private Date data;
	private int numberMin;
	private int numberMax;
	private double costo;
	private String descrizione;
	private Set<Iscrizione> tblIscriziones = new HashSet<Iscrizione>(0);
	private Set<OptionalEscursione> tblOptionalEscursiones = new HashSet<OptionalEscursione>(0);

	public Escursione() {
	}

	public Escursione(StatoEscursione tblStatoEscursione, TipoEscursione tblTipoEscursione,
			Utente tblUtente, String nome, Date data, int numberMin, int numberMax, double costo,
			String descrizione) {
		this.tblStatoEscursione = tblStatoEscursione;
		this.tblTipoEscursione = tblTipoEscursione;
		this.tblUtente = tblUtente;
		this.nome = nome;
		this.data = data;
		this.numberMin = numberMin;
		this.numberMax = numberMax;
		this.costo = costo;
		this.descrizione = descrizione;
	}

	public Escursione(StatoEscursione tblStatoEscursione, TipoEscursione tblTipoEscursione,
			Utente tblUtente, String nome, Date data, int numberMin, int numberMax, double costo, String descrizione,
			Set<Iscrizione> tblIscriziones, Set<OptionalEscursione> tblOptionalEscursiones) {
		this.tblStatoEscursione = tblStatoEscursione;
		this.tblTipoEscursione = tblTipoEscursione;
		this.tblUtente = tblUtente;
		this.nome = nome;
		this.data = data;
		this.numberMin = numberMin;
		this.numberMax = numberMax;
		this.costo = costo;
		this.descrizione = descrizione;
		this.tblIscriziones = tblIscriziones;
		this.tblOptionalEscursiones = tblOptionalEscursiones;
	}

	/**
	 * @return l'id dell'Escursione
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_escursione", unique = true, nullable = false)
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stato_escursione", nullable = false)
	public StatoEscursione getStatoEscursione() {
		return this.tblStatoEscursione;
	}

	/**
	 * Metodo che setta lo stato dell'Escursione
	 * 
	 * @param tblStatoEscursione
	 */
	public void setStatoEscursione(StatoEscursione tblStatoEscursione) {
		this.tblStatoEscursione = tblStatoEscursione;
	}

	/**
	 * @return il tipo dell'escursione
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_escursione", nullable = false)
	public TipoEscursione getTipoEscursione() {
		return this.tblTipoEscursione;
	}

	/**
	 * Metodo che setta il Tipo dell'Escursione
	 * 
	 * @param tblTipoEscursione
	 */
	public void setTipoEscursione(TipoEscursione tblTipoEscursione) {
		this.tblTipoEscursione = tblTipoEscursione;
	}

	/**
	 * @return l'utente associato a una determinata Escursione
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mde", nullable = false)
	public Utente getUtente() {
		return this.tblUtente;
	}

	/**
	 * Metodo che setta l'utente associato all'escursione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente tblUtente) {
		this.tblUtente = tblUtente;
	}

	/**
	 * @return il nome dell'escursione
	 */
	@Column(name = "nome", nullable = false, length = 45)
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
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false, length = 10)
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
	@Column(name = "number_min", nullable = false)
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
	@Column(name = "number_max", nullable = false)
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
	@Column(name = "costo", nullable = false, precision = 22, scale = 0)
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
	@Column(name = "descrizione", nullable = false, length = 65535)
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
	 * @return le iscrizioni per una determinata escursione
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblEscursione")
	public Set<Iscrizione> getIscrizioni() {
		return this.tblIscriziones;
	}

	/**
	 * Metodo che setta le iscrizioni per una determinata escursione
	 * 
	 * @param tblIscriziones
	 */
	public void setIscrizioni(Set<Iscrizione> tblIscriziones) {
		this.tblIscriziones = tblIscriziones;
	}

	/**
	 * @return gli optional di una determinata escursione
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblEscursione")
	public Set<OptionalEscursione> getOptionalEscursione() {
		return this.tblOptionalEscursiones;
	}

	/**
	 * Metodo che setta gli optional di una escursione
	 * 
	 * @param tblOptionalEscursiones
	 */
	public void setOptionalEscursione(Set<OptionalEscursione> tblOptionalEscursiones) {
		this.tblOptionalEscursiones = tblOptionalEscursiones;
	}

}
