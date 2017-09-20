package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe che rappresenta lo stato di OptionalIscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
@Entity
@Table(name = "tbl_optional_iscrizione", catalog = "outdoorsports")
public class OptionalIscrizione implements OutDoorSports{

	private static final long serialVersionUID = -5812339531551921552L;
	private Integer id;
	private Iscrizione tblIscrizione;
	private OptionalEscursione tblOptionalEscursione;

	public OptionalIscrizione() {
	}

	public OptionalIscrizione(Iscrizione tblIscrizione, OptionalEscursione tblOptionalEscursione) {
		this.tblIscrizione = tblIscrizione;
		this.tblOptionalEscursione = tblOptionalEscursione;
	}

	/**
	 * @return l'id di optional iscrizione
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo che setta l'id di OptionalIscrizione
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return l'iscrizione relativa all'optional
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_iscrizione", nullable = false)
	public Iscrizione getIscrizione() {
		return this.tblIscrizione;
	}

	/**
	 * Metodo che setta l'iscrizione relativa all'optional
	 * 
	 * @param tblIscrizione
	 */
	public void setIscrizione(Iscrizione tblIscrizione) {
		this.tblIscrizione = tblIscrizione;
	}

	/**
	 * @return l'optional escursione relativo all'iscrizione
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_optional", nullable = false)
	public OptionalEscursione getOptionalEscursione() {
		return this.tblOptionalEscursione;
	}

	/**
	 * Metodo che setta una optional escursione relativo all'iscrizione
	 * 
	 * @param tblOptionalEscursione
	 */
	public void setOptionalEscursione(OptionalEscursione tblOptionalEscursione) {
		this.tblOptionalEscursione = tblOptionalEscursione;
	}

}