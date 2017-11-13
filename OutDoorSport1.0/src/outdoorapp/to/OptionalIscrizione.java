package outdoorapp.to;

import outdoorapp.to.interfaces.OptionalIscrizioneTO;

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
