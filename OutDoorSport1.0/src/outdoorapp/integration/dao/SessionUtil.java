package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Classe che prepara la configurazione e la sessione per interagire
 * con il database
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class SessionUtil {
	
	private static SessionFactory sessionFactory;

	/**
	 * Metodo che prepara la session factory
	 * 
	 * @return la session Factory
	 * @throws DatabaseException
	 */
	public static SessionFactory buildSessionFactory() throws DatabaseException{
		SessionFactory res = null;

		if(sessionFactory == null){
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			try{
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				res = sessionFactory;
			}catch(Exception e){
				throw new DatabaseException(e.getMessage());
			}
		} else {
			res = sessionFactory;
		}

		return res;
	}
	
	/**
	 * @return la sessionFactory
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;		
	}
}
