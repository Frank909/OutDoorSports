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

import outdoorapp.to.interfaces.ManagerDiSistemaTO;

/**
 * Classe che implementa il i dati del Manager di Sistema.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class ManagerDiSistema extends Utente implements ManagerDiSistemaTO{

	private static final long serialVersionUID = 3017572647898485317L;
	private int idMds;
	private String telefono;

	ManagerDiSistema() {
		super.setIdUtente(this.getIdMds());
	}

	@Override
	public int getIdMds() {
		return this.idMds;
	}

	@Override
	public void setIdMds(int idMds) {
		this.idMds = idMds;
	}

	@Override
	public String getTelefono() {
		return this.telefono;
	}

	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
