package outdoorapp.to;

import outdoorapp.to.interfaces.ManagerDiEscursioneTO;

/**
 * Classe che implementa il i dati del Manager di Escursione.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ManagerDiEscursione extends Utente implements ManagerDiEscursioneTO{

	private static final long serialVersionUID = -4507785182150099517L;
	private double stipendio;

	@Override
	public double getStipendio() {
		return this.stipendio;
	}

	@Override
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
}
