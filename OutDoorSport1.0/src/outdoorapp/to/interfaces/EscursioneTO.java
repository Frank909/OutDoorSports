package outdoorapp.to.interfaces;

import java.util.Date;
import java.util.Set;

public interface EscursioneTO extends OutDoorSports{
	public void setIdEscursione(Integer idEscursione);
	public Integer getIdEscursione();
	public StatoEscursioneTO getStatoEscursione();
	public void setStatoEscursione(StatoEscursioneTO statoEscursione);
	public TipoEscursioneTO getTipoEscursione();
	public void setTipoEscursione(TipoEscursioneTO tipoEscursione);
	public UtenteTO getUtente();
	public void setUtente(UtenteTO utente);
	public String getNome();
	public void setNome(String nome);
	public Date getData();
	public void setData(Date data);
	public int getNumberMin();
	public void setNumberMin(int numberMin);
	public int getNumberMax();
	public void setNumberMax(int numberMax);
	public double getCosto();
	public void setCosto(double costo);
	public String getDescrizione();
	public void setDescrizione(String descrizione);
	public Set<OptionalTO> getOptionals();
	public void setOptionals(Set<OptionalTO> optionals);
}
