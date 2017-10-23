package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalIscrizioneTO;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Classe che rappresenta lo stato di OptionalIscrizione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class OptionalIscrizione implements OptionalIscrizioneTO{

	private static final long serialVersionUID = -5812339531551921552L;
	private Integer id;
	private IscrizioneTO tblIscrizione;
	private OptionalEscursioneTO tblOptionalEscursione;

	OptionalIscrizione() {
	}

	OptionalIscrizione(IscrizioneTO tblIscrizione, OptionalEscursioneTO tblOptionalEscursione) {
		this.tblIscrizione = tblIscrizione;
		this.tblOptionalEscursione = tblOptionalEscursione;
	}

	/**
	 * @return l'id di optional iscrizione
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo che setta l'id di OptionalIscrizione
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return l'iscrizione relativa all'optional
	 */
	public IscrizioneTO getIscrizione() {
		return this.tblIscrizione;
	}

	/**
	 * Metodo che setta l'iscrizione relativa all'optional
	 * 
	 * @param tblIscrizione
	 */
	public void setIscrizione(IscrizioneTO tblIscrizione) {
		this.tblIscrizione = tblIscrizione;
	}

	/**
	 * @return l'optional escursione relativo all'iscrizione
	 */
	public OptionalEscursioneTO getOptionalEscursione() {
		return this.tblOptionalEscursione;
	}

	/**
	 * Metodo che setta una optional escursione relativo all'iscrizione
	 * 
	 * @param tblOptionalEscursione
	 */
	public void setOptionalEscursione(OptionalEscursioneTO tblOptionalEscursione) {
		this.tblOptionalEscursione = tblOptionalEscursione;
	}

}
