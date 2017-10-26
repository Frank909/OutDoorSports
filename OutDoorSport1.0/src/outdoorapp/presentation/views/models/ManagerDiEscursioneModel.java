package outdoorapp.presentation.views.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

public class ManagerDiEscursioneModel extends UtenteModel{

	private SimpleDoubleProperty stipendio;
	private ManagerDiEscursioneTO mde;
	
	public ManagerDiEscursioneModel(ManagerDiEscursioneTO mde) {
		this.id = new SimpleIntegerProperty(mde.getIdUtente());
		this.nome = new SimpleStringProperty(mde.getNome());
		this.cognome = new SimpleStringProperty(mde.getCognome());
		this.email = new SimpleStringProperty(mde.getEmail());
		this.username = new SimpleStringProperty(mde.getUsername());
		this.password = new SimpleStringProperty(mde.getPassword());
		this.codice_fiscale = new SimpleStringProperty(mde.getCodiceFiscale());
		this.data_nascita = new SimpleStringProperty(mde.getDataNascita());
		this.sesso = new SimpleStringProperty(mde.getSesso());
		this.indirizzo = new SimpleStringProperty(mde.getIndirizzo());
		this.citta = new SimpleStringProperty(mde.getCitta());
		this.stipendio = new SimpleDoubleProperty(mde.getStipendio());
		
		this.mde = mde;
	}

	public Double getStipendio() {
		return stipendio.get();
	}
}
