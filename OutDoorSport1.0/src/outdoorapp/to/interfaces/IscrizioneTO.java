package outdoorapp.to.interfaces;

import java.util.Set;

public interface IscrizioneTO extends OutDoorSports{
	public void setIdIscrizione(Integer idIscrizione);
	public Integer getIdIscrizione();
	public EscursioneTO getEscursione();
	public void setEscursione(EscursioneTO escursione);
	public StatoIscrizioneTO getStatoIscrizione();
	public void setStatoIscrizione(StatoIscrizioneTO statoIscrizione);
	public UtenteTO getUtente();
	public void setUtente(UtenteTO utente);
	public String getData();
	public void setData(String data);
	public Set<OptionalTO> getOptionals();
	public void setOptionals(Set<OptionalTO> optionals);
}
