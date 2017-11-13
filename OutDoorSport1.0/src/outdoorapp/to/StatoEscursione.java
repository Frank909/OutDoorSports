package outdoorapp.to;

import outdoorapp.to.interfaces.StatoEscursioneTO;

/**
 * Classe che implementa StatoEscursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class StatoEscursione implements StatoEscursioneTO{

	private static final long serialVersionUID = -3317221757629047387L;
	private Integer idStatoEscursione;
	private String nome;

	StatoEscursione() {
	}

	@Override
	public Integer getIdStatoEscursione() {
		return this.idStatoEscursione;
	}

	@Override
	public void setIdStatoEscursione(Integer idStatoEscursione) {
		this.idStatoEscursione = idStatoEscursione;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
}
