package ru.pomeshikov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.pomeshikov.model.UserDO;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(UserDO user){
		sessionFactory.getCurrentSession().save(user);
	}
	
	public UserDO findByUkey(String ukey){
		return (UserDO) sessionFactory.getCurrentSession().createCriteria(UserDO.class).add(Restrictions.eq("ukey", ukey)).uniqueResult();
	}
	
	public UserDO findByEmailAndPassword(String email, String password){
		return (UserDO) sessionFactory.getCurrentSession().createCriteria(UserDO.class).add(Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("password", password))).uniqueResult();
	}
}