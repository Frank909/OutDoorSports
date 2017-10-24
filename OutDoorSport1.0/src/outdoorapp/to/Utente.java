package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import outdoorapp.to.interfaces.RuoliTO;
import outdoorapp.to.interfaces.StatoUtenteTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che implementa Utente.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Utente implements UtenteTO{

	private static final long serialVersionUID = 625888613100910416L;
	private Integer idUtente;
	private RuoliTO ruolo;
	private StatoUtenteTO statoUtente;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String codiceFiscale;
	private String dataNascita;
	private String sesso;
	private String indirizzo;
	private String citta;

	Utente() {
	}

	Utente(Ruoli ruolo, StatoUtente statoUtente, String username, String password, String nome,
			String cognome, String email, String codiceFiscale, String dataNascita, String sesso, String indirizzo,
			String citta) {
		this.ruolo = ruolo;
		this.statoUtente = statoUtente;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}

	@Override
	public Integer getIdUtente() {
		return this.idUtente;
	}

	@Override
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public RuoliTO getRuolo() {
		return this.ruolo;
	}

	@Override
	public void setRuolo(RuoliTO ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public StatoUtenteTO getStatoUtente() {
		return this.statoUtente;
	}

	@Override
	public void setStatoUtente(StatoUtenteTO tblStatoUtente) {
		this.statoUtente = tblStatoUtente;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
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
	public String getCognome() {
		return this.cognome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	@Override
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String getDataNascita() {
		return this.dataNascita;
	}

	@Override
	public void setDataNascita(String localDate) {
		this.dataNascita = localDate;
	}

	@Override
	public String getSesso() {
		return this.sesso;
	}

	@Override
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	@Override
	public String getIndirizzo() {
		return this.indirizzo;
	}

	@Override
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String getCitta() {
		return this.citta;
	}

	@Override
	public void setCitta(String citta) {
		this.citta = citta;
	}
}
