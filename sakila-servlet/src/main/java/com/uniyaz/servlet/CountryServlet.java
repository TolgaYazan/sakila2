package com.uniyaz.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CountryServlet extends HttpServlet {

   /* @Override
    public void init() throws ServletException {
        System.out.println("Init içerisindeyiz");
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("ATİLLA");

    }

  /*  @Override
    public void destroy() {
        System.out.println("Destroy içerisindeyiz");
    }*/
}