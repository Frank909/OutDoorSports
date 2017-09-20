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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe che rappresenta TipoEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_tipo_escursione", catalog = "outdoorsports")
public class TipoEscursione implements OutDoorSports{

	private static final long serialVersionUID = -8638030899448668159L;
	private Integer idTipoEscursione;
	private String nome;
	private String decrizione;
	private Set<Escursione> tblEscursiones = new HashSet<Escursione>(0);

	public TipoEscursione() {
	}

	public TipoEscursione(String nome, String decrizione) {
		this.nome = nome;
		this.decrizione = decrizione;
	}

	public TipoEscursione(String nome, String decrizione, Set<Escursione> tblEscursiones) {
		this.nome = nome;
		this.decrizione = decrizione;
		this.tblEscursiones = tblEscursiones;
	}

	/**
	 * @return l'id del tipo escursione
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_tipo_escursione", unique = true, nullable = false)
	public Integer getIdTipoEscursione() {
		return this.idTipoEscursione;
	}

	/**
	 * Metodo che setta l'id del tipo escursione
	 * 
	 * @param idTipoEscursione
	 */
	public void setIdTipoEscursione(Integer idTipoEscursione) {
		this.idTipoEscursione = idTipoEscursione;
	}

	/**
	 * @return il nome del tipo escursione
	 */
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo che setta il nome del tipo escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** 
	 * @return la descrizione del tipoEscursione
	 */
	@Column(name = "decrizione", nullable = false, length = 65535)
	public String getDecrizione() {
		return this.decrizione;
	}

	/**
	 * Metodo che setta la descrizione del tipo escursione
	 * 
	 * @param decrizione
	 */
	public void setDecrizione(String decrizione) {
		this.decrizione = decrizione;
	}

	/**
	 * @return le escursioni collegate a un determinato tipo di escursione
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTipoEscursione")
	public Set<Escursione> getEscursioni() {
		return this.tblEscursiones;
	}

	/**
	 * Metodo che setta le escursioni collegate a un determinato tipo di escursione
	 * 
	 * @param tblEscursiones
	 */
	public void setEscursioni(Set<Escursione> tblEscursiones) {
		this.tblEscursiones = tblEscursiones;
	}
}
