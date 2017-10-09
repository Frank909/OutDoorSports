package outdoorapp.presentation.reqresp;

import outdoorapp.to.OutDoorSports;

/**
 * Classe 
 * 
 * @author andreese
 *
 */

public class Request {

	private OutDoorSports data;
	private String request;
	
	public Request(){}
	
	public Request(OutDoorSports data, String request) {
		this.data = data;
		this.request = request;
	}

	public OutDoorSports getData() {
		return data;
	}

	public String getRequest() {
		return request;
	}
	
	public void setData(OutDoorSports data) {
		this.data = data;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
