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

public class Email{

	private List<Utente> listaDestinatari;
	private String messaggio;
	private String oggetto;
	
	/**
	 * Costruttore della classe Email che inizializza la lista dei destinatari
	 */
	public Email() {
		this.listaDestinatari = new ArrayList<Utente>();
	}

	/**
	 * @return la lista dei destinatari
	 */
	public List<Utente> getListaDestinatari() {
		return listaDestinatari;
	}

	/**
	 * Metodo che setta la lista dei destinatari
	 * 
	 * @param listaDestinatari
	 */
	public void setListaDestinatari(List<Utente> listaDestinatari) {
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
