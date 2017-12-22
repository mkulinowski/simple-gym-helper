package dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.Schedule;
import util.HibernateUtil;

public class GenericDAOImpl<T> implements IGenericDAO<T>{
	
	private Class<T> clazz;
	
	public GenericDAOImpl(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public void add(T t){
		Transaction transaction = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			transaction = session.beginTransaction();
			session.save(t);
			session.flush();
			session.getTransaction().commit();
			
		}catch (RuntimeException e){
			  if (transaction != null) {
	                transaction.rollback();
	            }
			  e.printStackTrace();
		}finally {
			
			session.close();
		}
	};
	
	public void edit(T t){
		Transaction transaction = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			transaction = session.beginTransaction();
			session.update(t);
			session.flush();
			session.getTransaction().commit();
			
		}catch (RuntimeException e){
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}finally{
			
			session.close();
		}
	}

	@Override
	public void delete(T t) {
		Transaction transaction = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		try{
			transaction = session.beginTransaction();
			session.delete(t);
			session.flush();
			session.getTransaction().commit();
			
		}catch(RuntimeException e){
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}finally{
			
			session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		List<T> t = new ArrayList<T>();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
        	
            transaction = session.beginTransaction();
            t = session.createQuery("from "+clazz.getName()).list();
        } catch (RuntimeException e) {
        	transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return t;
	}

	@Override
	public T findByid(Integer id) {
		      T t = null;
	        Transaction transaction = null;
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
	        try {
	            transaction = session.beginTransaction();
	            t = session.get(clazz, id);
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	            transaction.rollback();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return t;
	}

  
}
