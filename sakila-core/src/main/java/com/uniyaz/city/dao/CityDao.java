package com.uniyaz.city.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.city.domain.City;
import com.uniyaz.city.queryfilterdto.CityQueryFilterDto;
import com.uniyaz.country.domain.Country;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class CityDao {

    public List<City> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select city From City city");
        List<City> cityList = query.list();
        return cityList;
    }

    public City save(City city) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        city = (City) currentSession.merge(city);
        transaction.commit();
        return city;
    }

    public void delete(City city) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(city);
        transaction.commit();
    }

    public List<City> findAllByQueryFilterDto(CityQueryFilterDto cityQueryFilterDto) {

        String hql =
                "Select city " +
                "From City city " +
                "Left Join fetch city.country country " +
                "where 1=1 ";

        if (cityQueryFilterDto.getId() != null) {
            hql += " and city.id = :cityId";
        }

        if (cityQueryFilterDto.getCity() != null) {
            hql += " and city.city = :city";
        }

        if (cityQueryFilterDto.getCountry() != null) {
            hql += " and city.country = :country ";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (cityQueryFilterDto.getId() != null) {
            query.setParameter("cityId", cityQueryFilterDto.getId());
        }

        if (cityQueryFilterDto.getCity() != null) {
            query.setParameter("city", cityQueryFilterDto.getCity());
        }

        if (cityQueryFilterDto.getCountry() != null) {
            query.setParameter("country", cityQueryFilterDto.getCountry());
        }

        List<City> cityList = query.list();
        return cityList;
    }


    public List<City> findAllByQueryFilterDtoByCountryName(String countryName) {

        String hql =
                "Select city " +
                        "From City city " +
                        "Left Join fetch city.country country " +
                        "where 1=1 ";

        if (countryName != null) {
            hql += " and country.country = :country";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (countryName!= null) {
            query.setParameter("country", countryName);
        }

        List<City> cityList = query.list();
        return cityList;
    }


    public List<City> findAllByQueryFilterDtoCriteria(CityQueryFilterDto cityQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(City.class);
        criteria.createAlias("country", "countryAlias", JoinType.LEFT_OUTER_JOIN);

        if (cityQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", cityQueryFilterDto.getId()));
        }

        if (cityQueryFilterDto.getCity() != null) {
            criteria.add(Restrictions.eq("city", cityQueryFilterDto.getCity()));
        }

        if (cityQueryFilterDto.getCountry() != null) {
            criteria.add(Restrictions.eq("countryAlias.country", cityQueryFilterDto.getCountry().getCountry()));
        }

        List<City> cityList = criteria.list();
        return cityList;
    }

    public List<City> findAllByQueryFilterDtoDetachedCriteria(String countryName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(City.class);
        //criteria.createAlias("country", "countryAlias", JoinType.LEFT_OUTER_JOIN);
        //criteria.add(Restrictions.eq("countryAlias.country", countryName));

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Country.class);
        detachedCriteria.add(Restrictions.eq("country", countryName));
        detachedCriteria.setProjection(Projections.property("id"));

        criteria.add(Property.forName("country.id").in(detachedCriteria));

        List<City> cityList = criteria.list();
        return cityList;
    }
}