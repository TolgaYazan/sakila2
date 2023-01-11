package com.uniyaz.filmactor.service;

import com.uniyaz.filmactor.dao.FilmActorDao;
import com.uniyaz.filmactor.domain.FilmActor;

import java.util.List;

public class FilmActorService {

    public List<FilmActor> findAll() {
        FilmActorDao filmActorDao = new FilmActorDao();
        return filmActorDao.findAll();
    }

    public FilmActor save(FilmActor filmActor) {
        FilmActorDao filmActorDao = new FilmActorDao();
        return filmActorDao.save(filmActor);
    }

    public void delete(FilmActor filmActor) {
        FilmActorDao filmActorDao = new FilmActorDao();
        filmActorDao.delete(filmActor);
    }
}