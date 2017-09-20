package java.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

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

/**
 * Classe che rappresenta lo stato di OptionalEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
@Entity
@Table(name = "tbl_optional_escursione", catalog = "outdoorsports")
public class OptionalEscursione implements OutDoorSports{

	private static final long serialVersionUID = -2717683768500440335L;
	private Integer id;
	private Escursione tblEscursione;
	private Optional tblOptional;
	private Set<OptionalIscrizione> tblOptionalIscriziones = new HashSet<OptionalIscrizione>(0);

	public OptionalEscursione() {
	}

	public OptionalEscursione(Escursione tblEscursione, Optional tblOptional) {
		this.tblEscursione = tblEscursione;
		this.tblOptional = tblOptional;
	}

	public OptionalEscursione(Escursione tblEscursione, Optional tblOptional,
			Set<OptionalIscrizione> tblOptionalIscriziones) {
		this.tblEscursione = tblEscursione;
		this.tblOptional = tblOptional;
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

	/**
	 * @return l'id di OptionalEscursione
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo che setta l'id di OptionalEscursione
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return l'escursione collegata all'optional
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_escursione", nullable = false)
	public Escursione getEscursione() {
		return this.tblEscursione;
	}

	/**
	 * Metodo che setta l'escursione collegata all'optional
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(Escursione tblEscursione) {
		this.tblEscursione = tblEscursione;
	}

	/**
	 * @return l'optional collegato all'escursione
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_optional", nullable = false)
	public Optional getOptional() {
		return this.tblOptional;
	}

	/**
	 * Metodo che setta l'optional collegato all'escursione
	 * 
	 * @param tblOptional
	 */
	public void setOptional(Optional tblOptional) {
		this.tblOptional = tblOptional;
	}

	/**
	 * @return gli optional dell'iscrizione
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblOptionalEscursione")
	public Set<OptionalIscrizione> getOptionalIscrizione() {
		return this.tblOptionalIscriziones;
	}

	/**
	 * Metodo che setta gli optional relativi all'iscrizione
	 * 
	 * @param tblOptionalIscriziones
	 */
	public void setOptionalIscrizione(Set<OptionalIscrizione> tblOptionalIscriziones) {
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

}
