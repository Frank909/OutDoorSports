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

import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.StatoOptionalTO;

/**
 * Classe che rappresenta lo stato di StatoOptional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoOptional implements StatoOptionalTO{

	private static final long serialVersionUID = 4816235332067876389L;
	private Integer idStatoOptional;
	private String nome;

	StatoOptional() {
	}

	/**
	 * @return l'id dello stato dell'optional
	 */
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
}
