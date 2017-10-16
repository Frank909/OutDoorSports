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
	private String request;
	private String response;
	private String view;
	
	/**
	 * Costruttore di default
	 */
	public Response() {	}
	
	/**
	 * Costruttore che inizializza data e response
	 * 
	 * @param data
	 * @param response
	 */
	public Response(OutDoorSports data, String response, String view) {
		this.data = data;
		this.response = response;
		this.view = view;
	}

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
	 * @return la risposta da ricevere
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Metodo che setta la risposta da ricevere
	 * 
	 * @param response
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	/**
	 * @return la richiesta inviata inizialmente
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * Metodo che setta la richiesta inviata inizialmente
	 * 
	 * @param request
	 */
	public void setRequest(String request) {
		this.request = request;
	}
}
