package outdoorapp.presentation.reqresp;

import outdoorapp.to.OutDoorSports;

/**
 * Classe che gestisce le richieste da mandare ai vari livelli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class Request {

	private OutDoorSports data;
	private String request;
	
	/**
	 * Costruttore di default
	 */
	public Request(){}
	
	/**
	 * Costruttore che inizializza data e request
	 * 
	 * @param data
	 * @param request
	 */
	public Request(OutDoorSports data, String request) {
		this.data = data;
		this.request = request;
	}

	/**
	 * @return i dati da inviare
	 */
	public OutDoorSports getData() {
		return data;
	}

	/**
	 * @return la richiesta da inviare
	 */
	public String getRequest() {
		return request;
	}
	
	/**
	 * Metodo che setta i dati da inviare
	 * 
	 * @param data
	 */
	public void setData(OutDoorSports data) {
		this.data = data;
	}

	/**
	 * Metodo che setta la richiesta da inviare
	 * 
	 * @param request
	 */
	public void setRequest(String request) {
		this.request = request;
	}
}
