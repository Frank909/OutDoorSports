package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.util.Set;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * Classe che implementa lo stato dell'Iscrizione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Iscrizione implements IscrizioneTO{

	private Integer idIscrizione;
	private EscursioneTO escursione;
	private StatoIscrizioneTO statoIscrizione;
	private UtenteTO utente;
	private String data;
	private Set<OptionalTO> optionals;

	public Iscrizione() {
	}
	
	@Override
	public Integer getIdIscrizione() {
		return this.idIscrizione;
	}

	@Override
	public void setIdIscrizione(Integer idIscrizione) {
		this.idIscrizione = idIscrizione;
	}

	@Override
	public EscursioneTO getEscursione() {
		return this.escursione;
	}

	@Override
	public void setEscursione(EscursioneTO escursione) {
		this.escursione = escursione;
	}

	@Override
	public StatoIscrizioneTO getStatoIscrizione() {
		return this.statoIscrizione;
	}

	@Override
	public void setStatoIscrizione(StatoIscrizioneTO statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
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
	public String getData() {
		return this.data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public Set<OptionalTO> getOptionals() {
		return optionals;
	}

	@Override
	public void setOptionals(Set<OptionalTO> optionals) {
		this.optionals = optionals;
	}
}
