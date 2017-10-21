package outdoorapp.presentation.reqresp;

import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Utente;

/**
 * Classe che gestisce le risposte da ricevere dai vari livelli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class Response {

	private OutDoorSports data;
	private String response;
	private String view;
	
	/**
	 * Costruttore di default
	 */
	public Response() {	}

	/**
	 * @return la view da visualizzare
	 */
	public String getView() {
		return view;
	}

	/**
	 * Metodo che setta la view da visualizzare
	 * 
	 * @param view
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return i dati da ricevere
	 */
	public OutDoorSports getData() {
		return data;
	}

	/**
	 * Metodo che setta i dati da ricevere
	 * 
	 * @param data
	 */
	public void setData(OutDoorSports data) {
		this.data = data;
	}
	
	/**
	 * Metodo che setta la risposta
	 * 
	 * @param data
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	/**
	 * Override toString() di Object che restituisce la risposta effettiva.
	 */
	@Override
	public String toString() {
		return this.response;
	}
}
