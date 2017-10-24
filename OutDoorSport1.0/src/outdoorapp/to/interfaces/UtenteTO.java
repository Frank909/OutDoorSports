package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura e la scrittura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface UtenteTO extends OutDoorSports{
	
	/**
	 * Metodo che setta l'id dell'utente
	 * 
	 * @param idUtente
	 */
	public void setIdUtente(Integer idUtente);
	
	/**
	 * @return l'id dell'utente
	 */
	public Integer getIdUtente();
	
	/**
	 * Metodo che setta il ruolo relativo a un determinato utente
	 * 
	 * @param tblRuoli
	 */
	public void setRuolo(RuoliTO ruolo);
	
	/**
	 * @return il ruolo relativo a un determinato utente
	 */
	public RuoliTO getRuolo();
	
	/**
	 * @return lo stato utente relativo a un determinato utente
	 */
	public StatoUtenteTO getStatoUtente();
	
	/**
	 * Metodo che setta lo stato utente relativo a un determinato utente
	 * 
	 * @param tblStatoUtente
	 */
	public void setStatoUtente(StatoUtenteTO tblStatoUtente);
	
	/**
	 * @return l'username dell'utente
	 */
	public String getUsername();
	
	/**
	 * Metodo che setta l'username dell'utente
	 * 
	 * @param username
	 */
	public void setUsername(String username);
	
	/**
	 * @return la password dell'utente
	 */
	public String getPassword();
	
	/**
	 * Metodo che setta la password dell'utente
	 * 
	 * @param password
	 */
	public void setPassword(String password);
	
	/**
	 * @return il nome dell'utente
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dell'utente
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
	
	/**
	 * @return il cognome dell'utente
	 */
	public String getCognome();
	
	/**
	 * Metodo che setta il cognome dell'utente
	 * 
	 * @param cognome
	 */
	public void setCognome(String cognome);
	
	/**
	 * @return l'email dell'utente
	 */
	public String getEmail();
	
	/**
	 * Metodo che setta l'email dell'utente
	 * 
	 * @param email
	 */
	public void setEmail(String email);
	
	/**
	 * @return il codice fiscale dell'utente
	 */ 
	public String getCodiceFiscale();
	
	/**
	 * Metodo che setta il codice fiscale dell'utente
	 * 
	 * @param codiceFiscale
	 */
	public void setCodiceFiscale(String codiceFiscale);
	
	/**
	 * @return la data di nascia dell'utente
	 */
	public String getDataNascita();
	
	/**
	 * Metodo che setta la data di nascita dell'utente
	 * 
	 * @param localDate
	 */
	public void setDataNascita(String localDate);
	
	/**
	 * @return il sesso dell'utente
	 */
	public String getSesso();
	
	/**
	 * Metodo che setta il sesso dell'utente
	 * 
	 * @param sesso
	 */
	public void setSesso(String sesso);
	
	/**
	 * @return l'indirizzo dell'utente
	 */
	public String getIndirizzo();
	
	/**
	 * Metodo che setta l'indirizzo dell'utente
	 * 
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo);
	

	/**
	 * @return la città dell'utente
	 */
	public String getCitta();
	
	/**
	 * Metodo che setta la città dell'utente
	 * 
	 * @param citta
	 */
	public void setCitta(String citta);
}
