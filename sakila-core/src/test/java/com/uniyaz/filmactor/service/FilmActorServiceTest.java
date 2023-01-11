package com.uniyaz.filmactor.service;

import com.uniyaz.filmactor.domain.FilmActor;
import org.junit.Test;

import java.util.List;

public class FilmActorServiceTest {

    @Test
    public void findAllTest() {

        FilmActorService filmActorService = new FilmActorService();
        List<FilmActor> filmActorList = filmActorService.findAll();
        for (FilmActor filmActor : filmActorList) {
            System.out.println(filmActor);
        }
    }
}