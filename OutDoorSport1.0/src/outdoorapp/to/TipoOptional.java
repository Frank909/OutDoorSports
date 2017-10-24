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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import outdoorapp.to.interfaces.OutDoorSports;
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
