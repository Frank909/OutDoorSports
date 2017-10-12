package outdoorapp.utils;

/**
 * Interfaccia che contiene tutte le chiavi-valore per eseguire le azioni
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface KeyMap {
	public static final String MALE ="M";
	public static final String FEMALE = "F";
	public static final String RESP_OK = "OK";
	public static final String RESP_KO = "KO";
	public static final String OUTDOORSPORT_AUTENTICATION = "autenticazione";
	public static final String OUTDOORSPORT_MDS_EXISTS_AT_LEAST_ONE = "verificaManagerDiSistema";
	public static final String OUTDOORSPORT_SAVE_MDS = "nuovoManagerDiSistema";
	public static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
}
