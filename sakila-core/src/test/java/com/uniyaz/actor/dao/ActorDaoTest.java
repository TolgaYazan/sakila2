package com.uniyaz.actor.dao;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorQueryFilterDto;
import org.junit.Test;

import java.util.List;

public class ActorDaoTest {

    @Test
    public void findAll() {
        ActorDao actorDao = new ActorDao();
        List<Actor> actorList = actorDao.findAll();
        for (Actor actor : actorList) {
            System.out.println(actor);
        }
    }

    @Test
    public void findAllQuery() {

        ActorQueryFilterDto actorQueryFilterDto = new ActorQueryFilterDto();
        actorQueryFilterDto.setFirstName("' or '1'='1");

        ActorDao actorDao = new ActorDao();
        List<Actor> actorList = actorDao.findAllByQueryFilterDtoByQuery(actorQueryFilterDto);
        for (Actor actor : actorList) {
            System.out.println(actor);
        }
    }
}