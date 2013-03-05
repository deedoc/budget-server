package ru.pomeshikov.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.pomeshikov.model.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void add(User user){
		sessionFactory.getCurrentSession().save(user);
	}
}