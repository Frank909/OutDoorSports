package outdoorapp.utils;

/**
 * Interfaccia che contiene tutte le chiavi-valore per eseguire le azioni
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public interface Actions {
	public static final String MALE ="M";
	public static final String FEMALE = "F";
	public static final String RESP_OK = "OK";
	public static final String RESP_KO = "KO";
	public static final String OUTDOORSPORT_AUTENTICATION = "autenticazione";
	public static final String OUTDOORSPORT_MDS_EXISTS_AT_LEAST_ONE = "verificaManagerDiSistema";
	public static final String OUTDOORSPORT_SAVE_MDS = "nuovoManagerDiSistema";
	public static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public static final String OUTDOORSPORT_EXECUTE_LOGIN = "eseguiLogin";
	public static final String OUTDOORSPORT_REQUEST_NEW_PASSWORD = "richiediNuovaPassword";
	public static final String OUTDOORSPORT_SAVE_SRC_CERTIFICATE = "caricaCertificatoSRC";
	public static final String OUTDOORSPORT_SAVE_PARTECIPANTE = "nuovoPartecipante";
	public static final String OUTDOORSPORT_SAVE_MDE = "nuovoManagerDiEscursione";
	public static final String OUTDOORSPORT_MODIFY_MDE = "modificaManagerDiEscursione";
	public static final String OUTDOORSPORT_GET_ALL_MDE = "getAllManagerDiEscursione";
	public static final String ROOT_CERTIFICATE = "certificatiSRC";
}
