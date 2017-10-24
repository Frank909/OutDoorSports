package outdoorapp.to;

/**
 * Classe che implementa un oggetto Email. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

import java.util.ArrayList;
import java.util.List;

import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.OutDoorSports;
import outdoorapp.to.interfaces.UtenteTO;

class Email implements EmailTO{

	private static final long serialVersionUID = 1L;
	private List<UtenteTO> listaDestinatari;
	private String messaggio;
	private String oggetto;
	
	/**
	 * Costruttore della classe Email che inizializza la lista dei destinatari
	 */
	Email() {
		this.listaDestinatari = new ArrayList<UtenteTO>();
	}

	@Override
	public List<UtenteTO> getListaDestinatari() {
		return listaDestinatari;
	}

	@Override
	public void setListaDestinatari(List<UtenteTO> listaDestinatari) {
		this.listaDestinatari = listaDestinatari;
	}

	@Override
	public String getMessaggio() {
		return messaggio;
	}

	@Override
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	@Override
	public String getOggetto() {
		return oggetto;
	}

	@Override
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

}
