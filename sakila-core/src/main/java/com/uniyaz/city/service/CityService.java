package com.uniyaz.city.service;

import com.uniyaz.city.dao.CityDao;
import com.uniyaz.city.domain.City;
import com.uniyaz.city.queryfilterdto.CityQueryFilterDto;

import java.util.List;

public class CityService {

    public List<City> findAll() {
        CityDao cityDao = new CityDao();
        return cityDao.findAll();
    }

    public City save(City city) {
        CityDao cityDao = new CityDao();
        return cityDao.save(city);
    }

    public void delete(City city) {
        CityDao cityDao = new CityDao();
        cityDao.delete(city);
    }

    public List<City> findAllByQueryFilterDto(CityQueryFilterDto cityQueryFilterDto) {
        CityDao cityDao = new CityDao();
        return cityDao.findAllByQueryFilterDto(cityQueryFilterDto);
    }

    public List<City> findAllByQueryFilterDtoCriteria(CityQueryFilterDto cityQueryFilterDto) {
        CityDao cityDao = new CityDao();
        return cityDao.findAllByQueryFilterDtoCriteria(cityQueryFilterDto);
    }

    public List<City> findAllByQueryFilterDtoCriteriaByCounbtry(String  countryName) {
        CityDao cityDao = new CityDao();
        return cityDao.findAllByQueryFilterDtoByCountryName(countryName);
    }
}