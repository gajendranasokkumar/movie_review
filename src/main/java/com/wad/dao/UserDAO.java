package com.wad.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wad.model.User;
import com.wad.util.HibernateUtil;

public class UserDAO {
	public User getUser(String username, String password) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from User where username = :username and password = :password", User.class)
					.setParameter("username", username).setParameter("password", password).uniqueResult();
		}
	}

	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
