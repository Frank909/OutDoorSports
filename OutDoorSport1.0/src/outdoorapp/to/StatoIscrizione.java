package outdoorapp.to;

import outdoorapp.to.interfaces.StatoIscrizioneTO;

/**
 * Classe che implementa lo stato di StatoIscrizione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoIscrizione implements StatoIscrizioneTO{

	private static final long serialVersionUID = -8626250321580095948L;
	private Integer idStatoIscrizione;
	private String nome;

	StatoIscrizione() {
	}

	StatoIscrizione(String nome) {
		this.nome = nome;
	}

	@Override
	public Integer getIdStatoIscrizione() {
		return this.idStatoIscrizione;
	}

@Override
	public void setIdStatoIscrizione(Integer idStatoIscrizione) {
		this.idStatoIscrizione = idStatoIscrizione;
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
