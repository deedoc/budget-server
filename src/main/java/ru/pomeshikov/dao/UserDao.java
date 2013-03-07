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
	
	public void add(UserDO user){
		sessionFactory.getCurrentSession().save(user);
	}
	
	public UserDO findByUkey(String ukey){
		return (UserDO) sessionFactory.getCurrentSession().createCriteria(UserDO.class).add(Restrictions.eq("ukey", ukey)).list().get(0);
	}
	
	public UserDO findByEmailAndPassword(String email, String password){
		return (UserDO) sessionFactory.getCurrentSession().createCriteria(UserDO.class).add(Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("password", password))).list().get(0);
	}
}