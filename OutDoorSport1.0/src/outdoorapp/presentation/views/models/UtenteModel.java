package outdoorapp.presentation.views.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
	
	public Integer getId() {
		return id.get();
	}
	
	public String getNome() {
		return nome.get();
	}
	
	public String getCognome() {
		return cognome.get();
	}
	public String getEmail() {
		return email.get();
	}
	public String getUsername() {
		return username.get();
	}
	public String getCodice_fiscale() {
		return codice_fiscale.get();
	}
	public String getData_nascita() {
		return data_nascita.get();
	}
	public String getSesso() {
		return sesso.get();
	}
	public String getIndirizzo() {
		return indirizzo.get();
	}
	public String getCitta() {
		return citta.get();
	}
	
	public String getPassword() {
		return password.get();
	}
}
