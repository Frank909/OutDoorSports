package outdoorapp.to;

/**
 * Classe che implementa un oggetto Email. 
 * Sono quindi forniti tutti i metodi per gestire l'invio di una email.
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

	/**
	 * @return la lista dei destinatari
	 */
	public List<UtenteTO> getListaDestinatari() {
		return listaDestinatari;
	}

	/**
	 * Metodo che setta la lista dei destinatari
	 * 
	 * @param listaDestinatari
	 */
	public void setListaDestinatari(List<UtenteTO> listaDestinatari) {
		this.listaDestinatari = listaDestinatari;
	}

	/**
	 * @return il corpo del messaggio
	 */
	public String getMessaggio() {
		return messaggio;
	}

	/**
	 * Metodo che setta il corpo del messaggio
	 * 
	 * @param messaggio
	 */
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	/**
	 * @return restituisce l'oggetto della mail
	 */
	public String getOggetto() {
		return oggetto;
	}

	/**
	 * Metodo che setta l'oggetto della mail
	 * 
	 * @param oggetto
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

}
