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
