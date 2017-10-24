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
 * Classe che implementa lo stato di OptionalIscrizione.
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

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public IscrizioneTO getIscrizione() {
		return this.tblIscrizione;
	}

	@Override
	public void setIscrizione(IscrizioneTO tblIscrizione) {
		this.tblIscrizione = tblIscrizione;
	}

	@Override
	public OptionalEscursioneTO getOptionalEscursione() {
		return this.tblOptionalEscursione;
	}

	@Override
	public void setOptionalEscursione(OptionalEscursioneTO tblOptionalEscursione) {
		this.tblOptionalEscursione = tblOptionalEscursione;
	}

}
