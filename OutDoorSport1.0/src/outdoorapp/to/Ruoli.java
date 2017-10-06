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
 * Classe che rappresenta il ruolo dell'Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class Ruoli implements OutDoorSports{

	private static final long serialVersionUID = -5565024038693813756L;
	private Integer idRuolo;
	private String nome;

	public Ruoli() {
	}

	public Ruoli(String nome) {
		this.nome = nome;
	}

	/**
	 * @return l'id del ruolo dell'utente
	 */
	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	/**
	 * Metodo che setta il ruolo dell'utente
	 * 
	 * @param idRuolo
	 */
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	/**
	 * @return il nome del ruolo 
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome del ruolo
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
