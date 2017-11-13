package outdoorapp.to;

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
