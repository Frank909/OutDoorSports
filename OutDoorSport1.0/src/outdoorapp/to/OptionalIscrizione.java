package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalIscrizioneTO;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Classe che implementa lo stato di OptionalIscrizione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class OptionalIscrizione implements OptionalIscrizioneTO{

	private static final long serialVersionUID = -5812339531551921552L;
	private Integer idIscrizione;
	private Integer idOptionalEscursione;

	public OptionalIscrizione(Integer idIscrizione, Integer idOptionalEscursione) {
		this.idIscrizione = idIscrizione;
		this.idOptionalEscursione = idOptionalEscursione;
	}

	@Override
	public Integer getIdIscrizione() {
		return this.idIscrizione;
	}

	@Override
	public void setIdIscrizione(Integer idIscrizione) {
		this.idIscrizione = idIscrizione;
	}

	@Override
	public Integer getIdOptionalEscursione() {
		return this.idOptionalEscursione;
	}

	@Override
	public void setIdOptionalEscursione(Integer idOptionalEscursione) {
		this.idOptionalEscursione = idOptionalEscursione;
	}

}
