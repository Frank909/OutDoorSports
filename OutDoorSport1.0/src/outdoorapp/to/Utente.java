package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

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

/**
 * Classe che rappresenta Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class Utente implements OutDoorSports{

	private static final long serialVersionUID = 625888613100910416L;
	private Integer idUtente;
	private Ruoli ruolo;
	private StatoUtente statoUtente;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String codiceFiscale;
	private Date dataNascita;
	private String sesso;
	private String indirizzo;
	private String citta;

	public Utente() {
	}

	public Utente(Ruoli ruolo, StatoUtente statoUtente, String username, String password, String nome,
			String cognome, String email, String codiceFiscale, Date dataNascita, String sesso, String indirizzo,
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

	/**
	 * @return l'id dell'utente
	 */
	public Integer getIdUtente() {
		return this.idUtente;
	}

	/**
	 * Metodo che setta l'id dell'utente
	 * 
	 * @param idUtente
	 */
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	/**
	 * @return il ruolo relativo a un determinato utente
	 */
	public Ruoli getRuolo() {
		return this.ruolo;
	}

	/**
	 * Metodo che setta il ruolo relativo a un determinato utente
	 * 
	 * @param tblRuoli
	 */
	public void setRuolo(Ruoli ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return lo stato utente relativo a un determinato utente
	 */
	public StatoUtente getStatoUtente() {
		return this.statoUtente;
	}

	/**
	 * Metodo che setta lo stato utente relativo a un determinato utente
	 * 
	 * @param tblStatoUtente
	 */
	public void setStatoUtente(StatoUtente tblStatoUtente) {
		this.statoUtente = tblStatoUtente;
	}

	/**
	 * @return l'username dell'utente
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Metodo che setta l'username dell'utente
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return la password dell'utente
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Metodo che setta la password dell'utente
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return il nome dell'utente
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che setta il nome dell'utente
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return il cognome dell'utente
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Metodo che setta il cognome dell'utente
	 * 
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return l'email dell'utente
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo che setta l'email dell'utente
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return il codice fiscale dell'utente
	 */ 
	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	/**
	 * Metodo che setta il codice fiscale dell'utente
	 * 
	 * @param codiceFiscale
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return la data di nascia dell'utente
	 */
	public Date getDataNascita() {
		return this.dataNascita;
	}

	/**
	 * Metodo che setta la data di nascita dell'utente
	 * 
	 * @param dataNascita
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return il sesso dell'utente
	 */
	public String getSesso() {
		return this.sesso;
	}

	/**
	 * Metodo che setta il sesso dell'utente
	 * 
	 * @param sesso
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return l'indirizzo dell'utente
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}

	/**
	 * Metodo che setta l'indirizzo dell'utente
	 * 
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return la città dell'utente
	 */
	public String getCitta() {
		return this.citta;
	}

	/**
	 * Metodo che setta la città dell'utente
	 * 
	 * @param citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
}
