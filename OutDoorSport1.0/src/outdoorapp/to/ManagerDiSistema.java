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
 * Classe che rappresenta il i dati del Manager di Sistema. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ManagerDiSistema extends Utente{

	private static final long serialVersionUID = 3017572647898485317L;
	private int idMds;
	private String telefono;

	public ManagerDiSistema() {
		super.setIdUtente(this.getIdMds());
	}


	/**
	 * @return l'id del manager di sistema
	 */
	public int getIdMds() {
		return this.idMds;
	}

	/**
	 * Metodo che setta il manager di sistema
	 * 
	 * @param idMds
	 */
	public void setIdMds(int idMds) {
		this.idMds = idMds;
	}

	/**
	 * @return il numero di telefono del manager di sistema
	 */
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
