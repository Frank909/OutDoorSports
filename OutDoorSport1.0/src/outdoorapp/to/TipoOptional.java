package outdoorapp.to;

import outdoorapp.to.interfaces.TipoOptionalTO;

/**
 * Classe che implementa lo stato del TipoOptional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoOptional implements TipoOptionalTO{

	private static final long serialVersionUID = -7551037113422783655L;
	private Integer idTipoOptional;
	private String nome;
	private double costo;

	TipoOptional() {
	}

	@Override
	public Integer getIdTipoOptional() {
		return this.idTipoOptional;
	}

	@Override
	public void setIdTipoOptional(Integer idTipoOptional) {
		this.idTipoOptional = idTipoOptional;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	
	public void setCosto(double costo) {
		this.costo = costo;
	}
}
