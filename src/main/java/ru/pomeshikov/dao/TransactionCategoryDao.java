package ru.pomeshikov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.pomeshikov.model.TransactionCategoryDO;

@Repository
public class TransactionCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(TransactionCategoryDO transactionCategory){
		sessionFactory.getCurrentSession().saveOrUpdate(transactionCategory);
	}
	
	public void delete(String ukey, Long id){
		Session session = sessionFactory.getCurrentSession();
		session.delete(findById(ukey, id));
	}
	
	public TransactionCategoryDO findById(String ukey, Long id){
		return (TransactionCategoryDO) sessionFactory.getCurrentSession().createCriteria(TransactionCategoryDO.class).add(Restrictions.eq("id", id)).add(Restrictions.eq("ukey", ukey)).uniqueResult();
	}
}