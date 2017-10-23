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

import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalIscrizioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Classe che rappresenta lo stato di OptionalEscursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class OptionalEscursione implements OptionalEscursioneTO{

	private static final long serialVersionUID = -2717683768500440335L;
	private Integer id;
	private EscursioneTO tblEscursione;
	private OptionalTO tblOptional;
	private Set<OptionalIscrizioneTO> tblOptionalIscriziones = new HashSet<OptionalIscrizioneTO>(0);

	OptionalEscursione() {
	}

	OptionalEscursione(EscursioneTO tblEscursione, OptionalTO tblOptional) {
		this.tblEscursione = tblEscursione;
		this.tblOptional = tblOptional;
	}

	OptionalEscursione(EscursioneTO tblEscursione, OptionalTO tblOptional,
			Set<OptionalIscrizioneTO> tblOptionalIscriziones) {
		this.tblEscursione = tblEscursione;
		this.tblOptional = tblOptional;
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

	/**
	 * @return l'id di OptionalEscursione
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo che setta l'id di OptionalEscursione
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return l'escursione collegata all'optional
	 */
	public EscursioneTO getEscursione() {
		return this.tblEscursione;
	}

	/**
	 * Metodo che setta l'escursione collegata all'optional
	 * 
	 * @param tblEscursione
	 */
	public void setEscursione(EscursioneTO tblEscursione) {
		this.tblEscursione = tblEscursione;
	}

	/**
	 * @return l'optional collegato all'escursione
	 */
	public OptionalTO getOptional() {
		return this.tblOptional;
	}

	/**
	 * Metodo che setta l'optional collegato all'escursione
	 * 
	 * @param tblOptional
	 */
	public void setOptional(OptionalTO tblOptional) {
		this.tblOptional = tblOptional;
	}

	/**
	 * @return gli optional dell'iscrizione
	 */
	public Set<OptionalIscrizioneTO> getOptionalIscrizione() {
		return this.tblOptionalIscriziones;
	}

	/**
	 * Metodo che setta gli optional relativi all'iscrizione
	 * 
	 * @param tblOptionalIscriziones
	 */
	public void setOptionalIscrizione(Set<OptionalIscrizioneTO> tblOptionalIscriziones) {
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

}
