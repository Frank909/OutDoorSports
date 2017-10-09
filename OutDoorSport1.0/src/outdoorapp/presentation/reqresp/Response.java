package outdoorapp.presentation.reqresp;

import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Utente;

public class Response {

	private OutDoorSports data;
	private String response;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	public Response(OutDoorSports data, String response) {
		this.data = data;
		this.response = response;
	}

	public OutDoorSports getData() {
		return data;
	}

	public void setData(OutDoorSports data) {
		this.data = data;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
