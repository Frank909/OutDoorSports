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

import outdoorapp.to.interfaces.PartecipanteTO;

/**
 * Classe che rappresenta il i dati del Partecipante. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Partecipante extends Utente implements PartecipanteTO{

	private static final long serialVersionUID = -1029684532506699639L;
	private int idPartecipante;
	private String tesseraSanitaria;
	private String certificatoSrc;
	private String dataCertificatoSrc;

	Partecipante() {
		super.setIdUtente(this.getIdPartecipante());
	}

	/**
	 * @return l'id del partecipante
	 */
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
	 * @return il numero di tessera sanitaria del partecipante
	 */
	public String getTesseraSanitaria() {
		return this.tesseraSanitaria;
	}

	/**
	 * Metodo che setta il numero di tessera sanitaria del partecipante
	 * 
	 * @param NTesseraSanitaria
	 */
	public void setTesseraSanitaria(String tesseraSanitaria) {
		this.tesseraSanitaria = tesseraSanitaria;
	}

	/**
	 * @return il certificato src del partecipante
	 */
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
	public String getDataCertificatoSrc() {
		return this.dataCertificatoSrc;
	}

	/**
	 * Metodo che setta la data di rilascio del certificato del partecipante
	 * 
	 * @param string
	 */
	public void setDataCertificatoSrc(String string) {
		this.dataCertificatoSrc = string;
	}
}
