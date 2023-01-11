package com.uniyaz.country.service;

import com.uniyaz.country.dao.CountryDao;
import com.uniyaz.country.domain.Country;
import com.uniyaz.country.queryfilterdto.CountryQueryFilterDto;

import java.util.List;

public class CountryService {

    public List<Country> findAll() {
        CountryDao countryDao = new CountryDao();
        return countryDao.findAll();
    }

    public Country save(Country country) {
        CountryDao countryDao = new CountryDao();
        return countryDao.save(country);
    }

    public void delete(Country country) {
        CountryDao countryDao = new CountryDao();
        countryDao.delete(country);
    }

    public List<Country> findAllByQueryFilterDto(CountryQueryFilterDto countryQueryFilterDto) {
        CountryDao countryDao = new CountryDao();
        return countryDao.findAllByQueryFilterDto(countryQueryFilterDto);
    }
}