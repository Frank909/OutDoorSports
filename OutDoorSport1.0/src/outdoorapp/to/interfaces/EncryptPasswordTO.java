package outdoorapp.to.interfaces;

/**
 * Interfaccia che rappresenta un oggetto EncryptPassword. 
 * Sono quindi forniti tutti i metodi per gestire la criptazione di una password.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 */

public interface EncryptPasswordTO extends OutDoorSports{
	
	/**
	 * Metodo che esegue la criptazione della password
	 * 
	 * @param password da criptare
	 * @return la password criptata
	 */
	public String encryptPassword(String password);
	
	/**
	 * @param inputPassword: password da controllare
	 * @param encryptedPassword: password criptata
	 * @return vero se le password criptate coincidono, falso altrimenti
	 */
	public boolean checkPassword(String inputPassword, String encryptedPassword);
}
