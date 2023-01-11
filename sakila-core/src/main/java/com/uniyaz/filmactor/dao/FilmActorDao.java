package com.uniyaz.filmactor.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.filmactor.domain.FilmActor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FilmActorDao {

    public List<FilmActor> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select filmActor From FilmActor filmActor");
        List<FilmActor> filmActorList = query.list();
        return filmActorList;
    }

    public FilmActor save(FilmActor filmActor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        filmActor = (FilmActor) currentSession.merge(filmActor);
        transaction.commit();
        return filmActor;
    }

    public void delete(FilmActor filmActor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(filmActor);
        transaction.commit();
    }
}