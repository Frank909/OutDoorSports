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
 * Classe che rappresenta StatoEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class StatoEscursione implements OutDoorSports{

	private static final long serialVersionUID = -3317221757629047387L;
	private Integer idStatoEscursione;
	private String nome;
	private Set<Escursione> tblEscursiones = new HashSet<Escursione>(0);

	public StatoEscursione() {
	}

	public StatoEscursione(String nome) {
		this.nome = nome;
	}

	public StatoEscursione(String nome, Set<Escursione> tblEscursiones) {
		this.nome = nome;
		this.tblEscursiones = tblEscursiones;
	}

	/**
	 * @return l'id dello stato escursione
	 */
	public Integer getIdStatoEscursione() {
		return this.idStatoEscursione;
	}

	/**
	 * Metodo che setta lo stato dell'escursione
	 * 
	 * @param idStatoEscursione
	 */
	public void setIdStatoEscursione(Integer idStatoEscursione) {
		this.idStatoEscursione = idStatoEscursione;
	}

	/**
	 * @return il nome dello stato escursione
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dello stato escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return le escursioni associate allo stato escursione
	 */
	public Set<Escursione> getEscursione() {
		return this.tblEscursiones;
	}

	/**
	 * Metodo che setta le escursioni associate allo stato escursione
	 * 
	 * @param tblEscursiones
	 */
	public void setTblEscursiones(Set<Escursione> tblEscursiones) {
		this.tblEscursiones = tblEscursiones;
	}

}
