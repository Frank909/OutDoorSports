package outdoorapp.services;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

public interface Service {
	Response sendRequest(Request request, ServiceType serviceType);
}
