package outdoorapp.to.interfaces;

public interface PartecipanteTO extends UtenteTO{
	public void setIdPartecipante(int idPartecipante);
	public int getIdPartecipante();
	public String getTesseraSanitaria();
	public void setTesseraSanitaria(String tesseraSanitaria);
	public String getCertificatoSrc();
	public void setCertificatoSrc(String certificatoSrc);
	public String getDataCertificatoSrc();
	public void setDataCertificatoSrc(String string);
}
