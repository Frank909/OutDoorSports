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

public class StatoUtente implements OutDoorSports{

	private static final long serialVersionUID = -2947070361442588483L;
	private Integer idStatoUtente;
	private String nome;

	public StatoUtente() {
	}

	public StatoUtente(String nome) {
		this.nome = nome;
	}

	/**
	 * @return l'id dello stato utente
	 */
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
}
