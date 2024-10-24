package com.wad.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wad.model.Rating;
import com.wad.util.HibernateUtil;

public class RatingDAO {
	public void saveRating(Rating rating) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(rating);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
