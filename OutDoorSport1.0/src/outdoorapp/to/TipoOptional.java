package outdoorapp.to;
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
 * Classe che rappresenta lo stato del TipoOptional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_tipo_optional", catalog = "outdoorsports")
public class TipoOptional implements OutDoorSports{

	private static final long serialVersionUID = -7551037113422783655L;
	private Integer idTipoOptional;
	private String nome;
	private double costo;
	private Set<Optional> tblOptionals = new HashSet<Optional>(0);

	public TipoOptional() {
	}

	public TipoOptional(String nome, double costo) {
		this.nome = nome;
		this.costo = costo;
	}

	public TipoOptional(String nome, double costo, Set<Optional> tblOptionals) {
		this.nome = nome;
		this.costo = costo;
		this.tblOptionals = tblOptionals;
	}

	/**
	 * @return l'id del tipo optional
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_tipo_optional", unique = true, nullable = false)
	public Integer getIdTipoOptional() {
		return this.idTipoOptional;
	}

	/**
	 * Metodo che setta l'id del tipo optional
	 * 
	 * @param idTipoOptional
	 */
	public void setIdTipoOptional(Integer idTipoOptional) {
		this.idTipoOptional = idTipoOptional;
	}

	/**
	 * @return il nome del tipo optional
	 */
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome del tipo optional
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return il costo di un optional
	 */
	@Column(name = "costo", nullable = false, precision = 22, scale = 0)
	public double getCosto() {
		return this.costo;
	}

	/**
	 * Metodo che setta il costo di un optional
	 * 
	 * @param costo
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return gli optional collegati al tipo optional
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTipoOptional")
	public Set<Optional> getOptional() {
		return this.tblOptionals;
	}

	/**
	 * Metodo che setta gli optional collegati al tipo optional
	 * 
	 * @param tblOptionals
	 */
	public void setOptional(Set<Optional> tblOptionals) {
		this.tblOptionals = tblOptionals;
	}

}
