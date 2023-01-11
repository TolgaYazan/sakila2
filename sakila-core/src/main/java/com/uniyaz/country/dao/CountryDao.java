package com.uniyaz.country.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.common.dao.BaseDao;
import com.uniyaz.country.domain.Country;
import com.uniyaz.country.queryfilterdto.CountryQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CountryDao extends BaseDao<Country> {

    public CountryDao() {
        super(Country.class);
    }

    public Country save(Country entity) {
        SessionFactory sessionFcountryy = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFcountryy.openSession();
        Transaction transaction = currentSession.beginTransaction();
        entity = (Country) currentSession.merge(entity);
        transaction.commit();
        return entity;
    }

    public void delete(Country country) {
        SessionFactory sessionFcountryy = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFcountryy.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(country);
        transaction.commit();
    }

    public List<Country> findAllByQueryFilterDto(CountryQueryFilterDto countryQueryFilterDto) {

        String hql =
                "Select country " +
                "From Country country " +
                "where 1=1 ";

        if (countryQueryFilterDto.getId() != null) {
            hql += " and country.id = :countryId";
        }

        if (countryQueryFilterDto.getCountry() != null) {
            hql += " and country.country = :country";
        }

        SessionFactory sessionFcountryy = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFcountryy.openSession();
        Query query = currentSession.createQuery(hql);

        if (countryQueryFilterDto.getId() != null) {
            query.setParameter("countryId", countryQueryFilterDto.getId());
        }

        if (countryQueryFilterDto.getCountry() != null) {
            query.setParameter("country", countryQueryFilterDto.getCountry());
        }

        List<Country> countryList = query.list();
        return countryList;
    }


    public List<Country> findAllByQueryFilterDtoCriteria(CountryQueryFilterDto countryQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Country.class);

        if (countryQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", countryQueryFilterDto.getId()));
        }

        if (countryQueryFilterDto.getCountry() != null) {
            criteria.add(Restrictions.eq("country", countryQueryFilterDto.getCountry()));
        }

        List<Country> countryList = criteria.list();
        return countryList;
    }
}