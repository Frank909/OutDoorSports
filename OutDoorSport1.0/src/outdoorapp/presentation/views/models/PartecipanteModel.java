package outdoorapp.presentation.views.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;

/**
 * Classe che implementa il modello che servirà per la rappresentazione
 * dei dati nella tabella di una View. In questa classe è presentato il
 * Partecipante. La classe estende il modello dell'Utente perchè
 * avrà dei dati in comune.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class PartecipanteModel extends UtenteModel{

	private SimpleStringProperty nTesseraSanitaria;
	private SimpleStringProperty certificatoSRC;
	private SimpleStringProperty dataCertificatoSRC;
	private PartecipanteTO partecipante;
	
	public PartecipanteModel(PartecipanteTO partecipante) {
		this.id = new SimpleIntegerProperty(partecipante.getIdUtente());
		this.nome = new SimpleStringProperty(partecipante.getNome());
		this.cognome = new SimpleStringProperty(partecipante.getCognome());
		this.email = new SimpleStringProperty(partecipante.getEmail());
		this.username = new SimpleStringProperty(partecipante.getUsername());
		this.password = new SimpleStringProperty(partecipante.getPassword());
		this.codice_fiscale = new SimpleStringProperty(partecipante.getCodiceFiscale());
		this.data_nascita = new SimpleStringProperty(partecipante.getDataNascita());
		this.sesso = new SimpleStringProperty(partecipante.getSesso());
		this.indirizzo = new SimpleStringProperty(partecipante.getIndirizzo());
		this.citta = new SimpleStringProperty(partecipante.getCitta());
		this.nTesseraSanitaria = new SimpleStringProperty(partecipante.getTesseraSanitaria());
		this.certificatoSRC = new SimpleStringProperty(partecipante.getCertificatoSrc());
		this.dataCertificatoSRC = new SimpleStringProperty(partecipante.getDataCertificatoSrc());
		
		this.partecipante = partecipante;
	}

	/**
	 * @return il numero di tessera sanitaria del partecipante
	 */
	public String getnTesseraSanitaria() {
		return nTesseraSanitaria.get();
	}

	/**
	 * @return il percorso del file del certificato SRC
	 */
	public String getCertificatoSRC() {
		return certificatoSRC.get();
	}

	/**
	 * @return la data di validità del certificato src
	 */
	public String getDataCertificatoSRC() {
		return dataCertificatoSRC.get();
	}
}
