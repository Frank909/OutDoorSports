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
