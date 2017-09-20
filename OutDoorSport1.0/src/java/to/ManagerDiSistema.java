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
 * Classe che rappresenta il i dati del Manager di Sistema. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_mds", catalog = "outdoorsports")
public class ManagerDiSistema extends Utente{

	private static final long serialVersionUID = 3017572647898485317L;
	private int idMds;
	private Utente tblUtente;
	private String telefono;

	public ManagerDiSistema() {
	}

	public ManagerDiSistema(Utente tblUtente, String telefono) {
		this.tblUtente = tblUtente;
		this.telefono = telefono;
	}

	/**
	 * @return l'id del manager di sistema
	 */
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "tblUtente"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id_mds", unique = true, nullable = false)
	public int getIdManagerDiSistema() {
		return this.idMds;
	}

	/**
	 * Metodo che setta il manager di sistema
	 * 
	 * @param idMds
	 */
	public void setIdManagerDiSistema(int idMds) {
		this.idMds = idMds;
	}

	/**
	 * @return altre informazioni del manager di sistema dalla classe Utente
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Utente getUtente() {
		return this.tblUtente;
	}

	/**
	 * Metodo che setta le altre informazioni del manager di sistema
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente tblUtente) {
		this.tblUtente = tblUtente;
	}

	/**
	 * @return il numero di telefono del manager di sistema
	 */
	@Column(name = "telefono", nullable = false, length = 45)
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * Metodo che setta il numero di telefono del manager di sistema
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
