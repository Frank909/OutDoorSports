package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.SessionUtil;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.OutDoorSports;
import outdoorapp.to.Utente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * classe astratta rappresenta una generalizzazione del pattern DAO per 
 * tutti i DAO presenti all'interno del package. In particolare rappresenta
 * i nomi dei metodi del CRUD di una tabella del database, che sono generici per tutti.
 * Inoltre sono stati inseriti i nomi dei metodi per generalizzare le esecuzioni di 
 * una query con o senza parametri.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 * @param <T>
 */
public abstract class GenericDAO<T extends OutDoorSports>{
	
	private OutDoorSports currentClass;
	
	/**
	 * @return la sessione creata nella classe SessionUtil
	 * @throws DatabaseException 
	 * @throws HibernateException 
	 */
	private Session getSession(){
		return SessionUtil.getSessionFactory().openSession();
	}

	/**
	 * Metodo che aggiorna la classe corrente
	 * 
	 * @param currentClass
	 */
	protected void setCurrentClass( final OutDoorSports currentClass){
		this.currentClass = currentClass;
	}

	/**
	 * Inserisce l'entità di tipo T nel database
	 * 
	 * @param entity
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	public T create(final T entity) throws DatabaseException{
		Transaction transaction = null;
		Session session = null;

		try {
			session = this.getSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (Exception e){
			if (transaction != null){
				transaction.rollback();
			}
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return entity;
	}
	
	/**
	 * Restituisce una specifica entità di tipo T presente nel database
	 * in base al suo id
	 * 
	 * @param id
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	@SuppressWarnings("unchecked")
	public T findOne(final int id) throws DatabaseException{
		T entity = null;
		Transaction transaction = null;
		Session session = null;

		try{
			session = this.getSession();
			transaction = session.beginTransaction();

			entity = (T)session.get(this.currentClass.getClass().getName(), id);

			transaction.commit();
		} catch (HibernateException e){
			transaction.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return entity; 
	}
	
	/**
	 * Restituisce la lista di tutte le entità di tipo T presenti in una
	 * tabella del database.
	 * 
	 * @return lista di entità di tipo T
	 * @throws DatabaseException
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws DatabaseException{
		List<T> result = null;
		Transaction transaction = null;
		Session session = null;

		try{
			session = this.getSession();
			transaction = session.beginTransaction();

			result = session.createQuery("FROM "+this.currentClass.getClass().getName()).list();

			transaction.commit();
		}catch (HibernateException e){
			transaction.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}
		return result; 
	}
	
	/**
	 * Aggiorna una specifica entità già presente nel database
	 * 
	 * @param entity
	 * @return l'entità di tipo T
	 * @throws DatabaseException
	 */
	public T update(final T entity) throws DatabaseException{
		Transaction transaction = null;
		Session session = null;

		try {
			session = this.getSession();
			transaction = session.beginTransaction();

			session.update(entity);

			transaction.commit();
		} catch (HibernateException e){
			if (transaction != null){
				transaction.rollback();
			}
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return entity;
	}
	
	/**
	 * Elimina una entità già presente nel database
	 * 
	 * @param entity
	 * @throws DatabaseException 
	 */
	public void delete(final T entity) throws DatabaseException{
		Transaction transaction = null;
		Session session = null;

		try {
			session = this.getSession();
			transaction = session.beginTransaction();

			session.delete(entity);

			transaction.commit();
		} catch (HibernateException e){
			if (transaction != null){
				transaction.rollback();
			}
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}
	}
	
	/**
	 * Esegue la query parametrica 
	 * 
	 * @param queryName
	 * @param parameters
	 * @return Una lista di entità risultato della query.
	 * @throws DatabaseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	<P> List<T> executeParamQuery(String queryName, List<P> parameters) throws DatabaseException{
		Transaction transaction = null;
		List<T> response = null;
		Session session = null;

		try {
			session = this.getSession();
			transaction = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			//Query query = session.createQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, param);
				index++;
			}

			response = query.list();

			transaction.commit();
		} catch (Exception e){
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return response;
	}
	
	/**
	 * Esegue la query
	 * 
	 * @param queryName
	 * @return una lista di entità di tipo T
	 * @throws DatabaseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	List<T> executeQuery(String queryName) throws DatabaseException{
		Transaction transaction = null;
		List<T> response = null;
		Session session = null;

		try {
			session = this.getSession();
			transaction = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			response = query.list();

			transaction.commit();
		} catch (Exception e){
			if (transaction != null){
				transaction.rollback();
			}
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return response;
	}
}
