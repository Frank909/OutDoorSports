package outdoorapp.services;

/**
 * Classe che consente di creare il servizio richiesto e usufruirne.
 * Rappresenta il pattern del Service Locator
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ServiceLocator{

	/**
	 * Cache dei servizi
	 */
	private static Cache cache;

	static{
		cache = new Cache();		
	}

	/** 
	 * @param serviceType: Tipo di servizio richiesto.
	 * @return restituisce il servizio richiesto in base al tipo dello stesso dato in ingresso.
	 * @throws Exception: Lancia l'eccezione nel caso non esista il servizio richiesto.
	 */
	public static Service getService(ServiceType serviceType){
		Service service = cache.getService(serviceType);

		if(service != null)
			return service;

		InitialContext context = new InitialContext();
		Service service1 = null;
		
		try {
			service1 = (Service)context.lookup(serviceType);
			cache.addService(service1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service1;
	}
}
