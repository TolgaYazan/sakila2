package com.uniyaz.film.service;

import com.uniyaz.film.dao.FilmDao;
import com.uniyaz.film.domain.Film;

import java.util.List;

public class FilmService {

    public List<Film> findAll() {
        FilmDao filmDao = new FilmDao();
        return filmDao.findAll();
    }

    public Film save(Film film) {
        FilmDao filmDao = new FilmDao();
        return filmDao.save(film);
    }

    public void delete(Film film) {
        FilmDao filmDao = new FilmDao();
        filmDao.delete(film);
    }
}