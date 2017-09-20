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
 * Classe che rappresenta lo stato di StatoOptional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_stato_optional", catalog = "outdoorsports")
public class StatoOptional implements OutDoorSports{

	private static final long serialVersionUID = 4816235332067876389L;
	private Integer idStatoOptional;
	private String nome;
	private Set<Optional> tblOptionals = new HashSet<Optional>(0);

	public StatoOptional() {
	}

	public StatoOptional(String nome) {
		this.nome = nome;
	}

	public StatoOptional(String nome, Set<Optional> tblOptionals) {
		this.nome = nome;
		this.tblOptionals = tblOptionals;
	}

	/**
	 * @return l'id dello stato dell'optional
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_stato_optional", unique = true, nullable = false)
	public Integer getIdStatoOptional() {
		return this.idStatoOptional;
	}

	/**
	 * Metodo che setta l'id dello stato dell'optional
	 * 
	 * @param idStatoOptional
	 */
	public void setIdStatoOptional(Integer idStatoOptional) {
		this.idStatoOptional = idStatoOptional;
	}

	/**
	 * @return il nome dello stato dell'optional
	 */
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dello stato dell'optional
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return gli optional relativi al determinato stato
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblStatoOptional")
	public Set<Optional> getOptional() {
		return this.tblOptionals;
	}

	/**
	 * Metodo che setta gli optional relativi al determinato stato
	 * 
	 * @param tblOptionals
	 */
	public void setOptional(Set<Optional> tblOptionals) {
		this.tblOptionals = tblOptionals;
	}

}
