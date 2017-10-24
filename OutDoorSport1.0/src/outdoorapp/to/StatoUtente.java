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
import outdoorapp.to.interfaces.StatoUtenteTO;

/**
 * Classe che implementa lo stato dell'Utente.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class StatoUtente implements StatoUtenteTO{

	private static final long serialVersionUID = -2947070361442588483L;
	private Integer idStatoUtente;
	private String nome;

	StatoUtente() {
	}

	StatoUtente(String nome) {
		this.nome = nome;
	}

	@Override
	public Integer getIdStatoUtente() {
		return this.idStatoUtente;
	}

	@Override
	public void setIdStatoUtente(Integer idStatoUtente) {
		this.idStatoUtente = idStatoUtente;
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
