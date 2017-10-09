package outdoorapp.business.applicationservice;

/**
 * Classe utilizzata dal Business Delegate per identificare il giusto Application Service 
 * in base alla richiesta inviata
 * 
 * @author andreese
 *
 */

public class ApplicationService {

	/**
	 * Costante per recuperare il percorso del package
	 */
	private final static String PACKAGE_PATH = ApplicationService.class.getPackage().getName();
	
	/**
	 * Costante per recuperare il nome della classe
	 */
	private final static String APPLICATION_SERVICE_PREFIX = ApplicationService.class.getSimpleName();

	/**
	 * Metodo che restituisce il percorso completo dell'application service a cui 
	 * dovrà fare riferimento il Business Delegate. 
	 * 
	 * @param suffisso per l'application service
	 * @return il nome completo di package dell'application service
	 */
	public static String getApplicationService(String suffix){
		return PACKAGE_PATH + "." + APPLICATION_SERVICE_PREFIX + suffix;
	}
}
