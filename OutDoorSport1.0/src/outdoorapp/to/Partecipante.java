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
 * Classe che implementa lo stato del Partecipante.
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

	@Override
	public int getIdPartecipante() {
		return this.idPartecipante;
	}

	@Override
	public void setIdPartecipante(int idPartecipante) {
		this.idPartecipante = idPartecipante;
	}

	@Override
	public String getTesseraSanitaria() {
		return this.tesseraSanitaria;
	}

	@Override
	public void setTesseraSanitaria(String tesseraSanitaria) {
		this.tesseraSanitaria = tesseraSanitaria;
	}

	@Override
	public String getCertificatoSrc() {
		return this.certificatoSrc;
	}

	@Override
	public void setCertificatoSrc(String certificatoSrc) {
		this.certificatoSrc = certificatoSrc;
	}

	@Override
	public String getDataCertificatoSrc() {
		return this.dataCertificatoSrc;
	}
	
	@Override
	public void setDataCertificatoSrc(String string) {
		this.dataCertificatoSrc = string;
	}
}
