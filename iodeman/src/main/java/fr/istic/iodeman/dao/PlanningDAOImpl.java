package fr.istic.iodeman.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.istic.iodeman.model.OralDefense;
import fr.istic.iodeman.model.Planning;

@Repository
public class PlanningDAOImpl extends AbstractHibernateDAO implements PlanningDAO {

	public void persist(Planning planning) {
		Session session = getNewSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(planning);
			session.getTransaction().commit();	
		} catch (Exception e){
			if (transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Planning findById(Integer id) {
		Session session = getNewSession();
		Planning planning = (Planning)session.get(Planning.class, id);
		session.close();
		return planning;
	}

	public void delete(Planning entity) {
		Session session = getNewSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();	
		} catch (Exception e){
			if (transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Planning> findAll() {
		Session session = getNewSession();
		List<Planning> plannings = session.createCriteria(Planning.class).list();
		session.close();
		return plannings;
	}

	public void deleteAll() {
		List<Planning> entityList = findAll();
		for (Planning entity : entityList) {
			delete(entity);
		}
	}

}
