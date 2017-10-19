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

/**
 * Metodo che rappresenta il i dati del Manager di Escursione. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ManagerDiEscursione extends Utente{

	private static final long serialVersionUID = -4507785182150099517L;
	private int idMde;
	private double stipendio;

	public ManagerDiEscursione() {
		super.setIdUtente(this.getIdManagerDiEscursione());
	}

	/**
	 * @return l'id del manager di escursione
	 */ 
	public int getIdManagerDiEscursione() {
		return this.idMde;
	}

	/**
	 * Metodo che setta l'id del manager di escursione
	 * 
	 * @param idMde
	 */
	public void setIdManagerDiEscursione(int idMde) {
		this.idMde = idMde;
	}

	/**
	 * @return lo stipendio del manager di escursione
	 */
	public double getStipendio() {
		return this.stipendio;
	}

	/**
	 * Metodo che setta lo stipendio del manager di escursione
	 * 
	 * @param stipendio
	 */
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}

}
