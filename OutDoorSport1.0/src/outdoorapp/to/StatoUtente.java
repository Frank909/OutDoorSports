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
 * Classe che rappresenta lo stato dell'Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_stato_utente", catalog = "outdoorsports")
public class StatoUtente implements OutDoorSports{

	private static final long serialVersionUID = -2947070361442588483L;
	private Integer idStatoUtente;
	private String nome;
	private Set<Utente> tblUtentes = new HashSet<Utente>(0);

	public StatoUtente() {
	}

	public StatoUtente(String nome) {
		this.nome = nome;
	}

	public StatoUtente(String nome, Set<Utente> tblUtentes) {
		this.nome = nome;
		this.tblUtentes = tblUtentes;
	}

	/**
	 * @return l'id dello stato utente
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_stato_utente", unique = true, nullable = false)
	public Integer getIdStatoUtente() {
		return this.idStatoUtente;
	}

	/**
	 * Metodo che setta l'id dello stato utente
	 * 
	 * @param idStatoUtente
	 */
	public void setIdStatoUtente(Integer idStatoUtente) {
		this.idStatoUtente = idStatoUtente;
	}

	/**
	 * @return il nome dello stato utente
	 */
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dello stato utente
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return gli utenti associati a un determinato stato
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblStatoUtente")
	public Set<Utente> getUtente() {
		return this.tblUtentes;
	}

	/**
	 * Metodo che setta gli utenti associati a un determinato stato
	 * 
	 * @param tblUtentes
	 */
	public void setUtente(Set<Utente> tblUtentes) {
		this.tblUtentes = tblUtentes;
	}

}
