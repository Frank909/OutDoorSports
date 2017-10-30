package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.Set;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.TipoEscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che implementa lo stato dell'Escursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Escursione implements EscursioneTO{

	private static final long serialVersionUID = -8060673121786005549L;
	private Integer idEscursione;
	private StatoEscursioneTO statoEscursione;
	private TipoEscursioneTO tipoEscursione;
	private UtenteTO utente;
	private int idMDE;
	private String nome;
	private String data;
	private int numberMin;
	private int numberMax;
	private double costo;
	private String descrizione;
	private Set<OptionalTO> optionals;

	public Escursione() {
	}

	@Override
	public Integer getIdEscursione() {
		return this.idEscursione;
	}

	@Override
	public void setIdEscursione(Integer idEscursione) {
		this.idEscursione = idEscursione;
	}

	@Override
	public StatoEscursioneTO getStatoEscursione() {
		return this.statoEscursione;
	}

	@Override
	public void setStatoEscursione(StatoEscursioneTO statoEscursione) {
		this.statoEscursione = statoEscursione;
	}

	@Override
	public TipoEscursioneTO getTipoEscursione() {
		return this.tipoEscursione;
	}

	@Override
	public void setTipoEscursione(TipoEscursioneTO tipoEscursione) {
		this.tipoEscursione = tipoEscursione;
	}

	@Override
	public UtenteTO getUtente() {
		return this.utente;
	}

	@Override
	public void setUtente(UtenteTO utente) {
		this.utente = utente;
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
	public String getData() {
		return this.data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	
	@Override
	public int getNumberMin() {
		return this.numberMin;
	}

	@Override
	public void setNumberMin(int numberMin) {
		this.numberMin = numberMin;
	}

	@Override
	public int getNumberMax() {
		return this.numberMax;
	}

	@Override
	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String getDescrizione() {
		return this.descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public Set<OptionalTO> getOptionals() {
		return optionals;
	}

	@Override
	public void setOptionals(Set<OptionalTO> optionals) {
		this.optionals = optionals;
	}

	@Override
	public void setIdMde(int id_mde) {
		this.idMDE = id_mde;
	}

	@Override
	public int getIdMde() {
		return this.idMDE;
	}

}
