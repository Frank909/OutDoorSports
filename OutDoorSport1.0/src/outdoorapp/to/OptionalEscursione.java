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
 * Classe che implementa lo stato di OptionalEscursione.
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

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public EscursioneTO getEscursione() {
		return this.tblEscursione;
	}

	@Override
	public void setEscursione(EscursioneTO tblEscursione) {
		this.tblEscursione = tblEscursione;
	}

	@Override
	public OptionalTO getOptional() {
		return this.tblOptional;
	}

	@Override
	public void setOptional(OptionalTO tblOptional) {
		this.tblOptional = tblOptional;
	}

	@Override
	public Set<OptionalIscrizioneTO> getOptionalIscrizione() {
		return this.tblOptionalIscriziones;
	}

	@Override
	public void setOptionalIscrizione(Set<OptionalIscrizioneTO> tblOptionalIscriziones) {
		this.tblOptionalIscriziones = tblOptionalIscriziones;
	}

}
