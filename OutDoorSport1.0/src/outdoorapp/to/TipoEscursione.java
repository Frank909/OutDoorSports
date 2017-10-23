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
import outdoorapp.to.interfaces.TipoEscursioneTO;

/**
 * Classe che rappresenta TipoEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoEscursione implements TipoEscursioneTO{

	private static final long serialVersionUID = -8638030899448668159L;
	private Integer idTipoEscursione;
	private String nome;
	private String descrizione;

	TipoEscursione() {
	}

	/**
	 * @return l'id del tipo escursione
	 */
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
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Metodo che setta la descrizione del tipo escursione
	 * 
	 * @param decrizione
	 */
	public void setDescrizione(String decrizione) {
		this.descrizione = decrizione;
	}
}
