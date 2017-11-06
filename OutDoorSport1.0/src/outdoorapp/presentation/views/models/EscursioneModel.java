package outdoorapp.presentation.views.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che implementa il modello che servirà per la rappresentazione
 * dei dati nella tabella di una View. In questa classe è presento il dato
 * Escursione. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class EscursioneModel {

	private SimpleIntegerProperty idEscursione;
	private SimpleObjectProperty<StatoEscursioneTO> statoEscursione;
	private SimpleObjectProperty<TipoEscursioneTO> tipoEscursione;
	private SimpleStringProperty nome;
	private SimpleStringProperty data;
	private SimpleIntegerProperty numberMin;
	private SimpleIntegerProperty numberMax;
	private SimpleDoubleProperty costo;
	private SimpleStringProperty descrizione;
	private SimpleObjectProperty<UtenteTO> utente;
	private SimpleStringProperty nomeStatoEscursione;
	private SimpleStringProperty nomeTipoEscursione;
	private EscursioneTO escursione;
	
	public EscursioneModel(){}
	
	public EscursioneModel(EscursioneTO e) {
		this.idEscursione = new SimpleIntegerProperty(e.getIdEscursione().intValue());
		this.statoEscursione = new SimpleObjectProperty<StatoEscursioneTO>(e.getStatoEscursione());
		this.tipoEscursione = new SimpleObjectProperty<TipoEscursioneTO>(e.getTipoEscursione());
		this.nome = new SimpleStringProperty(e.getNome());
		this.data = new SimpleStringProperty(e.getData());
		this.numberMin = new SimpleIntegerProperty(e.getNumberMin());
		this.numberMax = new SimpleIntegerProperty(e.getNumberMax());
		this.costo = new SimpleDoubleProperty(e.getCosto());
		this.descrizione = new SimpleStringProperty(e.getDescrizione());
		this.utente = new SimpleObjectProperty<>(e.getUtente());
		this.nomeTipoEscursione = new SimpleStringProperty(e.getTipoEscursione().getNome());
		this.nomeStatoEscursione = new SimpleStringProperty(e.getStatoEscursione().getNome());
		this.escursione = e;
	}
	
	/**
	 * Metodo che restituisce una istanza di tipo EscursioneTO in
	 * base al modello che viene passato
	 * 
	 * @param model
	 * @return istanza di EscursioneTO
	 */
	public EscursioneTO getEscursione(){
		return this.escursione;
	}

	/**
	 * @return il nome dello stato dell'escursione
	 */
	public String getNomeStatoEscursione() {
		return nomeStatoEscursione.get();
	}

	/**
	 * @return il nome del tipo dell'escursione
	 */
	public String getNomeTipoEscursione() {
		return nomeTipoEscursione.get();
	}

	/**
	 * @return il manager di escursione
	 */
	public UtenteTO getUtente() {
		return utente.get();
	}

	/**
	 * @return id dell'Escursione
	 */
	public int getIdEscursione() {
		return idEscursione.get();
	}

	/**
	 * @return lo stato dell'escursione
	 */
	public StatoEscursioneTO getStatoEscursione() {
		return statoEscursione.get();
	}
	
	/**
	 * @return il tipo dell'escursione
	 */
	public TipoEscursioneTO getTipoEscursione() {
		return tipoEscursione.get();
	}

	/**
	 * @return il nome dell'escursione
	 */
	public String getNome() {
		return nome.get();
	}

	/**
	 * @return la data dell'escursione
 	 */
	public String getData() {
		return data.get();
	}

	/**
	 * @return il numero minimo dei partecipanti a una escursione
	 */
	public int getNumberMin() {
		return numberMin.get();
	}

	/**
	 * @return il numero massimo dei partecipanti a una escursione
	 */
	public int getNumberMax() {
		return numberMax.get();
	}

	/**
	 * @return il costo di una escursione
	 */
	public double getCosto() {
		return costo.get();
	}

	/**
	 * @return la descrizione dell'escursione
	 */ 
	public String getDescrizione() {
		return descrizione.get();
	}
}
