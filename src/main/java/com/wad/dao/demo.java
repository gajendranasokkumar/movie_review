package com.wad.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wad.model.Movie;
import com.wad.util.HibernateUtil;

public class demo {
	public static void main(String[] args) {
		create();		
	}
	public static void create() {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Movie movie = new Movie();
		movie.setName("hello");
		session.persist(movie);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}
}
