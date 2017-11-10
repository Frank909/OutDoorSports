package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
