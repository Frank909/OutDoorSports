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
import outdoorapp.to.interfaces.TipoEscursioneTO;

/**
 * Classe che implementa lo stato di TipoEscursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class TipoEscursione implements TipoEscursioneTO{

	private static final long serialVersionUID = -8638030899448668159L;
	private Integer idTipoEscursione;
	private String nome;
	private String descrizione;

	TipoEscursione() {
	}

	@Override
	public Integer getIdTipoEscursione() {
		return this.idTipoEscursione;
	}

	@Override
	public void setIdTipoEscursione(Integer idTipoEscursione) {
		this.idTipoEscursione = idTipoEscursione;
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
	public String getDescrizione() {
		return this.descrizione;
	}

	@Override
	public void setDescrizione(String decrizione) {
		this.descrizione = decrizione;
	}
}
