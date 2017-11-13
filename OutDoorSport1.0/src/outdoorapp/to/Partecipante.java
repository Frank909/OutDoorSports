package outdoorapp.to;

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
