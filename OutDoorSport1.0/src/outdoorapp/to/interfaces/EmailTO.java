package outdoorapp.to.interfaces;

import java.util.List;

public interface EmailTO extends OutDoorSports{
	public List<UtenteTO> getListaDestinatari();
	public void setListaDestinatari(List<UtenteTO> listaDestinatari);
	public String getMessaggio();
	public void setMessaggio(String messaggio);
	public String getOggetto();
	public void setOggetto(String oggetto);
}
