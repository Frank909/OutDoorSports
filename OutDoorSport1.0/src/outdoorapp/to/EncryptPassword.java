package outdoorapp.to;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import outdoorapp.to.interfaces.EncryptPasswordTO;
/**
 * Utilizza la libreria Jasypt per effettuare il criptaggio delle password
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class EncryptPassword implements EncryptPasswordTO{

	private static final long serialVersionUID = 1L;
	private ConfigurablePasswordEncryptor passwordEncryptor;
	
	/**
	 * Il costruttore setta l'algoritmo
	 * SHA-256 per il criptaggio
	 */
	EncryptPassword(){
		this.passwordEncryptor = new ConfigurablePasswordEncryptor();
		this.passwordEncryptor.setAlgorithm("SHA-256");
		this.passwordEncryptor.setPlainDigest(true);
	}
	
	@Override
	public String encryptPassword(String password){
		return passwordEncryptor.encryptPassword(password);
	}
	
	@Override
	public boolean checkPassword(String inputPassword, String encryptedPassword){
		return passwordEncryptor.checkPassword(inputPassword, encryptedPassword);
	}
}
