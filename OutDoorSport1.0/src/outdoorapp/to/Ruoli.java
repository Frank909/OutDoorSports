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
