package com.uniyaz.servlet;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.service.ActorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ActorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        actor.setLastUpdate(new Date());
        ActorService actorService = new ActorService();
        actorService.save(actor);
    }
}