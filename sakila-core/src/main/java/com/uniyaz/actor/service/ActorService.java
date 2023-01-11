package com.uniyaz.actor.service;

import com.uniyaz.actor.dao.ActorDao;
import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorCriteriaFilterDto;

import java.util.List;

public class ActorService {

    public List<Actor> findAll() {
        ActorDao actorDao = new ActorDao();
        return actorDao.findAll();
    }

    public Actor save(Actor actor) {
        ActorDao actorDao = new ActorDao();
        return actorDao.save(actor);
    }

    public void delete(Actor actor) {
        ActorDao actorDao = new ActorDao();
        actorDao.delete(actor);
    }

    public List<Actor> findAllByName(String name) {
        ActorDao actorDao = new ActorDao();
        return actorDao.findAllByName(name);
    }

    public List<Actor> findAllByQueryFilterDto(ActorCriteriaFilterDto actorCriteriaFilterDto) {

        ActorDao actorDao = new ActorDao();
        return actorDao.findAllByQueryFilterDto(actorCriteriaFilterDto);
    }
}