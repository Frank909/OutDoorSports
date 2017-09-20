package java.to;
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

@Entity
@Table(name = "tbl_mde", catalog = "outdoorsports")
public class ManagerDiEscursione extends Utente{

	private static final long serialVersionUID = -4507785182150099517L;
	private int idMde;
	private Utente tblUtente;
	private double stipendio;

	public ManagerDiEscursione() {
	}

	public ManagerDiEscursione(Utente tblUtente, double stipendio) {
		this.tblUtente = tblUtente;
		this.stipendio = stipendio;
	}

	/**
	 * @return l'id del manager di escursione
	 */ 
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "tblUtente"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id_mde", unique = true, nullable = false)
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
	 * @return altre informazioni del manager di escursione dalla classe Utente
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Utente getUtente() {
		return this.tblUtente;
	}

	/**
	 * Metodo che setta le altre informazioni del manager di escursione
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente tblUtente) {
		this.tblUtente = tblUtente;
	}

	/**
	 * @return lo stipendio del manager di escursione
	 */
	@Column(name = "stipendio", nullable = false, precision = 22, scale = 0)
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
