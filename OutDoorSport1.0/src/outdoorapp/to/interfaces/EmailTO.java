package outdoorapp.to.interfaces;

import java.util.List;

/**
 * Interfaccia che rappresenta un oggetto Email. 
 * Sono quindi forniti tutti i metodi per gestire l'invio di una email.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public interface EmailTO extends OutDoorSports{
	
	/**
	 * @return la lista dei destinatari
	 */
	public List<UtenteTO> getListaDestinatari();
	
	/**
	 * Metodo che setta la lista dei destinatari
	 * 
	 * @param listaDestinatari
	 */
	public void setListaDestinatari(List<UtenteTO> listaDestinatari);
	
	/**
	 * @return il corpo del messaggio
	 */
	public String getMessaggio();
	
	/**
	 * Metodo che setta il corpo del messaggio
	 * 
	 * @param messaggio
	 */
	public void setMessaggio(String messaggio);
	
	/**
	 * @return restituisce l'oggetto della mail
	 */
	public String getOggetto();
	
	/**
	 * Metodo che setta l'oggetto della mail
	 * 
	 * @param oggetto
	 */
	public void setOggetto(String oggetto);
}
