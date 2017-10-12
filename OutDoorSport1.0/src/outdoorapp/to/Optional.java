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

public class Optional implements OutDoorSports{

	private static final long serialVersionUID = -7068153413865370972L;
	private Integer idOptional;
	private StatoOptional statoOptional;
	private TipoOptional tipoOptional;
	private String nome;
	private String descrizione;

	public Optional() {
	}

	/**
	 * @return l'id dell'optional
	 */
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
	public StatoOptional getStatoOptional() {
		return this.statoOptional;
	}

	/**
	 * Metodo che setta lo stato dell'optional
	 * 
	 * @param tblStatoOptional
	 */
	public void setStatoOptional(StatoOptional statoOptional) {
		this.statoOptional = statoOptional;
	}

	/**
	 * @return il tipo dell'optional
	 */
	public TipoOptional getTipoOptional() {
		return this.tipoOptional;
	}

	/**
	 * Metodo che setta il tipo dell'optional
	 * 
	 * @param tblTipoOptional
	 */
	public void setTipoOptional(TipoOptional tipoOptional) {
		this.tipoOptional = tipoOptional;
	}

	/**
	 * @return il nome dell'optional
	 */
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
}
