package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato del Partecipante. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface PartecipanteTO extends UtenteTO{
	
	/**
	 * Metodo che setta l'id del partecipante
	 * 
	 * @param idPartecipante
	 */
	public void setIdPartecipante(int idPartecipante);
	
	/**
	 * @return l'id del partecipante
	 */
	public int getIdPartecipante();
	
	/**
	 * @return il numero di tessera sanitaria del partecipante
	 */
	public String getTesseraSanitaria();
	
	/**
	 * Metodo che setta il numero di tessera sanitaria del partecipante
	 * 
	 * @param NTesseraSanitaria
	 */
	public void setTesseraSanitaria(String tesseraSanitaria);
	
	/**
	 * @return il certificato src del partecipante
	 */
	public String getCertificatoSrc();
	
	/**
	 * Metodo che setta il certificato src del partecipante
	 * 
	 * @param certificatoSrc
	 */
	public void setCertificatoSrc(String certificatoSrc);
	
	/**
	 * @return la data di rilascio del certificato src del partecipante
	 */
	public String getDataCertificatoSrc();
	
	/**
	 * Metodo che setta la data di rilascio del certificato del partecipante
	 * 
	 * @param string
	 */
	public void setDataCertificatoSrc(String string);
}
