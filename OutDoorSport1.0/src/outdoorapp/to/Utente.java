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

/**
 * Classe che rappresenta Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_utente", catalog = "outdoorsports", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "codice_fiscale")})
public class Utente implements OutDoorSports{

	private static final long serialVersionUID = 625888613100910416L;
	private Integer idUtente;
	private Ruoli tblRuoli;
	private StatoUtente tblStatoUtente;
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
	private Set<Iscrizione> tblIscriziones = new HashSet<Iscrizione>(0);
	private Partecipante tblPartecipante;
	private ManagerDiSistema tblMds;
	private Set<Escursione> tblEscursiones = new HashSet<Escursione>(0);
	private ManagerDiEscursione tblMde;

	public Utente() {
	}

	public Utente(Ruoli tblRuoli, StatoUtente tblStatoUtente, String username, String password, String nome,
			String cognome, String email, String codiceFiscale, Date dataNascita, String sesso, String indirizzo,
			String citta) {
		this.tblRuoli = tblRuoli;
		this.tblStatoUtente = tblStatoUtente;
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

	public Utente(Ruoli tblRuoli, StatoUtente tblStatoUtente, String username, String password, String nome,
			String cognome, String email, String codiceFiscale, Date dataNascita, String sesso, String indirizzo,
			String citta, Set<Iscrizione> tblIscriziones, Partecipante tblPartecipante, ManagerDiSistema tblMds,
			Set<Escursione> tblEscursiones, ManagerDiEscursione tblMde) {
		this.tblRuoli = tblRuoli;
		this.tblStatoUtente = tblStatoUtente;
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
		this.tblIscriziones = tblIscriziones;
		this.tblPartecipante = tblPartecipante;
		this.tblMds = tblMds;
		this.tblEscursiones = tblEscursiones;
		this.tblMde = tblMde;
	}

	/**
	 * @return l'id dell'utente
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_utente", unique = true, nullable = false)
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ruolo", nullable = false)
	public Ruoli getRuolo() {
		return this.tblRuoli;
	}

	/**
	 * Metodo che setta il ruolo relativo a un determinato utente
	 * 
	 * @param tblRuoli
	 */
	public void setRuolo(Ruoli tblRuoli) {
		this.tblRuoli = tblRuoli;
	}

	/**
	 * @return lo stato utente relativo a un determinato utente
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stato_utente", nullable = false)
	public StatoUtente getStatoUtente() {
		return this.tblStatoUtente;
	}

	/**
	 * Metodo che setta lo stato utente relativo a un determinato utente
	 * 
	 * @param tblStatoUtente
	 */
	public void setStatoUtente(StatoUtente tblStatoUtente) {
		this.tblStatoUtente = tblStatoUtente;
	}

	/**
	 * @return l'username dell'utente
	 */
	@Column(name = "username", nullable = false, length = 45)
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
	@Column(name = "password", nullable = false, length = 45)
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
	@Column(name = "nome", nullable = false, length = 45)
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
	@Column(name = "cognome", nullable = false, length = 45)
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
	@Column(name = "email", nullable = false, length = 45)
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
	@Column(name = "codice_fiscale", nullable = false, length = 16)
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
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascita", nullable = false, length = 10)
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
	@Column(name = "sesso", nullable = false, length = 1)
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
	@Column(name = "indirizzo", nullable = false, length = 45)
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
	@Column(name = "citta", nullable = false, length = 45)
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

	/**
	 * @return le iscrizioni dell'utente
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblUtente")
	public Set<Iscrizione> getIscrizioni() {
		return this.tblIscriziones;
	}

	/**
	 * Metodo che setta le iscrizioni di un utente
	 * 
	 * @param tblIscriziones
	 */
	public void setIscrizioni(Set<Iscrizione> tblIscriziones) {
		this.tblIscriziones = tblIscriziones;
	}

	/**
	 * @return il partecipante relativo all'utente
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tblUtente")
	public Partecipante getPartecipante() {
		return this.tblPartecipante;
	}

	/**
	 * Metodo che setta il partecipante relatico all'utente
	 * 
	 * @param tblPartecipante
	 */
	public void setPartecipante(Partecipante tblPartecipante) {
		this.tblPartecipante = tblPartecipante;
	}

	/**
	 * @return manager di sistema relativo all'utente
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tblUtente")
	public ManagerDiSistema getManagerDiSistema() {
		return this.tblMds;
	}

	/**
	 * Metodo che setta il manager di sistema relativo all'utente
	 * 
	 * @param tblMds
	 */
	public void setManagerDiSistema(ManagerDiSistema tblMds) {
		this.tblMds = tblMds;
	}

	/**
	 * @return le escursioni relative all'utente
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblUtente")
	public Set<Escursione> getEscursioni() {
		return this.tblEscursiones;
	}

	/**
	 * Metodo che setta le escursioni relative all'utente
	 * 
	 * @param tblEscursiones
	 */
	public void setEscursioni(Set<Escursione> tblEscursiones) {
		this.tblEscursiones = tblEscursiones;
	}

	/**
	 * @return manager di escursione relativo all'utente
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tblUtente")
	public ManagerDiEscursione getManagerDiEscursione() {
		return this.tblMde;
	}

	/**
	 * Metodo che setta il manager di escursione relativo all'utente
	 * 
	 * @param tblMde
	 */
	public void setManagerDiEscursione(ManagerDiEscursione tblMde) {
		this.tblMde = tblMde;
	}

}
