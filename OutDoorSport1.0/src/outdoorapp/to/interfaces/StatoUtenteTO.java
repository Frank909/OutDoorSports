package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta lo stato dell'Utente. Sono fornite tutte le 
 * dichiarazioni dei metodi per la lettura dei dati
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface StatoUtenteTO extends OutDoorSports {
	
	/**
	 * Metodo che setta l'id dello stato utente
	 * 
	 * @param idStatoUtente
	 */
	public void setIdStatoUtente(Integer idStatoUtente);
	
	/**
	 * @return l'id dello stato utente
	 */
	public Integer getIdStatoUtente();
	
	/**
	 * @return il nome dello stato utente
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome dello stato utente
	 * 
	 * @param nome
	 */
	public void setNome(String nome);
}
