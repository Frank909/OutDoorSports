package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Classe che rappresenta il i dati del Partecipante. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

@Entity
@Table(name = "tbl_partecipante", catalog = "outdoorsports")
public class Partecipante extends Utente{


	private static final long serialVersionUID = -1029684532506699639L;
	private int idPartecipante;
	private Utente tblUtente;
	private String NTesseraSanitaria;
	private String certificatoSrc;
	private Date dataCertificatoSrc;

	public Partecipante() {
	}

	public Partecipante(Utente tblUtente, String NTesseraSanitaria, String certificatoSrc,
			Date dataCertificatoSrc) {
		this.tblUtente = tblUtente;
		this.NTesseraSanitaria = NTesseraSanitaria;
		this.certificatoSrc = certificatoSrc;
		this.dataCertificatoSrc = dataCertificatoSrc;
	}

	/**
	 * @return l'id del partecipante
	 */
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "tblUtente"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id_partecipante", unique = true, nullable = false)
	public int getIdPartecipante() {
		return this.idPartecipante;
	}

	/**
	 * Metodo che setta l'id del partecipante
	 * 
	 * @param idPartecipante
	 */
	public void setIdPartecipante(int idPartecipante) {
		this.idPartecipante = idPartecipante;
	}

	/**
	 * @return l'utente collegato al partecipante
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Utente getUtente() {
		return this.tblUtente;
	}

	/**
	 * Metodo che setta l'utente collegato al partecipante
	 * 
	 * @param tblUtente
	 */
	public void setUtente(Utente tblUtente) {
		this.tblUtente = tblUtente;
	}

	/**
	 * @return il numero di tessera sanitaria del partecipante
	 */
	@Column(name = "n_tessera_sanitaria", nullable = false, length = 20)
	public String getNTesseraSanitaria() {
		return this.NTesseraSanitaria;
	}

	/**
	 * Metodo che setta il numero di tessera sanitaria del partecipante
	 * 
	 * @param NTesseraSanitaria
	 */
	public void setNTesseraSanitaria(String NTesseraSanitaria) {
		this.NTesseraSanitaria = NTesseraSanitaria;
	}

	/**
	 * @return il certificato src del partecipante
	 */
	@Column(name = "certificato_src", nullable = false, length = 200)
	public String getCertificatoSrc() {
		return this.certificatoSrc;
	}

	/**
	 * Metodo che setta il certificato src del partecipante
	 * 
	 * @param certificatoSrc
	 */
	public void setCertificatoSrc(String certificatoSrc) {
		this.certificatoSrc = certificatoSrc;
	}

	/**
	 * @return la data di rilascio del certificato src del partecipante
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_certificato_src", nullable = false, length = 10)
	public Date getDataCertificatoSrc() {
		return this.dataCertificatoSrc;
	}

	/**
	 * Metodo che setta la data di rilascio del certificato del partecipante
	 * 
	 * @param dataCertificatoSrc
	 */
	public void setDataCertificatoSrc(Date dataCertificatoSrc) {
		this.dataCertificatoSrc = dataCertificatoSrc;
	}

}
