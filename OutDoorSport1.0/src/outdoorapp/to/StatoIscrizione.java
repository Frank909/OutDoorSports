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
 * Classe che rappresenta lo stato di StatoIscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class StatoIscrizione implements OutDoorSports{

	private static final long serialVersionUID = -8626250321580095948L;
	private Integer idStatoIscrizione;
	private String nome;

	public StatoIscrizione() {
	}

	public StatoIscrizione(String nome) {
		this.nome = nome;
	}

	/**
	 * @return l'id dello statoIscrizione
	 */
	public Integer getIdStatoIscrizione() {
		return this.idStatoIscrizione;
	}

	/**
	 * Metodo che setta lo stato dell'iscrizione
	 * 
	 * @param idStatoIscrizione
	 */
	public void setIdStatoIscrizione(Integer idStatoIscrizione) {
		this.idStatoIscrizione = idStatoIscrizione;
	}

	/**
	 * @return il nome dello stato iscrizione
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dello stato iscrizione
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
