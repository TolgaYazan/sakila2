package com.uniyaz.actor.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorCriteriaFilterDto;
import com.uniyaz.common.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ActorDao extends BaseDao<Actor> {

    public ActorDao() {
        super(Actor.class);
    }

    public List<Actor> findAllByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select actor From Actor actor where actor.firstName = :name ");
        query.setParameter("name", name);
        List<Actor> actorList = query.list();
        return actorList;
    }
}