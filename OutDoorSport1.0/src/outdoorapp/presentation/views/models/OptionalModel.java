package outdoorapp.presentation.views.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import outdoorapp.to.interfaces.OptionalTO;

/**
 * Classe che implementa il modello che servirà per la rappresentazione
 * dei dati nella tabella di una View. In questa classe sono presentati
 * gli Optional
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class OptionalModel {

	private SimpleIntegerProperty idOptional;
	private SimpleStringProperty nome;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty nomeTipoOptional;
	private SimpleStringProperty costo;
	private OptionalTO optional;
	
	public OptionalModel(OptionalTO optional) {
		idOptional = new SimpleIntegerProperty(optional.getIdOptional());
		nome = new SimpleStringProperty(optional.getNome());
		descrizione = new SimpleStringProperty(optional.getDescrizione());
		nomeTipoOptional = new SimpleStringProperty(optional.getTipoOptional().getNome());
		costo = new SimpleStringProperty(Double.toString(optional.getTipoOptional().getCosto()));
		this.optional = optional;
	}

	/**
	 * @return l'id dell'optional
	 */
	public Integer getIdOptional() {
		return idOptional.get();
	}

	/**
	 * @return il nome dell'optional
	 */
	public String getNome() {
		return nome.get();
	}

	/**
	 * @return la descrizione dell'optional
	 */
	public String getDescrizione() {
		return descrizione.get();
	}

	/**
	 * @return il nome del tipo dell'optional
	 */
	public String getNomeTipoOptional() {
		return nomeTipoOptional.get();
	}

	/**
	 * @return il costo dell'optional
	 */
	public String getCosto() {
		return costo.get();
	}

	/**
	 * @return l'optional
	 */
	public OptionalTO getOptional() {
		return optional;
	}

	
}
