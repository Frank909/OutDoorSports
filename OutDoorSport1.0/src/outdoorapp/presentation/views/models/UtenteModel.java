package outdoorapp.presentation.views.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe che implementa il modello che servirà per la rappresentazione
 * dei dati nella tabella di una View. In questa classe è presentato l'Utente
 * generico. La classe è astratta perchè l'Utente loggato non potrà mai essere
 * un Utente generico, ma almeno un Manager di Sistema, oppure un Manager di Escursione,
 * oppure un Partecipante
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

abstract class UtenteModel {

	protected SimpleIntegerProperty id;
	protected SimpleStringProperty nome;
	protected SimpleStringProperty cognome;
	protected SimpleStringProperty email;
	protected SimpleStringProperty username;
	protected SimpleStringProperty password;
	protected SimpleStringProperty codice_fiscale;
	protected SimpleStringProperty data_nascita;
	protected SimpleStringProperty sesso;
	protected SimpleStringProperty indirizzo;
	protected SimpleStringProperty citta;
	
	/**
	 * @return l'id dell'Utente
	 */
	public Integer getId() {
		return id.get();
	}
	
	/**
	 * @return il nome dell'Utente
	 */
	public String getNome() {
		return nome.get();
	}
	
	/**
	 * @return il cognome dell'Utente
	 */
	public String getCognome() {
		return cognome.get();
	}
	
	/**
	 * @return l'email dell'Utente
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * @return l'username dell'Utente
	 */
	public String getUsername() {
		return username.get();
	}
	
	/**
	 * @return il codice fiscale dell'Utente
	 */
	public String getCodice_fiscale() {
		return codice_fiscale.get();
	}
	
	/**
	 * @return la data di nascita dell'Utente
	 */
	public String getData_nascita() {
		return data_nascita.get();
	}
	
	/**
	 * @return il sesso dell'Utente
	 */
	public String getSesso() {
		return sesso.get();
	}
	
	/**
	 * @return l'indirizzo dell'Utente
	 */
	public String getIndirizzo() {
		return indirizzo.get();
	}
	
	/**
	 * @return la città dell'Utente
	 */
	public String getCitta() {
		return citta.get();
	}
	
	/**
	 * @return la password dell'Utente
	 */
	public String getPassword() {
		return password.get();
	}
}
