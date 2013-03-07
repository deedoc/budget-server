package ru.pomeshikov.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.pomeshikov.model.TransactionDO;

@Repository
public class TransactionDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(TransactionDO transaction){
		sessionFactory.getCurrentSession().saveOrUpdate(transaction);
	}
	
	public List<TransactionDO> findByDate(String ukey, Date date){
		return sessionFactory.getCurrentSession().createCriteria(TransactionDO.class).add(Restrictions.eq("ukey", ukey)).add(Restrictions.eq("date", date)).list();
	}
}