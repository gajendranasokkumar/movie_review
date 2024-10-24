package com.wad.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.wad.model.Movie;
import com.wad.util.HibernateUtil;


public class MovieDAO {

	public void saveMovie(Movie movie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			System.out.println("hello");
			System.out.println(movie);
			session.persist(movie);
			transaction.commit();
		} catch (Exception e) {}
	}

	public List<Movie> getAllMovies() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Movie", Movie.class).list();
		}
	}

	public Movie getMovie(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Movie.class, id);
		}
	}

	public void updateMovie(Movie movie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(movie);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
