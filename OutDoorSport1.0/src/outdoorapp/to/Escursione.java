package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.Set;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che rappresenta lo stato dell'Escursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Escursione implements EscursioneTO{

	private static final long serialVersionUID = -8060673121786005549L;
	private Integer idEscursione;
	private StatoEscursioneTO statoEscursione;
	private TipoEscursioneTO tipoEscursione;
	private UtenteTO utente;
	private String nome;
	private Date data;
	private int numberMin;
	private int numberMax;
	private double costo;
	private String descrizione;
	private Set<OptionalTO> optionals;

	Escursione() {
	}

	/**
	 * @return l'id dell'Escursione
	 */
	public Integer getIdEscursione() {
		return this.idEscursione;
	}

	/**
	 * Metodo che setta l'id dell'escursione
	 * 
	 * @param idEscursione
	 */
	public void setIdEscursione(Integer idEscursione) {
		this.idEscursione = idEscursione;
	}

	/**
	 * @return lo stato dell'Escursione
	 */
	public StatoEscursioneTO getStatoEscursione() {
		return this.statoEscursione;
	}

	/**
	 * Metodo che setta lo stato dell'Escursione
	 * 
	 * @param tblStatoEscursione
	 */
	public void setStatoEscursione(StatoEscursioneTO statoEscursione) {
		this.statoEscursione = statoEscursione;
	}

	/**
	 * @return il tipo dell'escursione
	 */
	public TipoEscursioneTO getTipoEscursione() {
		return this.tipoEscursione;
	}

	/**
	 * Metodo che setta il Tipo dell'Escursione
	 * 
	 * @param tblTipoEscursione
	 */
	public void setTipoEscursione(TipoEscursioneTO tipoEscursione) {
		this.tipoEscursione = tipoEscursione;
	}

	/**
	 * @return l'utente associato a una determinata Escursione
	 */
	public UtenteTO getUtente() {
		return this.utente;
	}

	/**
	 * Metodo che setta l'utente associato all'escursione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(UtenteTO utente) {
		this.utente = utente;
	}

	/**
	 * @return il nome dell'escursione
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dell'escursione
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return la data dell'escursione
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Metodo che setta la data dell'escursione
	 * 
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return numero minimo di partecipanti della escursione
	 */
	public int getNumberMin() {
		return this.numberMin;
	}

	/**
	 * Metodo che setta il numero minimo dei partecipanti della escursione
	 * 
	 * @param numberMin
	 */
	public void setNumberMin(int numberMin) {
		this.numberMin = numberMin;
	}

	/**
	 * @return numero massimo di partecipanti della escursione
	 */
	public int getNumberMax() {
		return this.numberMax;
	}

	/**
	 * Metodo che setta il numero massimo dei partecipanti alla escursione
	 * 
	 * @param numberMax
	 */
	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	/**
	 * @return il costo della escursione
	 */
	public double getCosto() {
		return this.costo;
	}

	/**
	 * Metodo che setta il costo della escursione
	 * 
	 * @param costo
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return la descrizione della escursione
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Metodo che setta la descrizione della escursione
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return un set di optional
	 */
	public Set<OptionalTO> getOptionals() {
		return optionals;
	}

	/**
	 * Metodo che setta gli optional per una determinata escursione
	 * 
	 * @param optionals
	 */
	public void setOptionals(Set<OptionalTO> optionals) {
		this.optionals = optionals;
	}

}
