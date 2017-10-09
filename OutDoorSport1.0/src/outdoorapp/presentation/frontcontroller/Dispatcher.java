package outdoorapp.presentation.frontcontroller;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.ViewDashboardManagerDiEscursione;
import outdoorapp.presentation.views.ViewDashboardManagerDiSistema;
import outdoorapp.presentation.views.ViewDashboardPartecipante;
import outdoorapp.to.OutDoorSports;

public class Dispatcher {

	private ViewDashboardPartecipante viewDashboardPartecipante;
	private ViewDashboardManagerDiEscursione ViewDashboardManagerDiEscursione;
	private ViewDashboardManagerDiSistema ViewDashboardManagerDisistema;

	public Dispatcher() {
		viewDashboardPartecipante = new ViewDashboardPartecipante();
		ViewDashboardManagerDiEscursione = new ViewDashboardManagerDiEscursione();
		ViewDashboardManagerDisistema = new ViewDashboardManagerDiSistema();
	}

	public void dispatch(String request) {
		if (request.equalsIgnoreCase("PARTECIPANTE")) {
			viewDashboardPartecipante.show();
		} else if (request.equalsIgnoreCase("MANAGER DI ESCURSIONE")) {
			ViewDashboardManagerDiEscursione.show();
		} else if (request.equalsIgnoreCase("MANAGER DI SISTEMA")){
			ViewDashboardManagerDisistema.show();
		}
	}
}
