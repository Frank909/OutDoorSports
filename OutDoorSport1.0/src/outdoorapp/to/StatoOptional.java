package outdoorapp.to;

import outdoorapp.to.interfaces.StatoOptionalTO;

/**
 * Classe che implementa lo stato di StatoOptional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoOptional implements StatoOptionalTO{

	private static final long serialVersionUID = 4816235332067876389L;
	private Integer idStatoOptional;
	private String nome;

	StatoOptional() {
	}

	@Override
	public Integer getIdStatoOptional() {
		return this.idStatoOptional;
	}

	@Override
	public void setIdStatoOptional(Integer idStatoOptional) {
		this.idStatoOptional = idStatoOptional;
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
