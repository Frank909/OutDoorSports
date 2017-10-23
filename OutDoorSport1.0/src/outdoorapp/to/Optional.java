package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TipoOptionalTO;

/**
 * Classe che rappresenta lo stato dell'Optional. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Optional implements OptionalTO{

	private static final long serialVersionUID = -7068153413865370972L;
	private Integer idOptional;
	private StatoOptionalTO statoOptional;
	private TipoOptionalTO tipoOptional;
	private String nome;
	private String descrizione;

	Optional() {
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
	public StatoOptionalTO getStatoOptional() {
		return this.statoOptional;
	}

	/**
	 * Metodo che setta lo stato dell'optional
	 * 
	 * @param tblStatoOptional
	 */
	public void setStatoOptional(StatoOptionalTO statoOptional) {
		this.statoOptional = statoOptional;
	}

	/**
	 * @return il tipo dell'optional
	 */
	public TipoOptionalTO getTipoOptional() {
		return this.tipoOptional;
	}

	/**
	 * Metodo che setta il tipo dell'optional
	 * 
	 * @param tblTipoOptional
	 */
	public void setTipoOptional(TipoOptionalTO tipoOptional) {
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
