package outdoorapp.to;

import outdoorapp.to.interfaces.RuoliTO;

/**
 * Classe che implementa il ruolo dell'Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Ruoli implements RuoliTO{

	private static final long serialVersionUID = -5565024038693813756L;
	private Integer idRuolo;
	private String nome;

	Ruoli() {
	}

	Ruoli(String nome) {
		this.nome = nome;
	}

	@Override
	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	@Override
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
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
