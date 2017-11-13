package outdoorapp.to;

import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoOptionalTO;

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
	private EscursioneTO escursione;
	private OptionalTO optional;
	private StatoOptionalTO statoOptional;
	
	OptionalEscursione() {}
	
	OptionalEscursione(Integer id, EscursioneTO escursione, OptionalTO optional, StatoOptionalTO statoOptional) {
		this.id = id;
		this.escursione = escursione;
		this.optional = optional;
		this.statoOptional = statoOptional;
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
		return this.escursione;
	}

	@Override
	public void setEscursione(EscursioneTO escursione) {
		this.escursione = escursione;
	}

	@Override
	public OptionalTO getOptional() {
		return this.optional;
	}

	@Override
	public void setOptional(OptionalTO optional) {
		this.optional = optional;
	}

	@Override
	public StatoOptionalTO getStatoOptional() {
		return this.statoOptional;
	}

	@Override
	public void setStatoOptional(StatoOptionalTO statoOptional) {
		this.statoOptional = statoOptional;
	}
}
