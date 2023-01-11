package com.uniyaz.common.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.common.BaseCriteriaFilterDto;
import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.common.query.SakilaQueryable;
import org.hibernate.*;

import java.util.List;

public class BaseDao <T extends BaseEntity> {

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(entityClass);
        List<T> entityList = criteria.list();
        return entityList;
    }

    public T save(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        entity = (T) currentSession.merge(entity);
        transaction.commit();
        return entity;
    }

    public void delete(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(entity);
        transaction.commit();
    }

    public List<T> findAllByQueryFilterDto(BaseCriteriaFilterDto baseCriteriaFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(entityClass);
        baseCriteriaFilterDto.addFilters(criteria);
        return criteria.list();
    }

    public List<T> findAllByQueryFilterDtoByQuery(SakilaQueryable sakilaQueryable) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        String hql = sakilaQueryable.buildQuery();
        Query query = currentSession.createQuery(hql);
        return query.list();
    }
}