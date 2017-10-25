package outdoorapp.presentation.reqresp;

import javafx.scene.layout.Pane;
import outdoorapp.to.interfaces.OutDoorSports;

/**
 * Classe che gestisce le richieste da mandare ai vari livelli
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public class Request{

	private Object data;
	private Pane view;
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
	 * Costruttore che inizializza la view e la request
	 * 
	 * @param data
	 * @param request
	 */
	public Request(Pane view, String request) {
		this.view = view;
		this.request = request;
	}
	
	/**
	 * Costruttore che inizializza data, la view, e request
	 * 
	 * @param data
	 * @param request
	 */
	public Request(Object data, Pane view, String request) {
		this.data = data;
		this.view = view;
		this.request = request;
	}
	
	/**
	 * Costruttore che inizializza data e request
	 * 
	 * @param data
	 * @param request
	 */
	public Request(String request) {
		this.request = request;
	}

	/**
	 * @return i dati da inviare
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Metodo che setta i dati da inviare
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * @return i dati da inviare
	 */
	public Pane getView() {
		return view;
	}
	
	/**
	 * Metodo che setta i dati da inviare
	 * 
	 * @param data
	 */
	public void setData(Pane view) {
		this.view = view;
	}
	
	/**
	 * Override del metodo toString() della classe Object che restituisce la richiesta effettiva
	 */
	@Override
	public String toString() {
		return this.request;
	}
}
