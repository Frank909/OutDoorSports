package outdoorapp.presentation.reqresp;

import javafx.scene.layout.Pane;
import outdoorapp.to.OutDoorSports;

public class RequestView {

	private Pane data;
	private String request;
	
	/**
	 * Costruttore di default
	 */
	public RequestView(){}
	
	/**
	 * Costruttore che inizializza data e request
	 * 
	 * @param data
	 * @param request
	 */
	public RequestView(Pane data, String request) {
		this.data = data;
		this.request = request;
	}

	/**
	 * @return i dati da inviare
	 */
	public Pane getData() {
		return data;
	}
	
	/**
	 * Metodo che setta i dati da inviare
	 * 
	 * @param data
	 */
	public void setData(Pane data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return this.request;
	}

}
