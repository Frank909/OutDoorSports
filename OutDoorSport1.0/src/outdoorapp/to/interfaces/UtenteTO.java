package outdoorapp.to.interfaces;

public interface UtenteTO extends OutDoorSports{
	public void setIdUtente(Integer idUtente);
	public Integer getIdUtente();
	public void setRuolo(RuoliTO ruolo);
	public RuoliTO getRuolo();
	public StatoUtenteTO getStatoUtente();
	public void setStatoUtente(StatoUtenteTO tblStatoUtente);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getNome();
	public void setNome(String nome);
	public String getCognome();
	public void setCognome(String cognome);
	public String getEmail();
	public void setEmail(String email);
	public String getCodiceFiscale();
	public void setCodiceFiscale(String codiceFiscale);
	public String getDataNascita();
	public void setDataNascita(String localDate);
	public String getSesso();
	public void setSesso(String sesso);
	public String getIndirizzo();
	public void setIndirizzo(String indirizzo);
	public String getCitta();
	public void setCitta(String citta);
}
