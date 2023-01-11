package com.uniyaz.servlet;

import com.google.gson.Gson;
import com.uniyaz.city.domain.City;
import com.uniyaz.city.service.CityService;
import com.uniyaz.country.domain.Country;
import com.uniyaz.country.service.CountryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CountryJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CityService cityService = new CityService();
        String countryName =  req.getParameter("countryName");

        List<City> cityList= cityService.findAllByQueryFilterDtoCriteriaByCounbtry(countryName);




        Gson gson = new Gson();
        String cityListAsJson = gson.toJson(cityList);

        resp.setContentType("application/json");
        resp.getWriter().write(cityListAsJson);


    }
}
