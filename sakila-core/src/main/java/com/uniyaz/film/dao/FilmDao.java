package com.uniyaz.film.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.film.domain.Film;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FilmDao {

    public List<Film> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select film From Film film");
        List<Film> filmList = query.list();
        return filmList;
    }

    public Film save(Film film) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        film = (Film) currentSession.merge(film);
        transaction.commit();
        return film;
    }

    public void delete(Film film) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(film);
        transaction.commit();
    }
}