package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe che prepara la configurazione e la sessione per interagire
 * con il database
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class SessionUtil{
	
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Metodo che prepara la session factory
	 * 
	 * @return la session Factory
	 * @throws DatabaseException
	 */
	public static SessionFactory buildSessionFactory(){
		SessionFactory res = null;

		if(sessionFactory == null){
			Configuration configuration = new Configuration();
			configuration.configure("/resources/hibernate.cfg.xml");
			try{
				sessionFactory = configuration.buildSessionFactory();
				res = sessionFactory;
			}catch(Exception e){
				System.out.println(e.getMessage());
				try {
					throw new DatabaseException(e.getMessage());
				} catch (DatabaseException e1) {
					e1.getMessage();
				}
			}
		} else {
			res = sessionFactory;
		}

		return res;
	}
	
	/**
	 * Metodo che restituisce la session factory
	 * @return sessionFactory: Sessione restituita
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;		
	}
}
