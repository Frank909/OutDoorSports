package outdoorapp.to;

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
