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
 * Classe che rappresenta lo stato dell'Optional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_optional", catalog = "outdoorsports")
public class Optional implements OutDoorSports{

	private static final long serialVersionUID = -7068153413865370972L;
	private Integer idOptional;
	private StatoOptional tblStatoOptional;
	private TipoOptional tblTipoOptional;
	private String nome;
	private String descrizione;
	private Set<OptionalEscursione> tblOptionalEscursiones = new HashSet<OptionalEscursione>(0);

	public Optional() {
	}

	public Optional(StatoOptional tblStatoOptional, TipoOptional tblTipoOptional, String nome,
			String descrizione) {
		this.tblStatoOptional = tblStatoOptional;
		this.tblTipoOptional = tblTipoOptional;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Optional(StatoOptional tblStatoOptional, TipoOptional tblTipoOptional, String nome,
			String descrizione, Set<OptionalEscursione> tblOptionalEscursiones) {
		this.tblStatoOptional = tblStatoOptional;
		this.tblTipoOptional = tblTipoOptional;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tblOptionalEscursiones = tblOptionalEscursiones;
	}

	/**
	 * @return l'id dell'optional
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_optional", unique = true, nullable = false)
	public Integer getIdOptional() {
		return this.idOptional;
	}

	/**
	 * Metodo che setta l'id dell'Optional
	 * 
	 * @param idOptional
	 */
	public void setIdOptional(Integer idOptional) {
		this.idOptional = idOptional;
	}

	/**
	 * @return lo stato dell'optional
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stato_optional", nullable = false)
	public StatoOptional getStatoOptional() {
		return this.tblStatoOptional;
	}

	/**
	 * Metodo che setta lo stato dell'optional
	 * 
	 * @param tblStatoOptional
	 */
	public void setStatoOptional(StatoOptional tblStatoOptional) {
		this.tblStatoOptional = tblStatoOptional;
	}

	/**
	 * @return il tipo dell'optional
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_optional", nullable = false)
	public TipoOptional getTipoOptional() {
		return this.tblTipoOptional;
	}

	/**
	 * Metodo che setta il tipo dell'optional
	 * 
	 * @param tblTipoOptional
	 */
	public void setTipoOptional(TipoOptional tblTipoOptional) {
		this.tblTipoOptional = tblTipoOptional;
	}

	/**
	 * @return il nome dell'optional
	 */
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dell'optional
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return la descrizione dell'optional
	 */
	@Column(name = "descrizione", nullable = false, length = 65535)
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Metodo che setta la descrizione dell'optional
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return gli optional relativi a una determinata escursione
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblOptional")
	public Set<OptionalEscursione> getOptionalEscursione() {
		return this.tblOptionalEscursiones;
	}

	/**
	 * Metodo che setta gli optional per una relativa escursione
	 * 
	 * @param tblOptionalEscursiones
	 */
	public void setOptionalEscursione(Set<OptionalEscursione> tblOptionalEscursiones) {
		this.tblOptionalEscursiones = tblOptionalEscursiones;
	}

}
